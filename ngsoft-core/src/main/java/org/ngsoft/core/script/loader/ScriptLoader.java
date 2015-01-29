package org.ngsoft.core.script.loader;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.ngsoft.core.script.IScript;
import org.ngsoft.core.script.ScriptConfig;
import org.ngsoft.core.script.ScriptLoadResult;


/**
 * 脚本加载类
 * 
 * @author will
 *
 */
public class ScriptLoader {
	
	private static Logger log = Logger.getLogger(ScriptLoader.class);
	
	private ScriptLoader(){}
	private static ScriptLoader instance = new ScriptLoader();
	public static ScriptLoader getLoader(){
		return instance;
	}
	
	private URLClassLoader classLoader;
	private ScriptConfig config;
	
	/**
	 * 加载所有脚本class
	 * 
	 * @param scriptConfig
	 */
	public ScriptLoadResult load(ScriptConfig scriptConfig){
		if(scriptConfig == null){
			throw new IllegalArgumentException("scriptconfig can not be null!");
		}
		this.config = scriptConfig;
		URL url = this.getClass().getClassLoader().getResource(config.getDir());
		log.info("script location:"+url);
		URL url2 = this.getClass().getProtectionDomain().getCodeSource().getLocation();
		log.info("current code source location:"+url2);
		URLClassLoader tempClassLoader = new URLClassLoader(new URL[]{url});
		ScriptLoadResult result = new ScriptLoadResult();
		try {
			//TODO:will@只需要加载入口类即可，入口类中引用其他所有脚本，会顺带加载，不需要循环加载一遍
			recuteLoadClass(tempClassLoader, new File(config.getDir()),"");
		} catch (Exception e) {
			log.error(e);
			result.setErrMsg(e.getMessage());
			result.setResultCode(1);
		}
		classLoader = tempClassLoader;
		result.setResultCode(0);
		return result;
	}
	
	/**
	 * 加载class类文件
	 * 
	 * @param file
	 * @param className
	 */
	private void recuteLoadClass(ClassLoader loader, File file, String className) throws Exception{
		try{
		if(file.isDirectory()){
			String currentPackagePrefix = "";
			if(className!=null && !className.isEmpty()){
				currentPackagePrefix=className+".";
			}
			for (File child : file.listFiles()) {
				String clsName = currentPackagePrefix + child.getName().replace(".class", "");
				recuteLoadClass(loader, child, clsName);
			}
			return;
		}		
		String fileName = file.getName();
		int lastIndex = fileName.lastIndexOf(".");
		if(lastIndex == -1){
			return;
		}
		String fileExt = fileName.substring(lastIndex);
		if(!".class".equals(fileExt)){
			return;
		}
		try {
			loader.loadClass(className);
		} catch (ClassNotFoundException e) {
			log.error("load class err. classname="+className,e);
		}
		}catch(Exception ex){
			throw new Exception("load class err. classname="+className+",msg:"+ex.getMessage(),ex);
		}
	}
	
	public void reload(){
		
	}
}
