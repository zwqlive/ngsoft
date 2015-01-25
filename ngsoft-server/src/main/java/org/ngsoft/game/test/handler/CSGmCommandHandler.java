package org.ngsoft.game.test.handler;

import org.apache.log4j.Logger;
import org.ngsoft.core.netty.handler.MessageHandler;
import org.ngsoft.game.test.message.CSGmCommandMessage;

public class CSGmCommandHandler extends MessageHandler<CSGmCommandMessage> {
	
	private static Logger log = Logger.getLogger(CSGmCommandHandler.class);
	
	@Override
	public void handle(CSGmCommandMessage message) {
		if (message != null) {
			String gmCmd = message.getCommand();
			
		}
	}

	

}
