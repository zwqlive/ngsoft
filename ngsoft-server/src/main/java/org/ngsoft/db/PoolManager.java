package org.ngsoft.db;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.script.ScriptException;

import org.ngsoft.common.config.ConfigUtil;
import org.ngsoft.db.config.DBConfig;

/**
 * 数据连接池管理器
 * 
 * @author Will
 *
 */
public class PoolManager {
	private Vector<DBConfig> dbConfigs = new Vector<DBConfig>();
	private Map<String,ConnectionPool> pools = new HashMap<String,ConnectionPool>();
	private static PoolManager instance= new PoolManager();
	public static PoolManager getInstance(){
		return instance;
	}
	private PoolManager(){}
	
	
	public void initPool(List<String> dbConfigNames) throws IOException, ScriptException{
		loadDrivers(dbConfigNames);
		for(DBConfig config : dbConfigs){
			CreatePool(config);
		}
	}
	
	public void loadDrivers(List<String> configFileNames) throws IOException, ScriptException{
		for(String fileName : configFileNames){
			DBConfig dbConfig = new DBConfig();
			ConfigUtil.loadJsConfig(dbConfig, fileName);
			dbConfigs.add(dbConfig);
		}
	}
	
	private void CreatePool(DBConfig dbConfig){
		ConnectionPool pool = new ConnectionPool(dbConfig);
		pools.put(dbConfig.getPoolName(), pool);
	}
	
	/**
	 * 从连接池中获取数据库连接
	 * @param poolName
	 * @return
	 */
	public Connection getConnection(String poolName){
		ConnectionPool pool = pools.get(poolName);
		if(pool==null){
			return null;
		}
		return pool.getConnection();
	}
	
	/**
	 * 连接池回收数据库连接
	 * @param poolName
	 * @param conn
	 */
	public void freeConnection(String poolName,Connection conn){
		ConnectionPool pool = pools.get(poolName);
		if(pool!=null){
			pool.freeConnection(conn);
		}
	}
}
