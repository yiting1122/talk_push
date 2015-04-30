package com.yiting.push.server;

/**
 * Created by yiting on 2015/4/22.
 */
public interface IServer {
	public void start() throws Exception;
	public void stop() throws Exception;
	public void restart() throws Exception;
}
