package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ClientWriter extends Thread {
	private PrintWriter pw;
	private BufferedReader stdin;
	
	public ClientWriter(PrintWriter pw, BufferedReader stdin) {
		this.pw = pw;
		this.stdin = stdin;
	}
	
	public void run() {
		
        while (true) {//l'utente invia messaggi finchè la comunicazione col server non si interrompe        	
        	try {
        		String clientInput = stdin.readLine();//reading user input
        		
        		pw.println(clientInput);//sending user input to the server
        	} catch(IOException e) {
                System.out.println("Client i/o error");
            }
        }
	}
}
