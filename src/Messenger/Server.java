package Messenger;

import java.util.Vector;

public class Server implements Runnable{
	Vector<User> users;
	Vector<Group>groups;
	Vector<String> messageQueue;
	Server()
	{
		users = new Vector<>();
		groups = new Vector<>();
		messageQueue = new Vector<>();
	}
	private void setUpStreams()
	{
		
	}
	public void run()
	{
		while(true)
		{
			if(messageQueue.isEmpty())
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			handleMessage();
		}
	}
	private void handleMessage()
	{
		String[] command = this.messageQueue.firstElement().split(",");
		String user;
		for (int i=0;i < 3;i++)
		{
			
		}
		 
	}

}
