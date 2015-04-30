package com.yiting.push.model;

import java.util.List;

/**
 * Created by Administrator on 2015/4/22.
 */
public class ChannelInfo {
	private Long heartTime;
	private List<ChannelDeviceInfo> channelDeviceInfos;

	public Long getHeartTime() {
		return heartTime;
	}

	public void setHeartTime(Long heartTime) {
		this.heartTime = heartTime;
	}

	public List<ChannelDeviceInfo> getChannelDeviceInfos() {
		return channelDeviceInfos;
	}

	public void setChannelDeviceInfos(List<ChannelDeviceInfo> channelDeviceInfos) {
		this.channelDeviceInfos = channelDeviceInfos;
	}
}
