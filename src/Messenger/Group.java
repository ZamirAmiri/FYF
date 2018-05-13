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
		for(int i=0;i<10;i++)
			this.messageQueue.add("User ".concat(String.valueOf(i)).concat(" says hello, and welcomes you!"));
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
		int iterator = 0;
		//System.out.println("Thread started");
		while(true)
		{
			if(users.size() == 0 && flag == true)
			{
				return;
			}
			if(messageQueue.isEmpty())
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
			System.out.println("Message nr:"+ (iterator++) + " "+messageQueue.firstElement());
			
			handleMessage(this.messageQueue.firstElement());
			//messageGroup(this.messageQueue.firstElement());
			this.messageQueue.remove(0);
		}
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
		String name = message.split(",")[1];
		if(message.contains("join")) {
			this.users.get(this.users.size()-1).setName(name);
		}if(message.contains("leave")) {
			String msg = message.split(",")[1];
			this.users.remove(this.getUserIndex(msg));
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


