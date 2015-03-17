package org.ngsoft.core.netty.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import org.ngsoft.core.common.Loggers;
import org.ngsoft.core.message.IMessage;
import org.ngsoft.core.service.MessageRegistryService;
import org.ngsoft.core.session.ISession;
import org.ngsoft.core.session.NettySession;
import org.slf4j.Logger;

public class ChannelMessageHandler extends ChannelHandlerAdapter{
	
	private static Logger logger = Loggers.MSG_LOGGER;
	
	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if(msg instanceof IMessage){
    		IMessage message = (IMessage)msg;
			IMessageHandler<? extends IMessage> handler = MessageRegistryService.getHandler(message.getClass());
    		if(handler!=null){
    			ISession session = new NettySession(ctx);
    			handler.session(session).handle(message);
    		}
    	}else{
    		logger.error("can not recorgnize message");
    	}
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    	ctx.flush();     
    }
}
