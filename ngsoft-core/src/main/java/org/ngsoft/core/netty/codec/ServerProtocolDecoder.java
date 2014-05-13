package org.ngsoft.core.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.compression.ZlibCodecFactory;

import java.util.List;

import org.ngsoft.core.message.Message;
import org.ngsoft.core.netty.server.MessageRegistryService;

/**
 * 编码解析器
 * 
 * @author will
 *
 */
public class ServerProtocolDecoder extends ByteToMessageDecoder {
	
	public ServerProtocolDecoder(){
		decoder = new EmbeddedChannel(ZlibCodecFactory.newZlibEncoder(6));
	}
	
	private EmbeddedChannel decoder;
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if(in.capacity()==0){
			return;
		}
		int size = in.readInt();
		int msgId = in.readInt();
		int compress = (0xfffffff|size) >> 28;
		if(compress == 1){
			decoder.writeOutbound(in.retain());
		}else{
			Message message = MessageRegistryService.getMessage(msgId);
			message.read(in);
		}
		decoder.writeInbound(in);
	}
	
	public static void main(String[] args){
		int size = 200;
		int size2 = 0x10000000|size;
		int compress = (0xfffffff|size) >> 28;
		int compress2 = (0xfffffff|size2) >> 28;
		System.out.println(compress);
		System.out.println(compress2);
	}

}
