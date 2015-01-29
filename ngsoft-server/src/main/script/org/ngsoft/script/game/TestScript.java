package org.ngsoft.script.game;

import org.apache.log4j.Logger;
import org.ngsoft.game.test.script.ITestScript;

public class TestScript implements ITestScript {
	
	private static Logger log = Logger.getLogger(TestScript.class);
	
	@Override
	public void init() {
		
	}

	@Override
	public void testPrint() {
		System.out.println("script engine is work on !");
	}

}
