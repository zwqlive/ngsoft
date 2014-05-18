package org.ngsoft.core.netty.handler;

import org.ngsoft.core.message.IMessage;
import org.ngsoft.core.session.ISession;

public abstract class MessageHandler<T extends IMessage> implements IMessageHandler<T>{
	
	ISession session;
		
	public <M extends IMessage> void sendMessage(M message){
		session.write(message);
	}

	public ISession session() {
		return session;
	}

	public IMessageHandler<T> session(ISession session) {
		this.session = session;
		return this;
	}

}
