package org.ngsoft.core.server;

import java.util.TimerTask;

import org.ngsoft.core.server.thread.ServerThread;

public class ServerTimerTask extends TimerTask {

	private ServerThread serverThread;
	
	public ServerTimerTask(ServerThread serverThread){
		this.serverThread = serverThread;
	}
	
	@Override
	public void run() {
				
	}

}
