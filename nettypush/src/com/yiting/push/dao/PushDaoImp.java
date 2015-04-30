package com.yiting.push.dao;

import com.yiting.push.model.*;
import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/4/22.
 */
@Resource
@Scope("singleton")
public class PushDaoImp extends AbstractBaseDao implements IPushDao {
	@Override
	public int saveMessage(MessageInfo messageInfo) {
		return 0;
	}

	@Override
	public List<MessageInfo> queryMessageList() {
		return null;
	}

	@Override
	public List<MessageInfo> queryMessageOfflineList(String deviceId) {
		return null;
	}

	@Override
	public List<DeviceInfo> queryMessageDeviceList(Integer appId, long msgId) {
		return null;
	}

	@Override
	public int saveMessagePushedInfo(MessagePushedInfo messagePushedInfo) {
		return 0;
	}

	@Override
	public int[] saveMessagePushedList(List<MessagePushedInfo> messagePushedInfos) {
		return new int[0];
	}

	@Override
	public List<DeviceInfo> queryDeviceListByAppId(Integer appId) {
		return null;
	}

	@Override
	public DeviceInfo queryDeviceByDeviceId(String deviceId) {
		return null;
	}

	@Override
	public int saveOrUpdateDeviceInfo(DeviceInfo deviceInfo) {
		return 0;
	}

	@Override
	public int[] updateDeviceInfoList(List<DeviceInfo> deviceInfos) {
		return new int[0];
	}

	@Override
	public List<AppInfo> queryAppList() {
		return null;
	}

	@Override
	public List<MessageOffline> queryMessageOfflineList() {
		return null;
	}

	@Override
	public int deleteMessageOffline(MessageOffline messageOffline) {
		return 0;
	}

	@Override
	public int saveOrUpdateMessageOffline(MessageOffline messageOffline) {
		return 0;
	}

	@Override
	public Map<String, List<MessagePushedInfo>> queryMessagePushedInfo() {
		return null;
	}

	@Override
	public int[] saveOrUpdateMessageDeviceList(List<MessageDevice> devices) {
		return new int[0];
	}

	@Override
	public List<DeviceInfo> queryDeviceInfoFromRegIds(String appKey, List<String> regIds) {
		return null;
	}
}
