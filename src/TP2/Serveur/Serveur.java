package TP2.Serveur;

import TP2.Actions;
import TP2.Jeu.Grid;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur extends Thread {

	private int[][] coordinatesp1 = new int[10][10];
	private int[][] attacksp1 = new int[10][10];
	private int[][] coordinatesp2 = new int[10][10];
	private int[][] attacksp2 = new int[10][10];
	private int pready, sendattack;
	private boolean placeboat,endgame,attacking;
	private int active,oui,non;

	public Serveur (){

		this.coordinatesp1 = coordinatesp1;
		this.attacksp1 = attacksp1;

		this.coordinatesp2 = coordinatesp2;
		this.attacksp2 = attacksp2;

		this.pready = 0;

		this.placeboat = false;
		this.endgame = false;
		this.active = 1;
		this.attacking = true;
		this.sendattack = 0;

		this.oui = oui;
		this.non = non;
	}

	public int getOui() {
		return oui;
	}

	public void setOui(int oui) {
		this.oui = oui;
	}

	public int getNon() {
		return non;
	}

	public void setNon(int non) {
		this.non = non;
	}

	public int getSendattack() {
		return sendattack;
	}

	public void setSendattack(int sendattack) {
		this.sendattack = sendattack;
	}

	public int getPready() {
		return pready;
	}

	public void setPready(int pready) {
		this.pready = pready;
	}

	public boolean isPlaceboat() {
		return placeboat;
	}

	public void setPlaceboat(boolean placeboat) {
		this.placeboat = placeboat;
	}

	public boolean isEndgame() {
		return endgame;
	}



	public void setEndgame(boolean endgame) {
		this.endgame = endgame;
	}

	public boolean isAttacking() {
		return attacking;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public void setCoordinatesp1(int[][] coordinatesp1) {
		this.coordinatesp1 = coordinatesp1;
	}

	public void setAttacksp1(int[][] attacksp1) {
		this.attacksp1 = attacksp1;
	}

	public void setCoordinatesp2(int[][] coordinatesp2) {
		this.coordinatesp2 = coordinatesp2;
	}

	public void setAttacksp2(int[][] attacksp2) {
		this.attacksp2 = attacksp2;
	}

	public int[][] getCoordinatesp1() {
		return this.coordinatesp1;
	}

	public int[][] getCoordinatesp2() {
		return this.coordinatesp2;
	}

	public int[][] getAttacksp1() {
		return this.attacksp1;
	}

	public int[][] getAttacksp2() {
		return this.attacksp2;
	}


	public static void main(String[] args) {

		try {
			ServerSocket ecoute = new ServerSocket(1234);
			int id = 0;
			while (true) {
				id++;


				Socket socketJoueur1 = ecoute.accept();
				System.out.println("Un joueur est connecte en attente du deuxieme!");
				Socket socketJoueur2 = ecoute.accept();
				BufferedReader br1 = new BufferedReader(new InputStreamReader(socketJoueur1.getInputStream()));
				BufferedReader br2 = new BufferedReader(new InputStreamReader(socketJoueur2.getInputStream()));
				PrintWriter pw1 = new PrintWriter(socketJoueur1.getOutputStream(), true);
				PrintWriter pw2 = new PrintWriter(socketJoueur2.getOutputStream(), true);
				Serveur serveur = new Serveur();
				System.out.println("Le deuxieme joueur s'est connecte");
				System.out.println("Partie numero: " + id + " lancee");

				ThreadGame threadGame1 = new ThreadGame(socketJoueur1, serveur, 1);
				threadGame1.start();
				ThreadGame threadGame2 = new ThreadGame(socketJoueur2, serveur, 2);
				threadGame2.start();


			}} catch(Exception e){
				e.printStackTrace();
			}

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

