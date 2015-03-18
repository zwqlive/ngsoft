package org.ngsoft.robot;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.ngsoft.core.message.IMessageResolver;


public interface IClient extends IMessageResolver {
	
	ChannelHandlerContext context();
	
	Channel channel();
	
	IClient connect(String host,int port);

	void disconnect();

}
