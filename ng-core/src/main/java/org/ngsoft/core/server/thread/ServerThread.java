package org.ngsoft.core.server.thread;

import java.util.Collection;
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
    private static final String thread_name_prefix="ServerThread-";
	
	/**
	 * 等待执行的动作队列
	 */
	private LinkedBlockingQueue<IAction> actionQueue = new LinkedBlockingQueue<IAction>();
	
	//心跳间隔
	private int heart=0;
	private String threadName;
	private ServerTimerTask timerTask;
    private volatile boolean running = false;

    public ServerThread(){
        this("Main");
    }

    /**
     * 服务器线程构造器
     * @param threadName 线程名称
     */
    public ServerThread(String threadName){
        this(threadName, 50);
    }

    /**
     * 服务器线程构造器
     * @param threadName 线程名称
     * @param heart 心跳间隔
     */
	public ServerThread(String threadName, int heart){
		super(threadName);
		this.threadName = thread_name_prefix+threadName;
		this.heart = heart;
		timerTask = new ServerTimerTask(this);
		Logger.getLogger(this.threadName);
	}

    @Override
    public void start(){
        running=true;
        super.start();
    }
	
	@Override
	public void run(){
		try{
			while(running){
                //do work
                IAction action = actionQueue.poll();
                if(action==null){
                    wait();
                }
            }
		}catch(Exception ex){
			log.error("server thread occured error!",ex);
		}
	}

    /**
     * 判断服务器线程是否已经停止
     * @return
     */
    public boolean isStoped(){
        return !running;
    }


    /**
     * 停止服务器线程
     * @param force 是否强制停止，为true时会立即中段线程的执行，为false则不会再接受任务，直到当前任务执行完之后停止线程
     */
    public void stop(boolean force){
        if(force){
            stopNow();
        }else{
            running=false;
            notify();
        }
    }

    /**
     * 停止任务的执行
     */
    private void stopNow(){
        running=false;
        this.interrupt();
        actionQueue.clear();
        notify();
    }


    /**
     * 添加任务
     * @param action
     */
    public void addAction(IAction action){
        assert running : this.threadName+"is not running";
        actionQueue.add(action);
        notify();
    }

    /**
     * 添加任务的集合
     * @param actions
     */
    public void addActions(Collection<IAction> actions){
        assert running : this.threadName+"is not running";
        actionQueue.addAll(actions);
        notify();
    }
	
}
