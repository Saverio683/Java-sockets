package tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	static final int PORT = 1234;
	
	public static void main(String args[]) {	    
	    System.out.println("Server listening on port "+PORT+"...");
	    
	    try {
	    	ServerSocket server = new ServerSocket(PORT);  	 
	    	
		    while(true) {//listening for client connections
		        try {
		        	Socket socket = server.accept();//Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made.
		        	
		        	ServerThread st = new ServerThread(socket);//assigning a thread to communicate with the client
		        	st.start();
		        	
		            System.out.println("Connection established, thread assigned: "+st.getName());
		        }
			    catch(Exception e) {
			        e.printStackTrace();
			        System.out.println("Connection error");
			        server.close();	      
			    }
		    }
	    }
	    catch(IOException e) {
		    e.printStackTrace();
		    System.out.println("Server error");
	    }
	}	
}
