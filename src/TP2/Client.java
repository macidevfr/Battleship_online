package TP2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Client extends Thread {
	

	private Socket 			socket;
	private BufferedReader  bufferedReader;
	private PrintWriter		printwriter ;
	private String 			username;
	
	public Client(Socket socket,String username) 
	{
		try 
		{
		this.socket		 	= socket;
		this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.printwriter    = new PrintWriter(socket.getOutputStream(),true);
		this.username 		= username;
		}
		catch(Exception e)
		{	
		}
	}
	
	/*	public void ClientEcoute() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try
				{
					String messageFromClient;
					while(socket.isConnected())
					{
						messageFromClient = bufferedReader.readLine();
						System.out.println(messageFromClient);
					}
				}
				catch(Exception e) 
				{
				}
			}
		}).start();
	}
	*/
	
	public void ClientParle() {
		String messageFromClient;
		Scanner sc = new Scanner(System.in);
		
		while(socket.isConnected()) 
		{
			System.out.print("Inserez votre message: ");
			messageFromClient = sc.nextLine();
			printwriter.print(username+" : "+messageFromClient);
		}
	}
	
	public static void main(String[] args) {
		try
		{
			String username;
			Scanner sc = new Scanner(System.in);
			System.out.println("Entrez votre nom pour initialiser la partie");
			username = sc.nextLine();
			
			Socket socket 	= new Socket("localhost",1234);
			Client client 	= new Client(socket,username);
			System.out.println("Connexion etablie ");
							
			while(socket.isConnected()) {
				client.ClientParle();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
}
	
}
