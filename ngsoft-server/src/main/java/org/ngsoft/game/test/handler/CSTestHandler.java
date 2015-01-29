package org.ngsoft.game.test.handler;

import org.ngsoft.core.netty.handler.MessageHandler;
import org.ngsoft.game.test.message.CSTestMessage;
import org.ngsoft.game.test.message.SCTestMessage;
import org.ngsoft.game.test.transobj.SomeInfo;

public class CSTestHandler extends MessageHandler<CSTestMessage> {

	@Override
	public void handle(CSTestMessage message) {
		if (message != null) {
			System.out.println("playerId:" + message.getPlayerId());
			SCTestMessage scMsg = new SCTestMessage();
			scMsg.setName("haha");
			scMsg.setSendRoleId(message.getPlayerId());
			scMsg.setAge(22);
			SomeInfo someInfo = new SomeInfo();
			someInfo.setId(12);
			someInfo.setName("someInfoName");
			scMsg.setSomeInfo(someInfo);
			sendMessage(scMsg);
			
		}
	}

	

}
