package TP2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Serveur extends Thread {

	
	public static void main(String[] args) {
		
		try 
		{
			ServerSocket ecoute = new ServerSocket(1234);
			int id = 0;
			while(true) 
			{
				id++;
				Socket socketJoueur1 = ecoute.accept();
				System.out.println("Un joueur est connecte en attente du deuxieme!");
				Socket socketJoueur2 = ecoute.accept();
				System.out.println("Le deuxieme joueur s'est connecte");
				System.out.println("Partie numero: "+id+" lancee");
				ThreadGame threadGame = new ThreadGame(socketJoueur1,socketJoueur2);
				threadGame.start();
				//StartNewGame(socketJoueur1,socketJoueur2);
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * public static void broadcastServer(Socket socket1, Socket socket2,String
	 * message) { try { PrintWriter p1 = new
	 * PrintWriter(socket1.getOutputStream(),true); PrintWriter p2 = new
	 * PrintWriter(socket2.getOutputStream(),true); p1.print(message);
	 * p2.print(message); } catch(Exception e) { } }
	 */

	/*
	public static void StartNewGame( final Socket socketJoueur1, final Socket socketJoueur2) 
	{
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("game started");
					//Lancement de la partie 
				}
			}).start();
	}
	*/
}
