package org.ngsoft.core.message;

import io.netty.buffer.ByteBuf;

public interface ITransportable {
	
	void write(ByteBuf byteBuf);
	
	void read(ByteBuf byteBuf);

	void writeByte(ByteBuf byteBuf, byte value);
	
	void writeShort(ByteBuf byteBuf, short value);
	
	void writeInt(ByteBuf byteBuf, int value) ;
	
	void writeLong(ByteBuf byteBuf, long value);
	
	void writeBoolean(ByteBuf byteBuf, boolean value);
	
	void writeString(ByteBuf byteBuf, String value);
	
	void writeObject(ByteBuf byteBuf, ITransportable transObj);
	
	byte readByte(ByteBuf byteBuf);
	
	short readShort(ByteBuf byteBuf);
	
	int readInt(ByteBuf byteBuf);
	
	long readLong(ByteBuf byteBuf);
	
	boolean readBoolean(ByteBuf byteBuf);
	
	String readString(ByteBuf byteBuf);
	
	ITransportable readObject(ByteBuf byteBuf, Class<? extends ITransportable> clasz);
}
