package org.ngsoft.core.netty.codec;

public class ServerProtocolFactory {
	
	public static ServerProtocolEncoder getEncoder(){
		return new ServerProtocolEncoder();
	}
	public static ServerProtocolDecoder getDecoder(){
		return new ServerProtocolDecoder();
	}
}
