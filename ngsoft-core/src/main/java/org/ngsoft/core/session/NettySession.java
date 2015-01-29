package org.ngsoft.core.session;

import io.netty.channel.ChannelHandlerContext;

import java.net.SocketAddress;
import java.util.concurrent.ConcurrentHashMap;

import org.ngsoft.core.message.IMessage;

/**
 * 
 * @author will
 *
 */
public class NettySession implements ISession{
	
	private ConcurrentHashMap<String, Object> attributes = new ConcurrentHashMap<String, Object>();
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
	public void setAttribute(String key, Object value) {
		attributes.put(key, value);
	}

	@Override
	public Object getAttribute(String key) {
		return attributes.get(key);
	}

	@Override
	public void close() {
		handlerContext.disconnect();
	}
	
}
