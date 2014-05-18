package org.ngsoft.core.netty.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import org.ngsoft.core.message.IMessage;
import org.ngsoft.core.service.MessageRegistryService;
import org.ngsoft.core.session.ISession;
import org.ngsoft.core.session.NettySession;

public class ChannelMessageHandler extends ChannelHandlerAdapter{
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if(msg instanceof IMessage){
    		IMessage message = (IMessage)msg;
			IMessageHandler handler = MessageRegistryService.getHandler(message.getId());
    		if(handler!=null){
    			ISession session = new NettySession(ctx);
    			handler.session(session).handle(message);
    		}
    	}else{
    		System.out.println("can not recorgnize message");
    	}
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    	ctx.flush();     
    }
}
