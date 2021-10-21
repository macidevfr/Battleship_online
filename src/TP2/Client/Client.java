package TP2.Client;
import TP2.Actions;
import TP2.Jeu.Grid;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;


public class Client extends Thread {


	private Socket 			socket;
	private BufferedReader  bufferedReader;
	private PrintWriter		printwriter ;
	public String 			username;
	private Grid selfgrid = new Grid(true);
	private Grid attackgrid = new Grid(false);
	private int[][] coordinates ;
	private int[][] attacks;
	//Integer[] size = new Integer[]{5, 3, 2, 3, 4};
	Integer[] size = new Integer[]{2};
	public boolean available, placing, attacking;


	Actions action = new Actions();




	
	public Client(Socket socket,String username) 
	{
		try 
		{
		this.socket		 	= socket;
		this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.printwriter    = new PrintWriter(socket.getOutputStream(),true);
		this.username 		= username;
		this.coordinates =new int[10][10];
		this.attacks =new int[10][10];
		this.available = false;
		this.placing = false;
		this.attacking = false;

		}
		catch(Exception e)
		{	
		}
	}

	public BufferedReader getBufferedReader() {
		return bufferedReader;
	}

	public PrintWriter getPrintwriter() {
		return printwriter;
	}

	public void setCoordinates(int[][] tab) {
		for (int i=0;i<10;i++){
			for (int j=0;j<10;j++){
				this.coordinates[i][j]=tab[i][j];
			}
		}
	}
	public void setAttacks(int[][]tab) {
		for (int i=0;i<10;i++){
			for (int j=0;j<10;j++){
				this.attacks[i][j]=tab[i][j];
			}
		}
	}

	public Grid getSelfgrid() {
		return selfgrid;
	}

	public Grid getAttackgrid() {
		return attackgrid;
	}

	public int[][] getCoordinates() {
		return this.coordinates;
	}

	public int[][] getAttacks() {
		return this.attacks;
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
		
		while(socket.isConnected() && this.available == true)
		{
			System.out.print("Inserez votre message: ");
			messageFromClient = sc.nextLine();
			if (messageFromClient.equals("g"))
				System.out.println(this.selfgrid.Printgrid(this.coordinates));
			else if (messageFromClient.equals("a"))
				System.out.println(this.attackgrid.Printgrid(this.attacks));
			else if (messageFromClient.equals("att")){
				System.out.println(this.username);
				for (int i=0;i<10;i++){
					for (int j=0;j<10;j++){
						System.out.print(this.coordinates[i][j]);
					}
				}
				System.out.println();}
			else this.printwriter.println(username+" : "+messageFromClient);
		}
	}
	
	public static void main(String[] args) {
		try
		{
			Socket socket 	= new Socket("localhost",1234);
			System.out.println("Connexion etablie ");

			String username;
			Scanner sc = new Scanner(System.in);
			System.out.println("Entrez votre nom pour initialiser la partie");
			username = sc.nextLine();

			Client client 	= new Client(socket,username);
			client.printwriter.println("Username,"+username);


			new ListeningThread(socket,client).start();
			while (true){
				//System.out.println(client.available+" "+client.placing+" "+client.attacking);
				//if (client.available) client.ClientParle();
				if (client.placing) {
					client.Placeships(client);
					client.printwriter.println("Je suis prêt");
				}
				if (client.attacking) client.Attack();
				System.out.print("");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
}

	private void Attack() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ligne visée : ");
		int ligne = sc.nextInt();
		System.out.println("Colonne visée : ");
		int colonne = sc.nextInt();
		this.attacking=false;
		String envoieatk = "Attaque,"+ligne+","+colonne;

		this.printwriter.println(envoieatk);

	}

	private void Placeships(Client client) throws InterruptedException {
		for (int a:this.size){
			this.action.Placeship(a,client);
			String b = "Bateaux ";
			for (int i=0;i<10;i++){
				for (int j=0;j<10;j++){
					b+= this.action.getCoordinates()[i][j]+",";
				}
			}
			this.printwriter.println(b);
			Thread.sleep(10);
			System.out.println(this.selfgrid.Printgrid(this.coordinates));
		}
		this.placing = false;
	}

}
