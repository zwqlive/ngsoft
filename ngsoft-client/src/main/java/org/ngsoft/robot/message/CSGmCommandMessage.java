package org.ngsoft.robot.message;

import io.netty.buffer.ByteBuf;

import org.ngsoft.core.message.Message;

public class CSGmCommandMessage extends Message{
	private String command;
	@Override
	public void write(ByteBuf byteBuf) {
		writeString(byteBuf,command);
	}

	@Override
	public void read(ByteBuf byteBuf) {
		command = readString(byteBuf);
	}
	
	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	@Override
	public int getId() {
		return 101102;
	}

}
