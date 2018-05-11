package Messenger;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;

public class UserListener extends Thread{
	private DataInputStream in;
	private Vector<String> messageQueue;
	
	UserListener (DataInputStream in,Vector<String> m)
	{
		this.in = in;
		this.messageQueue = m;
	}

	@Override
	public void run() {
		System.out.println("Listener started");
		while(this.listen());
		
	}

	private boolean listen()
	{
		System.out.println("Listening to user");
		String s = null;
		try {
			s = this.in.readUTF();
			System.out.println("Message recieved is: "+ s);
			this.messageQueue.add(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(s == null)
			return false;
		else
			s = null;
		return true;
	}
	
	public void shutdown()
	{
		try {
			this.in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
