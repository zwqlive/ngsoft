package org.ngsoft.script.java;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.ngsoft.script.java.loader.ScriptLoader;
import org.ngsoft.script.java.loader.ScriptLoader;

/**
 * 脚本管理器
 * TODO:will@可以考虑使用spring的ioc来管理
 * 
 * @author will
 * @date 2015年1月25日 下午10:05:05
 *
 */
public class ScriptManager {
	
	private ConcurrentHashMap<Class<?>, List<IScript>> scripts = new ConcurrentHashMap<Class<?>, List<IScript>>();
	
	private ScriptConfig config;
	
	private ScriptLoader loader = new ScriptLoader();
	
	private ScriptManager(){
		
	}
	
	private static ScriptManager instance = new ScriptManager();
	
	public static ScriptManager getInstance(){
		return instance;
	}

	public ScriptConfig getConfig() {
		return config;
	}



	public void setConfig(ScriptConfig config) {
		this.config = config;
	}



	public void initScripts(){
		clear();
		loader.load(config);
	}
	
	public void clear(){
		scripts.clear();
	}
	
	/**
	 * 注册脚本
	 * 
	 * @param script
	 */
	public void registerScript(IScript script){
		//TODO:will@防止多次注册
		Class<?>[] interfaces = script.getClass().getInterfaces();
		for(Class<?> clasz : interfaces){
			if(!scripts.containsKey(clasz)){
				scripts.put(clasz, new ArrayList<IScript>());
			}
			scripts.get(clasz).add(script);
		}
	}
	
	/**
	 * 获取收个实现类
	 * @param clasz
	 * @return
	 */
	public IScript getHeadScript(Class<? extends IScript> clasz){
		if(!scripts.containsKey(clasz)){
			return null;
		}
		List<IScript> list = scripts.get(clasz);
		if(list==null || list.isEmpty()){
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * 获取多个实现类
	 * 
	 * @param clasz
	 * @return
	 */
	public List<IScript> getScripts(Class<? extends IScript> clasz){
		if(!scripts.containsKey(clasz)){
			return null;
		}
		List<IScript> list = scripts.get(clasz);
		return list;
	}
}
