package org.ngsoft.robot.command;

import org.ngsoft.robot.message.CSGmCommandMessage;

import io.netty.channel.ChannelHandlerContext;

public class GmCommand implements ICommand {
	
	private ChannelHandlerContext context;
	private String commandText;

	public ChannelHandlerContext getContext() {
		return context;
	}

	public void setContext(ChannelHandlerContext context) {
		this.context = context;
	}
	
	public String getCommandText() {
		return commandText;
	}

	public void setCommandText(String commandText) {
		this.commandText = commandText;
	}

	@Override
	public void execute() {
		CSGmCommandMessage gmMsg = new CSGmCommandMessage();
		gmMsg.setCommand(commandText);
		context.channel().pipeline().write(gmMsg);
		context.channel().pipeline().flush();
	}
}
