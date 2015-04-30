package com.yiting.push.handler.process;


import com.xwtec.protoc.CommandProtoc;
import com.yiting.push.model.AppInfo;
import com.yiting.push.model.DeviceInfo;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by Administrator on 2015/4/27.
 */
public class DeviceOfflineProcessor extends AbstractHandleProcessor<CommandProtoc.DeviceOffline> {
	@Override
	public CommandProtoc.PushMessage process(ChannelHandlerContext ctx) {

		CommandProtoc.DeviceOnoffResult.ResultCode resultCode=null;
		CommandProtoc.Message.UserStatus userStatus=CommandProtoc.Message.UserStatus.OFFLINE;
		//获取设备Id；
		String deviceId=this.getProcessObject().getDeviceId();
		DeviceInfo deviceInfo=this.applicationContext.getDeviceInfo(deviceId);
		AppInfo appinfo=deviceInfo==null?null:this.applicationContext.getAppinfo(deviceInfo.getAppkey());
		String appPackage=appinfo==null?null:appinfo.getAppPackage();
		boolean ret=this.applicationContext.online(deviceId);

		if(ret){
			resultCode= CommandProtoc.DeviceOnoffResult.ResultCode.SUCCESS;
			System.out.println("device offLine success");
		}else {
			resultCode= CommandProtoc.DeviceOnoffResult.ResultCode.FAILED;
			System.out.println("device offLine error");
		}
		return this.applicationContext.createCommandDeviceOnoffResult(appPackage,resultCode,userStatus);
	}


}
