package org.ngsoft.core.netty.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import org.ngsoft.core.common.Loggers;
import org.ngsoft.core.handler.IServerMessageHandler;
import org.ngsoft.core.message.IMessage;
import org.ngsoft.core.message.IMessageResolver;
import org.ngsoft.core.server.IServer;
import org.slf4j.Logger;

public class NettyServerMessageHandler extends ChannelHandlerAdapter implements IServerMessageHandler{

    private static Logger logger = Loggers.MSG_LOGGER;
    private IMessageResolver resolver;


    public NettyServerMessageHandler(IMessageResolver resolver){
        this.resolver = resolver;
    }

	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if(msg instanceof IMessage){
    		IMessage message = (IMessage)msg;
            onMessageReceive(message);
    	}else{
    		logger.error("can not recognize message");
    	}
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    	ctx.flush();     
    }

    @Override
    public void onMessageReceive(IMessage message) {
        resolver.resolve(message);
    }
}
