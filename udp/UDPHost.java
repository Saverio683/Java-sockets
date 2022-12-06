package udp;

import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPHost {
	static final int PORT = 8290;
	
	public static void main(String[] args) throws SocketException, UnknownHostException {
		DatagramSocket senderSocket = new DatagramSocket();
		senderSocket.setBroadcast(true);
		
		DatagramSocket receiveSocket = new DatagramSocket(null);//all read sockets will be listening on the same port
		receiveSocket.setReuseAddress(true);
		receiveSocket.bind(new InetSocketAddress(PORT));
		
		UDPReader r = new UDPReader(receiveSocket);
		UDPWriter w = new UDPWriter(senderSocket, PORT);
		
		r.start();
		w.start();
	}
}
