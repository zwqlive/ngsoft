package org.ngsoft.core.script;

/**
 * 脚本配置
 * 
 * @author will
 *
 */
public class ScriptConfig {
	// 脚本目录
	private String dir;
	// 入口类
	private String entryScript;
	// 脚本根目录
	private String scriptBaseDir;

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

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
