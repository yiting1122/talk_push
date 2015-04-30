package com.yiting.push.handler.process;

import com.xwtec.protoc.CommandProtoc;
import com.yiting.push.model.AppInfo;
import com.yiting.push.model.DeviceInfo;
import com.yiting.push.model.MessageInfo;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

/**
 * Created by Administrator on 2015/4/27.
 */
public class DeviceOnlineProcessor extends AbstractHandleProcessor<CommandProtoc.DeviceOnline> {
	@Override
	public CommandProtoc.PushMessage process(ChannelHandlerContext ctx) {
		String deviceId = this.getProcessObject().getDeviceId();
		DeviceInfo deviceInfo = this.applicationContext.getDeviceInfo(deviceId);
		AppInfo appinfo = deviceInfo == null ? null : this.applicationContext.getAppinfo(deviceInfo.getAppkey());
		String appPackage = appinfo == null ? null : appinfo.getAppPackage();
		CommandProtoc.DeviceOnoffResult.ResultCode resultCode = null;
		CommandProtoc.Message.UserStatus userStatus = CommandProtoc.Message.UserStatus.ONLINE;
		boolean ret = this.applicationContext.online(deviceId);
		resultCode = ret ? CommandProtoc.DeviceOnoffResult.ResultCode.SUCCESS : CommandProtoc.DeviceOnoffResult.ResultCode.SUCCESS;
		CommandProtoc.PushMessage pushMessage = this.applicationContext.createCommandDeviceOnoffResult(appPackage, resultCode, userStatus);
		System.out.println("writeAndFlush DeviceOnoffResult:========" + pushMessage);
		//上线消息
		ctx.writeAndFlush(pushMessage);
		if (ret) {
			pushMessage = this.applicationContext.getDefaultPushMessageForLogin(deviceId, "welcome", "login to the server", false);
			System.out.println("writeandflush getDefaultPushMessageForLogin:======" + pushMessage);
			//平台默认消息
			ctx.writeAndFlush(pushMessage);
			//查询该设备是否有离线消息，有的话返回离线消息列表
			List<MessageInfo> messagesList=this.applicationContext.getMessageOfflineDevice(deviceId);
			if (messagesList != null && messagesList.size() > 0) {
				for (MessageInfo info : messagesList) {
					System.out.println("writeandflush the off line message" + info.getMsgId() + info.getTitle());
					this.applicationContext.sendMessage(info, CommandProtoc.Message.UserStatus.OFFLINE);
				}
			}
		}

		return null;
	}
}
