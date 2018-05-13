package User;

import java.util.Vector;

public class UserManager extends Thread{
	private volatile Vector<String> messageQueue;
	private Vector<User> users;
	private UserSocket socket;
	
	UserManager(int port)
	{
		System.out.println("Message Queue created");
		this.messageQueue = new Vector<>();
		System.out.println("User Socket created");
		this.socket = new UserSocket(port, messageQueue);
	}
	public void run()
	{
		while(true)
		{
			if(this.messageQueue.isEmpty())
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
			System.out.println("Message FOUND");
			handleMessage();
		}
	}
	private void handleMessage() {
		String message = this.messageQueue.firstElement();
		System.out.println(message);
		this.messageQueue.remove(0);
	}

}
