package com.yiting.push.dao;

import com.yiting.push.model.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/4/22.
 */
public interface IPushDao {
	/**
	 * 保存消息
	 * @param messageInfo
	 * @return
	 */
	public int saveMessage(MessageInfo messageInfo);

	/**
	 * 查询push消息
	 * @return
	 */
	public List<MessageInfo> queryMessageList();

	/**
	 * 获取decive的离线push消息
	 * @param deviceId
	 * @return
	 */
	public List<MessageInfo> queryMessageOfflineList(String deviceId);

	/**
	 * 查询消息设备列表信息
	 * @param appId
	 * @param msgId
	 * @return
	 */
	public List<DeviceInfo> queryMessageDeviceList(Integer appId,long msgId);


	/**
	 * 保存设备单个推送消息
	 * @param messagePushedInfo
	 * @return
	 */
	public int saveMessagePushedInfo(MessagePushedInfo messagePushedInfo);


	/**
	 * 保存设备多个推送消息
	 * @param messagePushedInfos
	 * @return
	 */
	public int[] saveMessagePushedList(List<MessagePushedInfo> messagePushedInfos);

	/**
	 * 查询设备列表
	 * @param appId
	 * @return
	 */
	public List<DeviceInfo> queryDeviceListByAppId(Integer appId);

	/**
	 * 查询特定设备
	 * @param deviceId
	 * @return
	 */
	public DeviceInfo queryDeviceByDeviceId(String deviceId);


	/**
	 * 更新设备信息
	 * @param deviceInfo
	 * @return
	 */
	public int saveOrUpdateDeviceInfo(DeviceInfo deviceInfo);


	/**
	 * 批量更新
	 * @param deviceInfos
	 * @return
	 */
	public int[] updateDeviceInfoList(List<DeviceInfo> deviceInfos);

	/**
	 * 查询app列表信息
	 * @return
	 */
	public List<AppInfo> queryAppList();

	/**
	 * 查询离线消息-设备列表
	 * @return
	 */
	public List<MessageOffline> queryMessageOfflineList();

	/**
	 * 删除离线消息
	 * @param messageOffline
	 * @return
	 */
	public int deleteMessageOffline(MessageOffline messageOffline);

	/**
	 * 保存离线消息到数据库
	 * @param messageOffline
	 * @return
	 */
	public int saveOrUpdateMessageOffline(MessageOffline messageOffline);

	/**
	 * 获取已经推送的消息列表
	 * @return
	 */
	public Map<String,List<MessagePushedInfo>> queryMessagePushedInfo();

	/**
	 * 保存消息-设备列表
	 * @param devices
	 * @return
	 */
	public int[] saveOrUpdateMessageDeviceList(List<MessageDevice> devices);

	/**
	 * 根据app key和注册Ids获取设备信息列表
	 * @param appKey
	 * @param regIds
	 * @return
	 */
	public List<DeviceInfo> queryDeviceInfoFromRegIds(String appKey,List<String> regIds);
}
