package com.yiting.push.model;

import io.netty.channel.Channel;

import java.util.List;

/**
 * Created by Administrator on 2015/4/22.
 */
public class ChannelDeviceInfo {
	private Channel channel;
	private DeviceInfo deviceInfo;
	private List<MessagePushedInfo> messagePushedInfos;

//	public Channel getChannel() {
//		return channel;
//	}
//
//	public void setChannel(Channel channel) {
//		this.channel = channel;
//	}

	public DeviceInfo getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(DeviceInfo deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public List<MessagePushedInfo> getMessagePushedInfos() {
		return messagePushedInfos;
	}

	/**
	 * 是否需要同步 有待考虑
	 * @param messagePushedInfos
	 */
	public void setMessagePushedInfos(List<MessagePushedInfo> messagePushedInfos) {
		if(this.messagePushedInfos!=null&&this.messagePushedInfos.size()>0){
			this.messagePushedInfos.clear();
			this.messagePushedInfos=null;
		}
		this.messagePushedInfos = messagePushedInfos;
	}
}
