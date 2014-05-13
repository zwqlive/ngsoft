package org.ngsoft.core.script.loader;

import java.io.File;
import java.net.URL;

import org.ngsoft.core.script.ScriptConfig;


/**
 * 脚本加载类
 * 
 * @author will
 *
 */
public class ScriptLoader {
	
	private ScriptLoader(){}
	private static ScriptLoader instance = new ScriptLoader();
	public static ScriptLoader getLoader(){
		return instance;
	}
	
	private ScriptClassLoader classLoader;
	private ScriptConfig config;
	
	/**
	 * 加载所有脚本class
	 * 
	 * @param scriptConfig
	 */
	public void load(ScriptConfig scriptConfig){
		if(scriptConfig == null){
			throw new IllegalArgumentException("scriptconfig can not be null!");
		}
		this.config = scriptConfig;
		URL url = this.getClass().getClassLoader().getResource(config.getDir());
		System.out.println(url);
		url = this.getClass().getProtectionDomain().getCodeSource().getLocation();
		System.out.println(url);
		classLoader = new ScriptClassLoader(new URL[]{this.getClass().getClassLoader().getResource(config.getDir())});
		recuteLoadClass(new File(config.getDir()),"");
	}
	
	/**
	 * 加载class类文件
	 * 
	 * @param file
	 * @param className
	 */
	private void recuteLoadClass(File file,String className){
		if(file.isDirectory()){
			String currentPackagePrefix = "";
			if(className!=null && !className.isEmpty()){
				currentPackagePrefix=className+".";
			}
			for (File child : file.listFiles()) {
				String clsName = currentPackagePrefix + child.getName().replace(".class", "");
				recuteLoadClass(child, clsName);
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
			classLoader.loadClass(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
