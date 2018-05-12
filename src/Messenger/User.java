package Messenger;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class User extends Thread{
	private String name;
	private int port;
	private Socket socket;
	private Vector<String> messageQueue;
	private DataOutputStream out;
	private Thread listener;
	User(String name,Socket socket)
	{
		System.out.println("User created with the name:"+name);
		this.name = name;
		this.socket = socket;
		//initialize();
		//this.start();
	}
	
	public void addMessageQueue(Vector<String> messageQueue)
	{
		this.messageQueue = messageQueue;
	}
	private void initialize()
	{
		try {
			this.out = new DataOutputStream(this.socket.getOutputStream());
			//this.printLogger("Created");
			this.listener = new Thread(new UserListener(new DataInputStream(this.socket.getInputStream()), this.messageQueue));
			listener.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void send(String m)
	{
		try {
			this.out.writeUTF(m);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void shutdown()
	{
		try {
			this.out.close();
			((UserListener) this.listener).shutdown();
			this.socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String getNameUser() {
		return name;
	}
	public int getPort() {
		return port;
	}
	public Socket getSocket() {
		return socket;
	}
	public Vector<String> getMessageQueue() {
		return messageQueue;
	}
	public DataOutputStream getOut() {
		return out;
	}
	public Thread getListener() {
		return listener;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.initialize();
	}
	private void printLogger(String msg)
	{
		System.out.println("User "+this.name+": "+msg);
	}


}
