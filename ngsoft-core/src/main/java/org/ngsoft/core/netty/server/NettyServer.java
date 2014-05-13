package org.ngsoft.core.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.ngsoft.core.netty.config.ServerConfig;
import org.ngsoft.core.netty.handler.ServerChannelHandler;

public class NettyServer {
	
	private ServerConfig serverConfig;
	
	
	public ServerConfig getServerConfig() {
		return serverConfig;
	}

	public void setServerConfig(ServerConfig serverConfig) {
		this.serverConfig = serverConfig;
	}

	public void start(){
		if(serverConfig == null){
			throw new NullPointerException("serverConfig");
		}
		ServerBootstrap bootstrap = new ServerBootstrap();
		EventLoopGroup parentGroup = new NioEventLoopGroup();
		EventLoopGroup childGroup = new NioEventLoopGroup();
		bootstrap.channel(NioServerSocketChannel.class)
		.group(parentGroup,childGroup).childHandler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new ServerChannelHandler());
			}
		}).option(ChannelOption.TCP_NODELAY, true)
		.childOption(ChannelOption.SO_KEEPALIVE, true);
		ChannelFuture cf = bootstrap.bind(serverConfig.getPort());
		try {
			cf.sync();
			cf.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			childGroup.shutdownGracefully();
			parentGroup.shutdownGracefully();
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		ServerConfig config = new ServerConfig();
		config.setPort(8030);
		NettyServer server = new NettyServer();
		server.setServerConfig(config);
		server.start();
	}
}
