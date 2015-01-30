package org.ngsoft.game.test.handler;

import org.apache.log4j.Logger;
import org.ngsoft.core.netty.handler.MessageHandler;
import org.ngsoft.core.script.IScript;
import org.ngsoft.core.script.ScriptManager;
import org.ngsoft.game.test.message.CSGmCommandMessage;
import org.ngsoft.game.test.script.ITestScript;

public class CSGmCommandHandler extends MessageHandler<CSGmCommandMessage> {
	
	private static Logger log = Logger.getLogger(CSGmCommandHandler.class);
	
	@Override
	public void handle(CSGmCommandMessage message) {
		if (message != null) {
			String gmCmd = message.getCommand();
			if("scriptTest".equalsIgnoreCase(gmCmd)){
				IScript headScript = ScriptManager.getInstance().getHeadScript(ITestScript.class);
				if(headScript!=null && headScript instanceof ITestScript){
					ITestScript test = (ITestScript)headScript;
					test.testPrint();
				}
			}else if("refresh".equalsIgnoreCase(gmCmd)){
				ScriptManager.getInstance().initScripts();
			}
		}
	}

	

}
