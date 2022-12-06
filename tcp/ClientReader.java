package tcp;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientReader extends Thread {
	private BufferedReader stdin;
	
	public ClientReader(BufferedReader stdin) {
		this.stdin = stdin;
	}
	
	public void run() {
		
        while (true) {
        	try {
        		String incoming = stdin.readLine();//reading server messages
        		
        		System.out.println(incoming);
        	} catch(IOException e) {
                System.out.println("Client i/o error");
            }
        }
	}
}
