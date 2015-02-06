package org.ngsoft.robot.handler;

import org.ngsoft.core.netty.handler.MessageHandler;
import org.ngsoft.robot.message.SCTestMessage;

public class SCTestHandler extends MessageHandler<SCTestMessage>{

	@Override
	public void doHandle(SCTestMessage message) {
		System.out.println("name:" + message.getName());
		System.out.println("age:" + message.getAge());
		System.out.println("someInfo:"+message.getSomeInfo());
	}

}
