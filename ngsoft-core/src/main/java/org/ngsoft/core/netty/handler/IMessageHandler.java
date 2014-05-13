package org.ngsoft.core.netty.handler;

import org.ngsoft.core.message.IMessage;

public interface IMessageHandler<T extends IMessage> {
	
	void handle(T message);
}
