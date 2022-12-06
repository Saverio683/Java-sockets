package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {
	static final int PORT = 1234;
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket(InetAddress.getLocalHost(), PORT);//creates the socket, then connects to the server
		
		
        BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));//for reading server messages
        BufferedReader userReader = new BufferedReader(new InputStreamReader(System.in));//for reading user inputs
        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);//to send messages to the server
        
        ClientReader cr = new ClientReader(serverReader);
        ClientWriter cw = new ClientWriter(pw, userReader);
        
        cr.start();
        cw.start();
	}
}
