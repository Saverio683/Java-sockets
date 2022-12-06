package udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.NetworkInterface;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Objects;

public class UDPWriter extends Thread {
	private DatagramSocket socket;
	private int port;
	
	public UDPWriter(DatagramSocket s, int p) {
		super();		
		this.socket = s;
		this.port = p;
	}
	
	public void run() {
		try {	
			BufferedReader userReader = new BufferedReader(new InputStreamReader(System.in));//reading user inputs
			
			while(true) {		
				byte[] sendData = userReader.readLine().getBytes(StandardCharsets.UTF_8);//saves the user input to the buffer
				broadcast(sendData);//sends the packet to all sockets on the network
			} 
		} catch (IOException e) {
			System.out.println(this.getName()+": error sending message");
		}
	}
	
    public void broadcast(byte[] buffer) throws IOException {
    	Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while(interfaces.hasMoreElements()) {//iterates all network interfaces on the pc to get the ips of the other hosts where to send the broadcast message
            NetworkInterface networkInterface = interfaces.nextElement();

            if (networkInterface.isUp() || !networkInterface.isLoopback()) {//verifies the integrity of the network interface
                networkInterface.getInterfaceAddresses().stream() 
	                .map(interfaceAddress -> interfaceAddress.getBroadcast())
	                .filter(Objects::nonNull)
	                .forEach(ip -> {//iterate over the ips
						try {
							socket.send(new DatagramPacket(buffer, buffer.length, ip, port));//sending the packet
						} catch (IOException e) {
							System.out.println(this.getName()+": error sending message");
						}	
					});
            }
        }
   }
}