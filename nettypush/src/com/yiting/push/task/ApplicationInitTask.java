package com.yiting.push.task;


import com.yiting.push.handler.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

/**
 * Application初始化任务
 * 
 * @author maofw
 * 
 */
@Service("applicationInitTask")
@Scope("singleton")
public class ApplicationInitTask extends TimerTask {
	@Resource
	private ApplicationContext applicationContext;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public void run() {
		try{
			if (applicationContext != null) {
				System.out.println(sdf.format(new Date()) + "-APPLICATIONINITTASK EXECUTE!");
				applicationContext.init();
			}
		}catch(Exception e){
			e.printStackTrace();			
		}
	}
}
