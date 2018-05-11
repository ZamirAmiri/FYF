package Messenger;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Group group = new Group();
		
		int port = 9000;
		for(int i=0;i<10;i++)
		{
			System.out.println("Adding users");
			group.addUser(new User("User"+String.valueOf(i),port+i));
		}
		group.start();
		
		Vector<Socket> users = new Vector<>();
		Vector<DataOutputStream> out = new Vector<>();
		for(int i=0;i<10;i++)
		{
			try {
				System.out.println("Sending messages");
				users.add(new Socket(InetAddress.getByName("127.0.0.1"),port + i ));
				out.add(new DataOutputStream(users.get(i).getOutputStream()));
				out.get(i).writeUTF("Hello");
				out.get(i).flush();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
