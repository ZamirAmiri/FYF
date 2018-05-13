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
		Thread serverMagager = new ServerManager(port,1);
		serverMagager.start();
		
		/*Vector<Socket> users = new Vector<>();
		Vector<DataOutputStream> out = new Vector<>();
			try {
				System.out.println("Sending messages");
				users.add(new Socket(InetAddress.getByName("127.0.0.1"),8000));
				out.add(new DataOutputStream(users.get(0).getOutputStream()));
				//System.out.println(String.valueOf(i));
				out.get(0).writeUTF("gps,User"+String.valueOf(0)+",x,y");
				out.get(0).flush();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block///
				e.printStackTrace();
			}
			int j=100000000;
			while(j-->0);
		
		for(int i=1;i<10;i++)
		{
			try {
				//System.out.println("Sending messages");
				users.add(new Socket(InetAddress.getByName("127.0.0.1"),8001));
				out.add(new DataOutputStream(users.get(i).getOutputStream()));
				//System.out.println(String.valueOf(i));
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
		} */
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
	private static int min(int a,int b)
	{
		return a>b?a:b;
	}

}
