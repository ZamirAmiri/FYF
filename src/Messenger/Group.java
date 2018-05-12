package Messenger;

import java.util.Vector;

public class Group extends Thread{
	private Vector<User>users;
	private Vector<String> messageQueue;
	private boolean flag;
	
	Group(){
		System.out.println("Group created.");
		this.users = new Vector<>();
		this.messageQueue = new Vector<>();
		this.flag = false;
		//groupThread();
	}
	
	public void run()
	{
		//System.out.println("Group started as thread");
		groupThread();
	}

	private void groupThread()
	{
		//System.out.println("Thread started");
		if(users.size() == 0 && flag == true)
		{
			return;
		}
		if(messageQueue.size() == 0)
		{
			
			try {
				synchronized (this) {
				    this.wait();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		handleMessage(this.messageQueue.firstElement());
		//messageGroup(this.messageQueue.firstElement());
		this.messageQueue.remove(0);
		this.groupThread();
	}
	
	private void messageGroup(String message)
	{
		for(int i=0;i<users.size();i++)
		{
			this.users.elementAt(i).send(message);
		}
	}
	public void addUser(User u)
	{
		//System.out.println(u.getNameUser() +"added to the group");
		u.addMessageQueue(this.messageQueue);
		
		this.users.addElement(u);
		u.start();
		this.flag = true;
	}
	private void handleMessage(String m)
	{
		String[] message = m.split(",");
		handleZero(m);
		//System.out.println("Handling message: "+ m);
	}
	private void handleZero (String message)
	{
		if(message.contains("leave")) {
			String[] msg = message.split(",");
			int index = this.getUserIndex(msg[1]);
		}else {
			// Do nothing
		}
		this.messageGroup(message);
	}
	private int getUserIndex(String name)
	{
		for(int i=0;i<this.users.size();i++)
			if(((User) users.get(i)).getNameUser() == name)
			{
				return i;
			}
		return -1;
	}

}


