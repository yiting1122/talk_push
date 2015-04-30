package com.yiting.push.handler.process;

import com.xwtec.protoc.CommandProtoc;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by Administrator on 2015/4/24.
 */
public interface IHandleProcessor {
	public CommandProtoc.PushMessage process(ChannelHandlerContext ctx);
	public void updateObject(Object t);
}
