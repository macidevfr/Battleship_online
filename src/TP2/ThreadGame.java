package TP2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadGame extends Thread {

	private  Socket 		 socketJoueur1;
	private  Socket 		 socketJoueur2;
	private  BufferedReader  br1,br2;
	private  PrintWriter     pw1,pw2 ;
	
	public ThreadGame(Socket socketJoueur1, Socket socketJoueur2 ) {
		
		try 
		{
		this.socketJoueur1 = socketJoueur1;
		this.socketJoueur2 = socketJoueur2;	
		this.br1 		   = new BufferedReader(new InputStreamReader (socketJoueur1.getInputStream())) ;
		this.br2		   = new BufferedReader(new InputStreamReader (socketJoueur2.getInputStream())) ;
		this.pw1		   = new PrintWriter(socketJoueur1.getOutputStream(),true);
		this.pw2		   = new PrintWriter(socketJoueur2.getOutputStream(),true);
		}
		catch(Exception e) 
		{
		}
	}
	
	@Override
	public void run() {
		broadcastServer(socketJoueur1,socketJoueur2,"Game started");
		
		
	}
	
	public void broadcastServer(Socket socket1, Socket socket2,String message) 
	{
		try 
		{
			pw1.print(message);
			pw2.print(message);
		}
		catch(Exception e) 
		{
		}
	}

	
	
	/*public static void StartNewGame( final Socket socketJoueur1, final Socket socketJoueur2) 
	{
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("game started");
					//Lancement de la partie 
				}
			}).start();
	}*/
	
}
