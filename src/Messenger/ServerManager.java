package Messenger;

import java.util.Vector;

public class ServerManager extends Thread{
	private Vector<Server> sockets;
	private int port;
	private int numberOfSockets;
	ServerManager(int port, int numberOfSockets)
	{
		sockets = new Vector<>();
		this.port = port;
		this.numberOfSockets = numberOfSockets;
	}

	public void run() {
		for(int i=0;i<this.numberOfSockets;i++)
		{
			Server s = new Server(this.port);
			s.start();
			this.sockets.add(s);	
		}
	}

}
