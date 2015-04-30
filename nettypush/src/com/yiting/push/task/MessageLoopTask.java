package com.yiting.push.task;

import com.yiting.push.handler.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

/**
 * 消息轮询
 * Created by Administrator on 2015/4/27.
 */
@Service("messageLoopTask")
@Scope("singleton")
public class MessageLoopTask extends TimerTask {

	@Resource
	private ApplicationContext applicationContext;

	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	public void run() {
		try{
			if(applicationContext!=null){
				System.out.println(sdf.format(new Date())+"message loop task");
				applicationContext.sendAllMessages();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
