package com.yiting.push.handler;

import com.xwtec.protoc.CommandProtoc;
import com.yiting.push.client.model.Message;
import com.yiting.push.client.model.MessageResult;
import com.yiting.push.handler.context.ApplicationContext;
import com.yiting.push.model.AppInfo;
import com.yiting.push.model.DeviceInfo;
import com.yiting.push.model.MessageDevice;
import com.yiting.push.model.MessageInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/4/28.
 */
public class MessageHandler extends ChannelInboundHandlerAdapter {

	@Resource
	private ApplicationContext applicationContext;

	private long endTimesInMills;
	private long expireTimes;

	public long getEndTimesInMills() {
		return endTimesInMills;
	}

	public void setEndTimesInMills(long endTimesInMills) {
		this.endTimesInMills = endTimesInMills;
	}

	public long getExpireTimes() {
		return expireTimes;
	}

	public void setExpireTimes(long expireTimes) {
		this.expireTimes = expireTimes;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		if(ctx!=null)
		{
			applicationContext.addChannel(ctx.channel());
		}
		System.out.println("applicationcontext add channel (remote address):"+ctx.channel().remoteAddress());
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		Integer msgId=null;
		boolean success=false;
		if(msg!=null&&msg instanceof Message){
			Message message=(Message)msg;
			AppInfo app=applicationContext.getAppinfo(message.getAppkey());
			if(app==null){
				System.out.print("the app key is invalid:"+message.getAppkey());
				return;
			}

			Calendar calendar=Calendar.getInstance();
			Date today=calendar.getTime();
			calendar.setTimeInMillis(today.getTime()+endTimesInMills);
			Date endTime=calendar.getTime();

			System.out.println("Today:"+today+" -endtime:"+endTime);
			MessageInfo messageInfo = new MessageInfo();
			messageInfo.setAppId(app.getAppId());
			messageInfo.setContent(message.getContent());
			messageInfo.setTitle(message.getTitle());
			messageInfo.setEndTime(endTime);
			messageInfo.setStartTime(today);
			messageInfo.setPushTime(today);
			messageInfo.setExpireTimes(message.isReceipt() ? expireTimes : 0);
			messageInfo.setIsOfflineShow(message.isOfflineShow() ? ApplicationContext.MESSAGE_OFFLINE_SHOW_YES : ApplicationContext.MESSAGE_OFFLINE_SHOW_NO);
			messageInfo.setType(message.isAllPush() ? ApplicationContext.MESSAGE_TYPE_SEND_TO_ALL : ApplicationContext.MESSAGE_TYPE_SEND_TO_POINT);
			messageInfo.setState(ApplicationContext.MESSAGE_STATE_YES);

			int ret=applicationContext.saveMessageInfo(messageInfo);
			if(ret>0){
				msgId=messageInfo.getMsgId();
				success=true;
				/**
				 * 根据消息类型进行发型
				 */
				if(!message.isAllPush()){
					List<DeviceInfo> devices=applicationContext.queryDeviceInfoListByRegIds(message.getAppkey(),message.getDevices());
					//消息推送
					applicationContext.sendMessageToDevices(messageInfo, CommandProtoc.Message.UserStatus.ONLINE, devices);
					//消息存储
					if(devices!=null&&devices.size()>0){
						List<MessageDevice> messageDevices=new ArrayList<MessageDevice>();
						for(DeviceInfo deviceInfo:devices){
							MessageDevice md=new MessageDevice();
							md.setMsgId(messageInfo.getMsgId());
							md.setDeviceId(deviceInfo.getDeviceId());
							messageDevices.add(md);
						}
						applicationContext.saveMessageDevices(messageDevices);
					}
				}else{
					applicationContext.sendMessageToAll(messageInfo, CommandProtoc.Message.UserStatus.ONLINE);
				}
			}

			messageInfo=null;
		}

		/**
		 * 写回消息成功信息
		 */
		MessageResult messageResult=new MessageResult();
		messageResult.setMsgId(msgId);
		messageResult.setSuccess(success);
		ctx.writeAndFlush(messageResult);
	}


	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		super.channelReadComplete(ctx);
	}
}
