package org.ngsoft.core.netty.handler;

import org.ngsoft.core.message.IMessage;
import org.ngsoft.core.session.ISession;

public interface IMessageHandler<T extends IMessage> {

	void handle(IMessage message);

	ISession session();

	IMessageHandler<T> session(ISession session);
}
