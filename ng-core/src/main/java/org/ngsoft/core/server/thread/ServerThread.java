package org.ngsoft.core.server.thread;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;
import org.ngsoft.core.action.IAction;
import org.ngsoft.core.server.ServerTimerTask;

/**
 * 服务器线程
 * @author Administrator
 *
 */
public class ServerThread extends Thread {
	
	private Logger log = null;
	
	/**
	 * 等待执行的动作队列
	 */
	private LinkedBlockingQueue<IAction> actionQueue = new LinkedBlockingQueue<IAction>();
	
	//心跳间隔
	private int heart=50;
	private String threadName;
	private ServerTimerTask timerTask;
	
	public ServerThread(String threadName, int heart){
		super(threadName);
		this.threadName = threadName;
		this.heart = heart;
		timerTask = new ServerTimerTask(this);
		Logger.getLogger("ServerThread-"+threadName);
	}
	
	@Override
	public void run(){
		try{
			
		}catch(Exception ex){
			log.error("server thread occured error!",ex);
		}
	}
	
	
}
