package com.yiting.push.handler.process;

import com.xwtec.protoc.CommandProtoc;
import com.yiting.push.model.DeviceInfo;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by Administrator on 2015/4/27.
 */
public class RegistrationProcessor extends AbstractHandleProcessor<CommandProtoc.Registration> {
	@Override
	public CommandProtoc.PushMessage process(ChannelHandlerContext ctx) {
		CommandProtoc.Registration registration=this.getProcessObject();
		if(ctx!=null&&registration!=null) {
			int result = applicationContext.registerDevice(ctx.channel(), registration);
			String regId=null;
			if(result>0){
				DeviceInfo deviceInfo=applicationContext.getDeviceInfo(registration.getDeviceId());
				regId=deviceInfo.getRegId();
			}
			return applicationContext.createCommandRegistrationResult(registration.getAppKey(),registration.getAppPackage(),regId);
		}
		return null;
	}
}
