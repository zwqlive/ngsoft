package org.ngsoft.core.script.loader;

import java.net.URL;
import java.net.URLClassLoader;

import org.apache.log4j.Logger;

/**
 * 
 * @author will
 * @date 2015年1月25日 下午2:44:16
 *
 */
public class ScriptClassLoader extends URLClassLoader {
	
	private static Logger log = Logger.getLogger(ScriptClassLoader.class);
	
	public ScriptClassLoader(URL[] urls) {
		super(urls);
	}
	

	@Override
	public Class<?> loadClass(String name, boolean resolve) {
		Class<?> clasz = super.findLoadedClass(name);
		if (clasz == null) {
			try {
				clasz = super.findSystemClass(name);
			} catch (ClassNotFoundException e) {
				log.error("load class err!",e);
			}
		}
		if (clasz == null) {
			try {
				clasz = super.findSystemClass(name);
			} catch (ClassNotFoundException e) {
				log.error("load class err!",e);
			}
		}
		if (clasz == null) {
			try {
				clasz = super.findClass(name);
			} catch (ClassNotFoundException e) {
				log.error("load class err!",e);
			}
		}
		if (resolve) {
			resolveClass(clasz);
		}
		return clasz;
	}
}
