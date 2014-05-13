package org.ngsoft.core.netty.handler;

import io.netty.channel.ChannelHandlerAdapter;

import org.ngsoft.core.message.Message;

public abstract class MessageHandler<T extends Message> extends ChannelHandlerAdapter implements IMessageHandler<T>{

	@Override
	public abstract void handle(T message);
	
}
