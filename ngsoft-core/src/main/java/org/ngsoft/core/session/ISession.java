package org.ngsoft.core.session;

import java.net.SocketAddress;

import org.ngsoft.core.message.IMessage;

/**
 * 玩家会话Session
 * 
 * @author will
 *
 */
public interface ISession {
	
	void write(IMessage message);
	
	SocketAddress remoteAddress();
	
	void setAttribute(String key,Object obj);
	
	Object getAttribute(String key);
}
