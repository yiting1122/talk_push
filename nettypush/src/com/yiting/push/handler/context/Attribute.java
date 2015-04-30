package com.yiting.push.handler.context;

/**
 * Created by Administrator on 2015/4/22.
 */
public interface Attribute {
	/**
	 * 消息类型 0-系统消息 1-用户消息
	 */
	public static final int MESSAGE_TYPE_SYSTEM = 0;
	public static final int MESSAGE_TYPE_USER = 1;

	/**
	 * 消息状态状态 0-有效 1-无效
	 */
	public static final int MESSAGE_STATE_YES = 0;
	public static final int MESSAGE_STATE_NO = 1;


	/**
	 * 设备是否在线（1-在线 0-离线）
	 */
	public static final int DEVICE_ONLINE_YES = 1;
	public static final int DEVICE_ONLINE_NO = 0;

	/**
	 * 消息类型 0-群消息 1-点对点消息
	 */
	public static final int MESSAGE_TYPE_SEND_TO_ALL = 0;
	public static final int MESSAGE_TYPE_SEND_TO_POINT = 1;

	/**
	 * 离线是否可见 0-离线设备发送离线消息 1-离线设备不发送
	 */
	public static final int MESSAGE_OFFLINE_SHOW_YES = 0;
	public static final int MESSAGE_OFFLINE_SHOW_NO = 1;

	/**
	 * 设备-消息状态 0-初始状态（消息已发送尚未收到回执，下次轮询不能再发） 1-收到回执（不在发送相同消息） 2-push超时仍未收到回执
	 */
	public static final int MESSAGE_PUSHED_STATE_INIT = 0;
	public static final int MESSAGE_PUSHED_STATE_RECEIPT = 1;
	public static final int MESSAGE_PUSHED_STATE_FAILED = 2;

	/**
	 * 注册设备错误码
	 */
	public static final int REGIST_SUCCESS = 1;
	public static final int REGIST_FAILED = 0;
	public static final int REGIST_APPINFO_NULL = -100;
	public static final int REGIST_APPKEY_PACKAGE_NOT_MATCHED = -200;


}
