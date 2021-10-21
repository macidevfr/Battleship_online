package TP2.Client;

import TP2.Actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class ListeningThread extends Thread{
    private BufferedReader in;
    private Client c;

    public ListeningThread(Socket s, Client c) throws IOException {
        this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        this.c = c;

    }

    public void run(){
        try {
            int[][] coordinates = this.c.getCoordinates();
            while (true) {
                String str =in.readLine();

                if (str.contains("coordonnees")){
                    int[][] coor = new int[10][10];
                    String[] parts = str.split(" ");
                    String str2 = parts[1];
                    String[] chiffres1 = str2.split(",");
                    int index = 0;
                    for (int i=0;i<10;i++){
                        for (int j=0;j<10;j++){
                            coor[i][j] = Integer.parseInt(chiffres1[index]);

                            index+=1;

                    }
                    }

                    this.c.setCoordinates(coor);

                }

                else if (str.contains("attacks")){
                    int[][] att = new int[10][10];

                    String[] parts = str.split(" ");
                    String str2 = parts[1];
                    String[] chiffres2 = str2.split(",");
                    int index = 0;
                    for (int i=0;i<10;i++){
                        for (int j=0;j<10;j++){
                            att[i][j] = Integer.parseInt(chiffres2[index]);
                            index+=1;
                        }
                    }
                    this.c.setAttacks(att);
                }
                
                else if (str.contains("Placez")){
                    System.out.println(str);

                    this.c.placing = true;
                }

                else if (str.contains("A toi")){

                    System.out.println(str);
                    System.out.println(this.c.getAttackgrid().Printgrid(this.c.getAttacks()));
                    this.c.attacking = true;
                }

                else if (str.contains("Ton adversaire")){
                    Thread.sleep(1000);
                    System.out.println(str);
                    System.out.println("Ta grille :");
                    System.out.println(this.c.getSelfgrid().Printgrid(this.c.getCoordinates()));

                }

                else if (str.contains("Touché")){

                    System.out.println("Touché");
                }

                else if (str.contains("Coulé")){

                    System.out.println("Coulé");
                }

                else if (str.contains("Tu as")){
                    System.out.println(str);

                    Scanner sc = new Scanner(System.in);
                    System.out.println();
                    System.out.println("Voulez-vous rejouer?");
                    System.out.println("Entrez oui ou non");
                    String rep = sc.nextLine();
                    this.c.getPrintwriter().println(rep);
                }
                else System.out.println(str);
            }} catch (IOException | InterruptedException ioException) {
            ioException.printStackTrace();
        }
    }
}