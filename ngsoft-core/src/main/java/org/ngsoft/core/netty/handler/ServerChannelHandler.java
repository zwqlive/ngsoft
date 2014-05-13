package org.ngsoft.core.netty.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ServerChannelHandler extends ChannelHandlerAdapter{
	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ctx.fireChannelRead(msg);
        ctx.channel().remoteAddress();
    }
}
