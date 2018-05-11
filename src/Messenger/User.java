package Messenger;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class User implements Runnable{
	private ServerSocket server;
	private String name;
	private int port;
	private Socket socket;
	private Vector<String> messageQueue;
	private DataOutputStream out;
	private Thread listener;
	User(String name,int port)
	{
		System.out.println(name + " created.");
		this.name = name;
		this.port = port;
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
			System.out.println(this.name + ": Creating streams.");
			server = new ServerSocket(this.port);
			this.socket = server.accept();
			System.out.println(this.name +": Connected");
			this.out = new DataOutputStream(this.socket.getOutputStream());
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
	public ServerSocket getServer() {
		return server;
	}
	public String getName() {
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


}
