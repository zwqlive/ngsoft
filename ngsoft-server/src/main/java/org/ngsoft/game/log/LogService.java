package org.ngsoft.game.log;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ngsoft.game.log.entity.BaseLogEntity;

public class LogService {
	//参考size 10-20
	private ThreadPoolExecutor dbExecutor;
	//参考size 10-20
	private ThreadPoolExecutor fileExecutor;
	//参考size 8*10000
	private BlockingQueue<Runnable> dbLogEntityQueue; 
	//参考size 2*10000
	private BlockingQueue<Runnable> fileLogEntityQueue;
	//参考ComboPooledDataSource
	private DataSource ds;
	private List<String> stopEntityList;
	private static Logger logger = LogManager.getLogger(LogService.class);
	public AtomicInteger dbCount;
	public AtomicInteger fileCount;
	public AtomicLong lostCount;
	
	private static LogService instance = new LogService();
	private LogService(){
		//TODO:implement the constructor
	}
	
	public static LogService getInstance(){
		return instance;
	}
	
	public void log(BaseLogEntity logEntity){
		if(stopEntityList.contains(logEntity.getClass().getSimpleName().toUpperCase())){
			return;
		}
		int dbsize = dbExecutor.getQueue().size();
		int filesize = fileExecutor.getQueue().size();
		int file=0;
		long lostcount=0;
		if (dbsize <= 8 * 10000) {
			//写数据库 十万
			file = dbCount.get();
			dbExecutor.submit(new DbLogTask(ds,logEntity));
		} else if(filesize <= 2*10000) {
			//写文件2万
			file  = fileCount.getAndIncrement();
			fileExecutor.submit(new FileLogTask(logEntity));
			if(file!=0&&file%100==0){
				logger.info("executor(BaseLogBean) - filelogcount"+lostcount);
			}
		}else{
			//队列太长 丢掉
			lostcount = lostCount.getAndIncrement();
			logger.error("自启动开始共有"+lostcount+"条日志丢失");
			if(lostcount!=0&&lostcount%1000==0){
				logger.info("executor(BaseLogBean) - lostlogcount"+lostcount);
			}
		}
	}
	
	public void shutdown(){
		
	}
	
	public void checkTable(){
		
	}
}
