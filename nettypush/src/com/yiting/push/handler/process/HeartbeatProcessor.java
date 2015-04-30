package com.yiting.push.handler.process;

import com.xwtec.protoc.CommandProtoc;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by Administrator on 2015/4/27.
 */
public class HeartbeatProcessor extends AbstractHandleProcessor<Object> {
	@Override
	public CommandProtoc.PushMessage process(ChannelHandlerContext ctx) {
		if(ctx!=null){
			applicationContext.refreshHeart(ctx.channel());
		}
		return null;
	}
}
