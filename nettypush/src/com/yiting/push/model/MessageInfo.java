package com.yiting.push.model;

import java.util.Date;

/**
 * Created by Administrator on 2015/4/22.
 */
public class MessageInfo {
	private int msgId;
	private int appId;
	private String title;
	private String content;
	private int type;
	private int isOfflineShow;
	private Date pushTime;
	private Date startTime;
	private Date endTime;
	private long expireTimes;
	private int state;

	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getIsOfflineShow() {
		return isOfflineShow;
	}

	public void setIsOfflineShow(int isOfflineShow) {
		this.isOfflineShow = isOfflineShow;
	}

	public Date getPushTime() {
		return pushTime;
	}

	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public long getExpireTimes() {
		return expireTimes;
	}

	public void setExpireTimes(long expireTimes) {
		this.expireTimes = expireTimes;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
