package org.ngsoft.core.netty.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import org.ngsoft.core.message.Message;

public abstract class MessageHandler<T extends Message> extends ChannelHandlerAdapter implements IMessageHandler<T>{

	@Override
	public abstract void handle(T message);
	
	
	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("read msg:"+msg.getClass().getName());
    }
}
