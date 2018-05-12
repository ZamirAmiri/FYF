package Messenger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
	private ServerSocket server ;
	private int name;
	private int port;
	
	Server(int port)
	{
		System.out.println("Creating server with port nr "+String.valueOf(port));
		this.name = 0;
		this.port = port;
		try {
			System.out.println("Server:Server Socket created with port "+String.valueOf(port));
			this.server = new ServerSocket(this.port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run()
	{
		while(true)
			listen();
	}
	private void listen()
	{
		try {
			System.out.println("Group is created by Host");
			GroupServer g = new GroupServer(this.port+1,this.name++,(new User("0",this.server.accept())));
			g.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
