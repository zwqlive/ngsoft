package gamescript.entry;

import gamescript.game.test.TestScript;
import org.ngsoft.script.java.IScriptEntry;
import org.ngsoft.script.java.ScriptManager;

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
