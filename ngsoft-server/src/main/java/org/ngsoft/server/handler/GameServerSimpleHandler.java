package org.ngsoft.server.handler;

import io.netty.channel.ChannelHandlerContext;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ngsoft.core.message.Message;
import org.ngsoft.core.netty.handler.MessageHandler;
import org.ngsoft.server.message.CSTestMessage;
import org.ngsoft.server.message.SCTestMessage;

public class GameServerSimpleHandler extends MessageHandler<CSTestMessage> {

    private static final Logger logger = LogManager.getLogger(
    		GameServerSimpleHandler.class);

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
    	ctx.flush();
        System.out.println("server read complete");        
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        logger.log(Level.WARN, "Unexpected exception from downstream.", cause);
        ctx.close();
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        SCTestMessage scMsg = new SCTestMessage();
        scMsg.setName("haha");
        scMsg.setSendRoleId(10000000L);
        scMsg.setAge(22);
        ctx.write(scMsg);
        ctx.flush();
    }

	@Override
	public void handle(CSTestMessage message) {
		// TODO Auto-generated method stub
		
	}

}
