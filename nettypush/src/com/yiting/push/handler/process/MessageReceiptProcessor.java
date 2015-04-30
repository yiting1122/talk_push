package com.yiting.push.handler.process;

import com.xwtec.protoc.CommandProtoc;
import io.netty.channel.ChannelHandlerContext;

/**
 * 消息回执处理
 * Created by Administrator on 2015/4/27.
 */
public class MessageReceiptProcessor extends AbstractHandleProcessor<CommandProtoc.MessageReceipt> {
	@Override
	public CommandProtoc.PushMessage process(ChannelHandlerContext ctx) {
		CommandProtoc.MessageReceipt messageReceipt=this.getProcessObject();
		if(messageReceipt!=null&&ctx!=null){
			applicationContext.saveMessagePushedInfo(ctx.channel(),messageReceipt.getAppKey(),messageReceipt.getMsgId(),messageReceipt.getRegistrationId());
		}
		return null;
	}
}
