package org.ngsoft.core.server;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 心跳事件
 * @author Administrator
 *
 */
public class ServerTimerManager {
	
	ScheduledExecutorService scheduledThreadPool = null;
	
	private ServerTimerManager(){
		scheduledThreadPool = Executors.newScheduledThreadPool(1);
	}
	
	private static ServerTimerManager instance;
	public static ServerTimerManager getInstance(){
		if(instance == null){
			instance = new ServerTimerManager();
		}
		return instance;
	}
	
	public void addTask(Runnable task, long delay){
		scheduledThreadPool.schedule(task, delay, TimeUnit.MILLISECONDS);
	}
}
