package multicast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class MulticastHost {
	static final int PORT = 1234; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//reading user inputs
		MulticastSocket socket = new MulticastSocket(PORT);
		InetAddress ip = null;
		
		boolean wrongUserInput = true;
		System.out.println("Select a room: a) gossib b) brainstorming c) soccer");
		
		while(wrongUserInput) {
			wrongUserInput = false;			
			
			switch(br.readLine()) {
				case "a":
					ip = InetAddress.getByName("230.0.0.1");
					break;
				case "b":
					ip = InetAddress.getByName("230.0.0.2");
					break;
				case "c":
					ip = InetAddress.getByName("230.0.0.3");					
					break;
				default:
					System.out.println("wrong room entered, please retry");
					wrongUserInput = true;
					break;
			}
		}
		
		InetSocketAddress address = new InetSocketAddress(ip, PORT);
		NetworkInterface netIf = NetworkInterface.getByName("wlo1");//obtained by typing from terminal the command "ifconfig" (linux)
		
		socket.joinGroup(address, netIf);
		socket.setBroadcast(true);
		
		MulticastWriter w = new MulticastWriter(socket, br, ip);
		MulticastReader r = new MulticastReader(socket, ip);
		
		w.start();
		r.start();
		
	}
}
