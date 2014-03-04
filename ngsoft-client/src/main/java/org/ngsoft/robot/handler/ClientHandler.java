package org.ngsoft.robot.handler;

import io.netty.channel.ChannelHandlerContext;

import org.ngsoft.core.message.Message;
import org.ngsoft.core.netty.handler.MessageHandler;
import org.ngsoft.robot.message.CSTestMessage;
import org.ngsoft.robot.message.SCTestMessage;

public class ClientHandler extends MessageHandler<SCTestMessage>{
	
	@Override
    public void channelActive(ChannelHandlerContext ctx) {
        CSTestMessage msg = new CSTestMessage();
        msg.setPlayerId(1000000L);
        ctx.write(msg);
        ctx.flush();
    }
	
	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msgs) throws Exception {
//        System.out.println("server channel read");
//    	ByteBuf msg = (ByteBuf)msgs;
//    	ctx.write(msg.toString(Charset.defaultCharset()));
    	if(msgs instanceof Message){
    		Message msg = (Message)msgs;
    		System.out.println(msg.toString());
    	}else{
    		System.out.println("can not recorgnize message");
    	}
    }
	@Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("client read complete");
		ctx.flush();
    }
	

	@Override
	public void handle(SCTestMessage message) {
		// TODO Auto-generated method stub
		
	}

}
