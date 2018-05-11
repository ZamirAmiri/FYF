package Messenger;
import java.util.Vector;

public class MessageHandler implements Runnable{
	
	private Vector<String> message;
	MessageHandler(Vector<String> messageQueue)
	{
		this.message = messageQueue;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i = 0;
		while(true)
		{
			if(message.isEmpty())
				message.add(String.valueOf(i++));
			/*
			if(message.isEmpty())
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			System.out.println("Message is: " + message.firstElement());
			message.remove(0);*/
		}
		
	}
	

}
