package com.yiting.push.handler.process.factory;

import com.yiting.push.handler.process.IHandleProcessor;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by Administrator on 2015/4/28.
 */
public interface IHandleProcessorFactory {
	public IHandleProcessor findHandlerProcessor(ChannelHandlerContext ctx,Object msg);
}

