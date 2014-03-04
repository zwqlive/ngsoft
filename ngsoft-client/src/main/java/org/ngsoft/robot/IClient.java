package org.ngsoft.robot;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;


public interface IClient {
	
	ChannelHandlerContext context();
	Channel channel();
	
	IClient connect(String host,int port);

	void disconnect();

}
