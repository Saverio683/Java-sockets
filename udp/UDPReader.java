package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPReader extends Thread {
	private DatagramSocket socket;
	
	public UDPReader(DatagramSocket s) {
		super();
		this.socket = s;
	}
	
	public void run() {		
		while(true) {			
			try {
				byte[] receiveData = new byte[1024];
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);//messages travel as bytes through datagram packets
				socket.receive(receivePacket);//The socket remains listening for the datagram packet and then saves it to the buffer
				
				String incoming = new String(receiveData);
				System.out.println(incoming);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
