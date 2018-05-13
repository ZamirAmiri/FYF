package User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

public class UserSocket{
	
	private Vector<String> messageQueue;
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private int port;
	private UserListener listener;
	UserSocket(int port,Vector<String> messageQueue)
	{
		this.messageQueue = messageQueue;
		this.port = port;
		setUp();
	}
	private void setUp()
	{
		
		try {
			System.out.println("Socket Created");
			this.socket =new Socket(InetAddress.getByName("127.0.0.1"),this.port);
			this.out = new DataOutputStream(this.socket.getOutputStream());
			System.out.println("User Listener created");
			this.listener = new UserListener (this.messageQueue,new DataInputStream(this.socket.getInputStream()));
			this.listener.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void send(String command,String name,double x,double y)
	{
		String message = command.concat(",").concat(name).concat(",").concat(String.valueOf(x)).concat(String.valueOf(y));
		try {
			this.out.writeUTF(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void shutdown()
	{
		try {
			this.in.close();
			this.out.close();
			this.socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
