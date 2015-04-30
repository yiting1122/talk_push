package com.yiting.push.handler.process.factory;

import com.xwtec.protoc.CommandProtoc;
import com.yiting.push.handler.process.*;
import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/4/28.
 */
public class HandlerProcessorFactory implements IHandleProcessorFactory {

	//处理器集合
	private static Map<CommandProtoc.PushMessage.Type, IHandleProcessor> processors = new HashMap<CommandProtoc.PushMessage.Type, IHandleProcessor>();


	@Override
	public IHandleProcessor findHandlerProcessor(ChannelHandlerContext ctx, Object msg) {
		if (msg == null) {
			return null;
		}

		if (msg instanceof CommandProtoc.PushMessage) {
			CommandProtoc.PushMessage pushMessage = (CommandProtoc.PushMessage) msg;
			CommandProtoc.PushMessage.Type type = pushMessage.getType();

			if (type == null) {
				return null;
			}

			IHandleProcessor handleProcessor = null;
			synchronized (processors) {
				handleProcessor = processors.get(type);
				Object obj = null;
				boolean isNew = handleProcessor == null ? true : false;
				switch (type) {
					case HEART_BEAT:
						if (handleProcessor == null) {
							handleProcessor = new HeartbeatProcessor();
						}
						break;
					case DEVICE_ONLINE:
						obj = pushMessage.getDeviceOnline();
						if (handleProcessor == null) {
							handleProcessor = new DeviceOnlineProcessor();
						}
					case DEVICE_OFFLINE:
						// 设备下线
						obj = pushMessage.getDeviceOffline();
						if (handleProcessor == null) {
							handleProcessor = new DeviceOfflineProcessor();
						}
						break;
					case REGISTRATION:
						// 设备注册
						obj = pushMessage.getRegistration();
						if (handleProcessor == null) {
							handleProcessor = new RegistrationProcessor();
						}
						break;
					case MESSAGE_RECEIPT:
						// 消息回执
						obj = pushMessage.getMessageReceipt();
						if (handleProcessor == null) {
							handleProcessor = new MessageReceiptProcessor();
						}
						break;
					default:
						break;
				}

				if (isNew) {
					processors.put(type, handleProcessor);
				}
				handleProcessor.updateObject(obj);
			}
			return handleProcessor;
		}


		return null;
	}
}
