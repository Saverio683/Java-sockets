package multicast;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;

public class MulticastWriter extends Thread {
	private MulticastSocket socket;
	private BufferedReader br;
	private InetAddress ip;
	
	public MulticastWriter(MulticastSocket s, BufferedReader b, InetAddress i) {
		this.socket = s;
		this.br = b;
		this.ip = i;
	}
	
    private void send(String msg) throws IOException {   
        
		byte[] msgBytes = msg.getBytes(StandardCharsets.UTF_8);
		
		DatagramPacket ds = new DatagramPacket(msgBytes, msgBytes.length, ip, socket.getLocalPort());
		socket.send(ds);	
    }
	
	public void run() {				
		while(true) {
			try {
				String userInput = br.readLine();
				send(userInput);//sending user message to other clients in the same group
			} catch (IOException e) {
				System.out.println(this.getName()+": error sending message");
			}
		}
	}
}
