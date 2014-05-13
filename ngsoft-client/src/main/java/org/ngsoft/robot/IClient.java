package org.ngsoft.robot;

import io.netty.channel.ChannelHandlerContext;


public interface IClient {
	
	ChannelHandlerContext context();
	
	IClient connect(String host,int port);

	void disconnect();

}
