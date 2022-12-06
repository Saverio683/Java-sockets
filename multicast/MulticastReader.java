package multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastReader extends Thread {
	private MulticastSocket socket;
	private InetAddress ip;
	
	public MulticastReader(MulticastSocket s, InetAddress i) {
		this.socket = s;
		this.ip = i;
	}
	
    private void listen() throws IOException {		
		byte[] buf = new byte[1024];
		DatagramPacket recv = new DatagramPacket(buf, buf.length, ip, socket.getLocalPort());
		socket.receive(recv);
		 
		String incoming = new String(buf);
		System.out.println(incoming);
    }
	
	public void run() {
		while(true) {
			try {
				listen();
			} catch (IOException e) {
				System.out.println(this.getName()+": error listening incoming messages");
			}
		}
	}
}
