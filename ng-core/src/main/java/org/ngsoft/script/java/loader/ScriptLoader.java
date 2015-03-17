package org.ngsoft.script.java.loader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.ngsoft.script.java.ScriptConfig;
import org.ngsoft.script.java.ScriptLoadResult;
import org.ngsoft.script.java.ScriptConfig;
import org.ngsoft.script.java.ScriptLoadResult;

/**
 * 脚本加载类
 *
 * @author will
 */
public class ScriptLoader {

    private static Logger log = Logger.getLogger(ScriptLoader.class);

    private URLClassLoader classLoader = null;

    /**
     * 加载所有脚本class
     *
     * @param scriptConfig
     */
    public ScriptLoadResult load(ScriptConfig scriptConfig) {
        String entryScript = scriptConfig.getEntryScript();
        String baseScriptDir = scriptConfig.getScriptBaseDir();
        if (entryScript == null || entryScript.isEmpty() || entryScript.length()<6) {
            throw new IllegalArgumentException("scriptConfig.entryScript is illegal, can not be null and length must be greater than 6!");
        }
        String scriptDir = baseScriptDir+"/"+entryScript.replace(".", "/")+".class";
        if (scriptDir == null || scriptDir.isEmpty()) {
            throw new IllegalArgumentException("scriptConfig.entryScript is illegal!");
        }
        URL url = this.getClass().getClassLoader().getResource("");
        ClassLoader mainLoader = this.getClass().getClassLoader();
        URL scriptUrl = null;
        ScriptLoadResult result = new ScriptLoadResult();
        try {
            scriptUrl = new File(baseScriptDir).toURI().toURL();
            URL[] urls = new URL[]{url, scriptUrl};
            if (mainLoader instanceof URLClassLoader) {
                urls = ((URLClassLoader) mainLoader).getURLs();
                List<URL> urlList = new ArrayList<URL>();
                urlList.addAll(Arrays.asList(urls));
                urlList.add(scriptUrl);
                urls = urlList.toArray(urls);
            }
            log.info("script location:" + url);
            // URL url2 =
            // this.getClass().getProtectionDomain().getCodeSource().getLocation();
            // log.info("current code source location:"+url2);
            URLClassLoader tempClassLoader = new ScriptClassLoader(urls);

            try {
                // TODO:will@只需要加载入口类即可，入口类中引用其他所有脚本，会顺带加载，不需要循环加载一遍
                recuteLoadClass(tempClassLoader, new File(scriptDir),
                        entryScript);
            } catch (Exception e) {
                log.error(e);
                result.setErrMsg(e.getMessage());
                result.setResultCode(1);
            }
            classLoader = tempClassLoader;
            result.setResultCode(0);
        } catch (MalformedURLException e1) {
            log.error("load script err!",e1);
        }
        return result;
    }

    /**
     * 加载class类文件
     *
     * @param file
     * @param className
     */
    private void recuteLoadClass(ClassLoader loader, File file, String className)
            throws Exception {
        try {
            if (file == null) {
                return;
            }
            if (!file.exists()) {
                System.out.println(file.getAbsolutePath());
                return;
            }
            if (file.isDirectory()) {
                String currentPackagePrefix = "";
                if (className != null && !className.isEmpty()) {
                    currentPackagePrefix = className + ".";
                }
                for (File child : file.listFiles()) {
                    String clsName = currentPackagePrefix
                            + child.getName().replace(".class", "");
                    recuteLoadClass(loader, child, clsName);
                }
                return;
            }

            String fileName = file.getName();
            int lastIndex = fileName.lastIndexOf(".");
            if (lastIndex == -1) {
                return;
            }
            String fileExt = fileName.substring(lastIndex);
            if (!".class".equals(fileExt)) {
                return;
            }
            try {
                Class<?> loadClass = loader.loadClass(className);
                if (loadClass == null) {
                    // 出错了
                    throw new RuntimeException("load script class err!");
                }
            } catch (ClassNotFoundException e) {
                log.error("load class err. classname=" + className, e);
                throw new Exception("load class err. classname=" + className
                        + ",msg:" + e.getMessage(), e);
            }
        } catch (Exception ex) {
            throw new Exception("load class err. classname=" + className
                    + ",msg:" + ex.getMessage(), ex);
        }
    }
}
