package org.ngsoft.core.script.loader;

import java.net.URL;
import java.net.URLClassLoader;

public class ScriptClassLoader extends URLClassLoader {
	
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
				
			}
		}
		if (clasz == null) {
			try {
				clasz = super.findSystemClass(name);
			} catch (ClassNotFoundException e) {
				
			}
		}
		if (clasz == null) {
			try {
//				String path = name.replace('.', '/').concat(".class");
//				System.out.println(path);
//				path = "script_classes/serverscript/ScriptEntry.class";
//				File entryFile = new File(path);
//				try {
//					InputStreamReader reader = new InputStreamReader(new FileInputStream(entryFile));
//					BufferedReader bufferReader = new BufferedReader(reader);
//					System.out.println(reader);
//					try {
//						int index = 0;
//						while((index = bufferReader.read())>0){
//							System.out.println(bufferReader.readLine());
//						}
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				} catch (FileNotFoundException e) {					
//					e.printStackTrace();
//				}
//				URL res = super.findResource(path);
//				System.out.println(res);
				clasz = super.findClass(name);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		if (resolve) {
			resolveClass(clasz);
		}
		return clasz;
	}
}
