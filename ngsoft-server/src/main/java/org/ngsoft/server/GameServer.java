package org.ngsoft.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import org.ngsoft.core.netty.codec.ServerProtocolFactory;
import org.ngsoft.server.handler.GameServerSimpleHandler;

public class GameServer {
	 private final int port;

	    public GameServer(int port) {
	        this.port = port;
	        serverBootstrap = new ServerBootstrap();
	    }

	    public void start() throws Exception {
	        
	    	EventLoopGroup bossGroup = new NioEventLoopGroup();
	        EventLoopGroup workerGroup = new NioEventLoopGroup();
	        try {
	            serverBootstrap.group(bossGroup, workerGroup)
	             .channel(NioServerSocketChannel.class)
	             .option(ChannelOption.TCP_NODELAY, true)
	             .option(ChannelOption.SO_BACKLOG, 100)
	             .option(ChannelOption.SO_KEEPALIVE, true)
	             .option(ChannelOption.SO_TIMEOUT, 3000)
	             .handler(new LoggingHandler(LogLevel.INFO))
	             .childHandler(new ChannelInitializer<SocketChannel>() {
	                 @Override
	                 public void initChannel(SocketChannel ch) throws Exception {
	                	 ch.pipeline().addLast(ServerProtocolFactory.getDecoder());
	                	 ch.pipeline().addLast(ServerProtocolFactory.getEncoder());
	                     ch.pipeline().addLast(new GameServerSimpleHandler());
	                 }
	             });

	            ChannelFuture f = serverBootstrap.bind(port).sync();
	            f.channel().closeFuture().sync();
	        }catch(InterruptedException ex){
	        	
	        } finally {
	            bossGroup.shutdownGracefully();
	            workerGroup.shutdownGracefully();
	        }
	    }
	    
	    private ServerBootstrap serverBootstrap;
	    
	    public ServerBootstrap bootstrap(){
	    	if(null == serverBootstrap){
	    		serverBootstrap = new ServerBootstrap();
	    	}
	    	return serverBootstrap;
	    }
	    

	    public static void main(String[] args) throws Exception {
	        int port=8020;	        
	        new GameServer(port).start();
	    }
}
