package Messenger;

import java.io.IOException;
import java.net.ServerSocket;

public class GroupServer extends Thread{
	private int name;
	private int port;
	private ServerSocket socket;
	private Group group;
	private int userCounter = 0;

	GroupServer(int port,int name){
		this.name = name;
		this.port = port;
		this.group = new Group();
		this.group.start();
	}
	public void run()
	{
		setUpStreams();
		while(true)
		{
			listen();
		}
	}
	private void listen() {
		// TODO Auto-generated method stub
		try {
			this.group.addUser(new User(String.valueOf(this.userCounter++),this.socket.accept()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void setUpStreams()
	{
		try {
			this.socket = new ServerSocket(this.port);
			System.out.println("Socket "+ String.valueOf(this.port)+": created");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
