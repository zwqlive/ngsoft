package org.ngsoft.core.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.compression.ZlibCodecFactory;

import org.ngsoft.core.common.Loggers;
import org.ngsoft.core.message.IMessage;
import org.ngsoft.core.message.Message;

public class ServerProtocolEncoder extends MessageToByteEncoder<IMessage>{
	
	public ServerProtocolEncoder(){
		encoder = new EmbeddedChannel(ZlibCodecFactory.newZlibEncoder(6));
	}
	
	private EmbeddedChannel encoder;
	private int maxSize=1024;
	//一般512
	private int zipSize=512;
	
	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public int getZipSize() {
		return zipSize;
	}

	public void setZipSize(int zipSize) {
		this.zipSize = zipSize;
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, IMessage msg, ByteBuf out) throws Exception {
		if(msg instanceof Message){
			ByteBuf in = ctx.alloc().ioBuffer();
			msg.write(in);
			int size = in.readableBytes();
			if(size>maxSize){
				ctx.disconnect();
				Loggers.MSG_LOGGER.error("发送的消息数据量过大，msgId="+msg.getId());
				return;
			}
			out.writeInt(msg.getId());
			if(size>zipSize){
				//压缩消息
				size = 0x10000000|size;
				out.writeInt(size);
				encoder.writeOutbound(in.retain());
			}else{
				out.writeInt(size);
				out.writeBytes(in);
			}
//			out.discardReadBytes();
			in.release();
		}		
	}

}
