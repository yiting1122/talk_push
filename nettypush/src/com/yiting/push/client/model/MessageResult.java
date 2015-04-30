package com.yiting.push.client.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/4/28.
 */
public class MessageResult implements Serializable {
	private static final long serialVersionUID = -1216556212628871134L;
	private Integer msgId;
	private boolean success;

	public Integer getMsgId() {
		return msgId;
	}

	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
