package com.yiting.push.handler.process;

import com.yiting.push.handler.context.ApplicationContext;

/**
 * Created by Administrator on 2015/4/24.
 */
public abstract class AbstractHandleProcessor<T> implements  IHandleProcessor {
	private Object processObject;
	protected ApplicationContext applicationContext=ApplicationContext.GetInstance();

	public T getProcessObject(){
		return (T)processObject;
	}

	public void setProcessObject(Object processObject){
		this.processObject=null;
		this.processObject=processObject;
	}

	@Override
	public void updateObject(Object t) {
		setProcessObject(t);
	}
}
