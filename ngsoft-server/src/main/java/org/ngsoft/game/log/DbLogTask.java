package org.ngsoft.game.log;

import javax.sql.DataSource;

import org.ngsoft.game.log.entity.BaseLogEntity;

public class DbLogTask implements Runnable{
	private BaseLogEntity logEntity;
	private DataSource ds;
	
	public DbLogTask(DataSource ds, BaseLogEntity logEntity){
		this.ds = ds;
		this.logEntity = logEntity;
	}

	public void run() {
		// TODO Auto-generated method stub
		
	}

}
