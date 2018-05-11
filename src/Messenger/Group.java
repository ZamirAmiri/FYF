package Messenger;

import java.util.Vector;

public class Group extends Thread{
	private Vector<Thread>users;
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
		System.out.println("Group started as thread");
		groupThread();
	}

	private void groupThread()
	{
		System.out.println("Thread started");
		if(users.size() == 0 && flag == true)
		{
			return;
		}
		while(messageQueue.size() == 0);
		/*
		{
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		handleMessage(this.messageQueue.firstElement());
		//messageGroup(this.messageQueue.firstElement());
		this.messageQueue.remove(0);
		this.groupThread();
	}
	
	private void messageGroup(String message)
	{
		for(int i=0;i<users.size();i++)
		{
			//this.users.elementAt(i).send(message);
		}
	}
	public void addUser(User u)
	{
		System.out.println(u.getName() +"added to the group");
		u.addMessageQueue(this.messageQueue);
		Thread t = new Thread(u);
		this.users.addElement(t);
		t.start();
		this.flag = true;
	}
	private void handleMessage(String m)
	{
		System.out.println("Handling message: "+ m);
	}

}


