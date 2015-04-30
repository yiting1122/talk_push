package com.yiting.push.server;

import com.google.protobuf.ExtensionRegistry;
import com.xwtec.protoc.CommandProtoc;
import com.yiting.push.handler.DispatcherHandler;
import com.yiting.push.handler.MessageHandler;
import com.yiting.push.handler.context.ApplicationContext;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.nio.NioEventLoopGroup;

import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.util.List;

/**
 * Created by Administrator on 2015/4/22.
 */
@Service("nettyServer")
@Scope("singleton")
public class NettyServer implements IServer {

	private int port;
	private EventLoopGroup bossGroup;
	private EventLoopGroup workerGroup;

	@Resource
	private DispatcherHandler dispatcherHandler;

	private MessageHandler messageHandler;

	private List<String> serverIpconfig;

	@Resource
	private ApplicationContext applicationContext;

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public List<String> getServerIpconfig() {
		return serverIpconfig;
	}

	public void setServerIpconfig(List<String> serverIpconfig) {
		this.serverIpconfig = serverIpconfig;
	}

	@Override
	public void start() throws Exception {
		bossGroup=new NioEventLoopGroup();
		workerGroup=new NioEventLoopGroup();

		try{
			ServerBootstrap b=new ServerBootstrap();
			b.group(bossGroup,workerGroup);
			b.channel(NioServerSocketChannel.class);
			b.localAddress(new InetSocketAddress(port));

			final ExtensionRegistry registry=ExtensionRegistry.newInstance();
			CommandProtoc.registerAllExtensions(registry);
			b.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel socketChannel) throws Exception {
					InetSocketAddress address= socketChannel.remoteAddress();
					if(address!=null){
						if(address.getAddress()!=null){
							String ip=address.getAddress().getHostAddress();
							System.out.println("hostAddress ip:"+ip);
							if(serverIpconfig!=null&&ip!=null&&serverIpconfig.contains(ip)){
								System.out.println("contains ip");
								ChannelPipeline pipeline=socketChannel.pipeline();
								pipeline.addLast("frameDecoder",new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
								pipeline.addLast("frameEncoder",new LengthFieldPrepender(4));
								pipeline.addLast("encode",new ObjectEncoder());
								pipeline.addLast("decode",new ObjectDecoder(ClassResolvers.weakCachingConcurrentResolver(null)));
								pipeline.addLast("handler",messageHandler);
							}

						}
					}

					ChannelPipeline pipeline=socketChannel.pipeline();
					pipeline.addLast("frameDecoder", new ProtobufVarint32FrameDecoder());
					pipeline.addLast("protobufDecoder", new ProtobufDecoder(CommandProtoc.PushMessage.getDefaultInstance(), registry));
					pipeline.addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender());
					pipeline.addLast("protobufEncoder", new ProtobufEncoder());
					pipeline.addLast("hander", dispatcherHandler);
				}
			});

			b.option(ChannelOption.SO_BACKLOG,128);
			b.childOption(ChannelOption.SO_KEEPALIVE,true);
			//绑定server，采用同步方法 直到绑定成功
			ChannelFuture cf=b.bind().sync();
			applicationContext.addChannel(cf.channel());
			System.out.println("server applicationContext addChannel:======================\n" + cf.channel().localAddress() + "-"
					+ cf.channel().remoteAddress());
			System.out.println(this.getClass().getName() + " started and listen on " + cf.channel().localAddress());
			// Wait until the server socket is closed.
			cf.channel().closeFuture().sync();

		}finally {
			System.out.println("finally!");
			stopServer();
		}

	}

	/**
	 * 停止服务器
	 */
	@Override
	public void stop() throws Exception {
		ChannelGroupFuture future = applicationContext.closeAllChannels();
		if (future != null) {
			future.addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					future.channel().close();
					stopServer();
				}
			});
		}

	}

	private void stopServer() throws Exception {
		if (workerGroup != null) {
			workerGroup.shutdownGracefully();
			workerGroup = null;
		}

		if (bossGroup != null) {
			bossGroup.shutdownGracefully();
			bossGroup = null;
		}
		applicationContext.destory();
		System.out.println(this.getClass().getName() + " stop netty server on " + this.port + " success!");
	}

	@Override
	public void restart() throws Exception {
		System.out.println(this.getClass().getName() + " restarting  netty server on port:" + port);
		ChannelGroupFuture future = applicationContext.closeAllChannels();
		if (future != null) {
			future.addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					future.channel().close();
					stopServer();
					start();
				}
			});
		}
	}
}
