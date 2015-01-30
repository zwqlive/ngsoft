package org.ngsoft.script;

import org.ngsoft.core.script.IScriptEntry;
import org.ngsoft.core.script.ScriptManager;
import org.ngsoft.script.game.TestScript;

/**
 * 脚本类
 * 
 * @author will
 * @date 2015年1月25日 下午4:39:43
 *
 */
public class ScriptEntry implements IScriptEntry {
	
	@Override
	public void init() {
		ScriptManager.getInstance().registerScript(TestScript.getMe());
	}

}
