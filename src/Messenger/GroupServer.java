package Messenger;

import java.io.IOException;
import java.net.ServerSocket;

public class GroupServer extends Thread{
	private int name;
	private int port;
	private ServerSocket socket;
	private Group group;
	private int userCounter;

	GroupServer(int port,int name,User u){
		System.out.println("GroupServer created with port"+port);
		this.name = name;
		this.port = port;
		this.group = new Group();
		this.group.addUser(u);
		this.group.start();
		this.userCounter = 0;
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
			//System.out.println("HelloPlease");
			addUserToGroup(new User(String.valueOf(this.userCounter++),this.socket.accept()));
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
	private void addUserToGroup(User u)
	{
		this.group.addUser(u);
		
	}

}
