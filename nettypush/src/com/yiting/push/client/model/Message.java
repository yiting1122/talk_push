package com.yiting.push.client.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/4/28.
 */
public class Message implements Serializable{
	private static final long serialVersionUID = -7499550318903980815L;
	private String appkey;
	private String title;
	private String content;
	private boolean isOfflineShow;
	private boolean isReceipt;
	private boolean isAllPush;
	private List<String> devices;


	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
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

	public boolean isOfflineShow() {
		return isOfflineShow;
	}

	public void setOfflineShow(boolean isOfflineShow) {
		this.isOfflineShow = isOfflineShow;
	}

	public boolean isReceipt() {
		return isReceipt;
	}

	public void setReceipt(boolean isReceipt) {
		this.isReceipt = isReceipt;
	}

	public boolean isAllPush() {
		return isAllPush;
	}

	public void setAllPush(boolean isAllPush) {
		this.isAllPush = isAllPush;
	}

	public List<String> getDevices() {
		return devices;
	}

	public void setDevices(List<String> devices) {
		this.devices = devices;
	}
}
