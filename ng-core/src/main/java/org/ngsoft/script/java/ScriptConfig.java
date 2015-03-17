package org.ngsoft.script.java;

/**
 * 脚本配置
 * 
 * @author will
 *
 */
public class ScriptConfig {

	// 入口类
	private String entryScript;
	// 脚本根目录
	private String scriptBaseDir;

	public String getEntryScript() {
		return entryScript;
	}

	public void setEntryScript(String entryScript) {
		this.entryScript = entryScript;
	}

	public String getScriptBaseDir() {
		return scriptBaseDir;
	}

	public void setScriptBaseDir(String scriptBaseDir) {
		this.scriptBaseDir = scriptBaseDir;
	}

}
