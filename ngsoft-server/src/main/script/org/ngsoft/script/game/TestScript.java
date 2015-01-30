package org.ngsoft.script.game;

import org.apache.log4j.Logger;
import org.ngsoft.game.test.script.ITestScript;

public class TestScript implements ITestScript {
	
	private static Logger log = Logger.getLogger(TestScript.class);

	private static TestScript me;
	
	public static TestScript getMe(){
		if(me==null){
			me = new TestScript();
		}
		return me;
	}
	
	@Override
	public void testPrint() {
		System.out.println("script engine is work on ! version:2");
	}

}
