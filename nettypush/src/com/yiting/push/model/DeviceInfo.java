package com.yiting.push.model;

import java.util.Date;

/**
 * Created by Administrator on 2015/4/22.
 */
public class DeviceInfo {
	private String regId;
	private int userId;
	private int appId;
	private String appkey;
	private int isOnline;
	private String deviceId;
	private String imei;
	private String channel;
	private Date onlineTime;
	private Date offlineTime;

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public int getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(int isOnline) {
		this.isOnline = isOnline;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Date getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(Date onlineTime) {
		this.onlineTime = onlineTime;
	}

	public Date getOfflineTime() {
		return offlineTime;
	}

	public void setOfflineTime(Date offlineTime) {
		this.offlineTime = offlineTime;
	}

	@Override
	public String toString() {
		return "DeviceInfo{" +
				"regId='" + regId + '\'' +
				", userId=" + userId +
				", appId=" + appId +
				", isOnline=" + isOnline +
				", deveceId='" + deviceId + '\'' +
				", imei='" + imei + '\'' +
				", channel='" + channel + '\'' +
				", onlineTime=" + onlineTime +
				", offlineTime=" + offlineTime +
				'}';
	}
}
