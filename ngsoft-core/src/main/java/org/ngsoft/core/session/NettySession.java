package org.ngsoft.core.session;

import io.netty.channel.ChannelHandlerContext;

import java.net.SocketAddress;

import org.ngsoft.core.message.IMessage;

/**
 * 
 * @author will
 *
 */
public class NettySession implements ISession{
	
	public NettySession(ChannelHandlerContext ctx){
		if(ctx == null){
			throw new NullPointerException("ctx can not be null");
		}
		this.handlerContext = ctx;
	}
	
	private ChannelHandlerContext handlerContext;

	@Override
	public void write(IMessage message) {
		handlerContext.write(message);
	}

	@Override
	public SocketAddress remoteAddress() {		
		return handlerContext.channel().remoteAddress();
	}

	@Override
	public void setAttribute(String key, Object obj) {
		
	}

	@Override
	public Object getAttribute(String key) {
		return null;
	}
	
}
