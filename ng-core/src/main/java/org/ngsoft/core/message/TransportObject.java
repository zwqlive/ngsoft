package org.ngsoft.core.message;

import io.netty.buffer.ByteBuf;

import java.io.UnsupportedEncodingException;

import org.ngsoft.core.common.Loggers;

/**
 * 消息传输对象
 * 
 * @author will
 * 
 */
public abstract class TransportObject implements ITransportable {
	
	private static final int NULL_OBJECT_FLAG=-1;
	private static final int NOT_NULL_OBJECT_FLAG=-2;
	private static final String STRING_CHARSET="UTF-8";
	
	public abstract void write(ByteBuf byteBuf);
	
	public abstract void read(ByteBuf byteBuf);

	public void writeByte(ByteBuf byteBuf, byte value){
		byteBuf.writeByte(value);
	}
	
	public void writeShort(ByteBuf byteBuf, short value){
		byteBuf.writeShort(value);
	}
	
	public void writeInt(ByteBuf byteBuf, int value) {
		byteBuf.writeInt(value);
	}
	
	public void writeLong(ByteBuf byteBuf, long value){
		byteBuf.writeLong(value);
	}
	
	public void writeBoolean(ByteBuf byteBuf, boolean value){
		byteBuf.writeBoolean(value);
	}
	
	public void writeString(ByteBuf byteBuf, String value) {
		if (value == null) {
			byteBuf.writeInt(0);
		} else {
			try {
				byte[] bytes = value.getBytes(STRING_CHARSET);
				byteBuf.writeInt(bytes.length);
				byteBuf.writeBytes(bytes);
			} catch (UnsupportedEncodingException e) {
				Loggers.MSG_LOGGER.error("writeString encoding error:" + e.getMessage());
			}
		}
	}
	
	public void writeObject(ByteBuf byteBuf, ITransportable transObj){
		if(transObj == null){
			byteBuf.writeInt(NULL_OBJECT_FLAG);
		}else{
			byteBuf.writeInt(NOT_NULL_OBJECT_FLAG);
			transObj.write(byteBuf);
		}
	}
	
	public byte readByte(ByteBuf byteBuf){
		return byteBuf.readByte();
	}
	
	public short readShort(ByteBuf byteBuf){
		return byteBuf.readShort();
	}
	public int readInt(ByteBuf byteBuf){
		return byteBuf.readInt();
	}
	
	public long readLong(ByteBuf byteBuf){
		return byteBuf.readLong();
	}
	
	public boolean readBoolean(ByteBuf byteBuf){
		return byteBuf.readBoolean();
	}
	
	public String readString(ByteBuf byteBuf){
		int length = byteBuf.readInt();
		if(length==0){
			return "";
		}else{
			byte[] bytes = new byte[length];
			byteBuf.readBytes(bytes);
			try {
				return new String(bytes,STRING_CHARSET);
			} catch (UnsupportedEncodingException e) {
				Loggers.MSG_LOGGER.error("readString encoding error:"+ e.getMessage());
				return "";
			}
		}
	}
	
	public ITransportable readObject(ByteBuf byteBuf, Class<? extends ITransportable> clasz){
		int objFlag = byteBuf.readInt();
		if(objFlag == NULL_OBJECT_FLAG){
			return null;
		}else{
			ITransportable object = null;
			try {
				object = clasz.newInstance();
				object.read(byteBuf);
				
			} catch (InstantiationException e) {
				Loggers.MSG_LOGGER.error("readObject error:"+ e.getMessage());
			} catch (IllegalAccessException e) {
				Loggers.MSG_LOGGER.error("readObject error:"+ e.getMessage());
			}
			return object;
		}
	}
}
