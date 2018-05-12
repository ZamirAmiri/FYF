package Messenger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int port = 8000;
		Thread gserver = new GroupServer(port, 1);
		gserver.start();
		Vector<Socket> users = new Vector<>();
		Vector<DataOutputStream> out = new Vector<>();
		for(int i=0;i<10;i++)
		{
			try {
				System.out.println("Sending messages");
				users.add(new Socket(InetAddress.getByName("127.0.0.1"),port));
				out.add(new DataOutputStream(users.get(i).getOutputStream()));
				out.get(i).writeUTF("gps,User"+String.valueOf(i)+",x,y");
				out.get(i).flush();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block///
				e.printStackTrace();
			}
		}
		
		try {
			DataInputStream in = new DataInputStream(users.get(0).getInputStream());
			for(int i=0;i<10;i++)
				readNextLine(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	private static void readNextLine(DataInputStream in) {
		try {
			String msg = in.readUTF();
			System.out.println(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
