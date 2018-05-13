package User;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;

public class UserListener extends Thread{
	private DataInputStream in;
	private Vector<String> messageQueue;
	
	UserListener(Vector<String> messageQueue,DataInputStream in)
	{
		this.in=in;
		this.messageQueue = messageQueue;
	}
	
	public void run()
	{
		while(true)
			listen();
	}
	private void listen()
	{
		//this.messageQueue.add("DATA...");
		//System.out.println("Listening to the server...");
		try {
			String message = this.in.readUTF();
			//System.out.println("Listener has found: " + message);
			this.messageQueue.add(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
