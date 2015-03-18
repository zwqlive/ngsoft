package org.ngsoft.core.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.ngsoft.core.action.IAction;
import org.ngsoft.core.common.Loggers;
import org.ngsoft.core.message.IMessage;
import org.ngsoft.core.netty.config.ServerConfig;
import org.ngsoft.core.netty.handler.NettyServerMessageHandler;
import org.ngsoft.core.handler.IMessageDispatcher;
import org.ngsoft.core.handler.ServerAction;
import org.ngsoft.core.server.IServer;
import org.ngsoft.core.server.thread.ServerThread;
import org.slf4j.Logger;

public class NettyServer implements IServer{
	
	private static Logger logger = Loggers.MSG_LOGGER;
	
	private ServerConfig serverConfig;
	//服务器主线程
	private ServerThread serverMainThread;
    //消息分发器
    private IMessageDispatcher messageDispatcher;
	
	public NettyServer(){
		serverMainThread = new ServerThread(this.getClass().getSimpleName());
	}
	
	public ServerConfig getServerConfig() {
		return serverConfig;
	}

	public void setServerConfig(ServerConfig serverConfig) {
		this.serverConfig = serverConfig;
	}

    @Override
    public void init() {

    }

    public void start(){
		if(serverConfig == null){
			throw new NullPointerException("serverConfig");
		}
        NettyServer server = this;
		//启动主线程
		serverMainThread.start();
		//监听链接
		ServerBootstrap bootstrap = new ServerBootstrap();
		EventLoopGroup parentGroup = new NioEventLoopGroup();
		EventLoopGroup childGroup = new NioEventLoopGroup();
		bootstrap.channel(NioServerSocketChannel.class)
		.group(parentGroup,childGroup).childHandler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new NettyServerMessageHandler(server));
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
			logger.error("server start failed!",e);
		}
	}

    @Override
    public void stop() {
        serverMainThread.stop(false);
    }

    @Override
    public void resolve(IMessage message) {
        IAction action = new ServerAction(message);
        if(messageDispatcher!=null){
            if(messageDispatcher.dispatch(action))
                return;
        }
        serverMainThread.addAction(action);
    }
}
