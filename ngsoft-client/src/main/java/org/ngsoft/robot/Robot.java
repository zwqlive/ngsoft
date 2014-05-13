package org.ngsoft.robot;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import org.ngsoft.core.netty.codec.ServerProtocolFactory;
import org.ngsoft.robot.handler.ClientHandler;

/**
 * 机器人客户端
 * 
 * @author will
 * 
 */
public class Robot implements IClient {
	private Bootstrap bootstrap;
	private volatile boolean isConnected=false;
	private ChannelHandlerContext context;
	private ClientHandler clientHandler;
	public Robot(){
		bootstrap = new Bootstrap();
		clientHandler = new ClientHandler();
	}
	@Override
	public IClient connect(String host, int port) {
		if(isConnected){
			throw new IllegalStateException("robot is already connected,please disconnect is first");
		}
		EventLoopGroup group = new NioEventLoopGroup();
		bootstrap.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<Channel>() {

			@Override
			protected void initChannel(Channel ch) throws Exception {
				ch.pipeline().addFirst(ServerProtocolFactory.getEncoder());
				ch.pipeline().addFirst(ServerProtocolFactory.getDecoder());
				ch.pipeline().addLast(clientHandler);
			}
		});
		ChannelFuture cf = bootstrap.connect(host, port);
		try {
			cf.sync();
			context = cf.channel().pipeline().context(clientHandler);
			isConnected=true;
//			cf.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			group.shutdownGracefully();
		}
		return this;
	}

	@Override
	public void disconnect() {
		if(bootstrap==null){
			return;
		}
//		bootstrap.
		isConnected = false;
		
	}
	@Override
	public ChannelHandlerContext context() {
		return context;
	}

}
