package org.ngsoft.server.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ngsoft.core.message.IMessage;
import org.ngsoft.core.netty.handler.IMessageHandler;
import org.ngsoft.core.service.MessageRegistryService;

public class GameServerSimpleHandler extends ChannelHandlerAdapter {

    private static final Logger logger = LogManager.getLogger(
    		GameServerSimpleHandler.class);
   
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if(msg instanceof IMessage){
    		IMessage message = (IMessage)msg;
    		IMessageHandler<? extends IMessage> handler = MessageRegistryService.getHandler(message.getClass());
    		if(handler!=null){
    			handler.handle(message);
    		}
    	}else{
    		System.out.println("can not recorgnize message");
    	}
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    	ctx.flush();     
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        logger.log(Level.WARN, "Unexpected exception from downstream.", cause);
        ctx.close();
    }
}
