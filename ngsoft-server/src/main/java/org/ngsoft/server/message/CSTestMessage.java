package org.ngsoft.server.message;

import io.netty.buffer.ByteBuf;

import org.ngsoft.core.message.Message;

public class CSTestMessage extends Message{
	private long playerId;
	@Override
	public void write(ByteBuf byteBuf) {
		writeLong(byteBuf,playerId);
	}

	@Override
	public void read(ByteBuf byteBuf) {
		playerId = readLong(byteBuf);
	}
	
	

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	@Override
	public int getId() {
		return 101101;
	}

}
