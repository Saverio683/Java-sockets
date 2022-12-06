package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
	private Socket socket;
	
	public ServerThread(Socket s) {
		super();
		this.socket = s;
	}
		
 	//override of the run method
	public void run() {
    	try {         
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));//for reading client messages
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);//to send messages to client

            while(true) {
                String incoming = br.readLine();//listening for the client messages    
                
                pw.println("You sent me this: "+incoming);//responding to the client
            }  
        } catch (IOException e) {
        	System.out.println(this.getName()+": connection ended");
        } 
    }
}
