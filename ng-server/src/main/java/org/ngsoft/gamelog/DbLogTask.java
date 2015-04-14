package org.ngsoft.gamelog;

import javax.sql.DataSource;

import org.ngsoft.gamelog.entity.BaseLogEntity;

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
