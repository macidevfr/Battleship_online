package TP2.Serveur;

import TP2.Jeu.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

public class ThreadGame extends Thread {

    private Socket socket;
    private boolean attacked;
    private BufferedReader br;
    private PrintWriter pw;
    private Serveur serveur;
    int id;
    private boolean placeboat;
    private boolean ready;

    public ThreadGame(Socket socket, Serveur serveur, int id) {

        try {
            this.socket = socket;
            this.serveur = serveur;
            this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.pw = new PrintWriter(socket.getOutputStream(), true);
            this.id = id;
            this.placeboat = false;
            this.ready = false;
            this.attacked = false;
        } catch (Exception e) {
        }
    }


    public int[][] Parse(String str) {
        int[][] coor = new int[10][10];

        String[] parts = str.split(" ");
        String str2 = parts[1];
        String[] chiffres2 = str2.split(",");
        int index = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                coor[i][j] = Integer.parseInt(chiffres2[index]);
                index += 1;
            }
        }
        return coor;
    }

    private void Attack(String str) {
        String[] parts = str.split(",");
        int[][] att = new int[10][10];
        int a1 = Integer.parseInt(parts[1]) - 1;
        int a2 = Integer.parseInt(parts[2]) - 1;
        int[][] def;
        System.out.println("idattaque : " + this.id + " " + a1 + " " + a2);
        if (this.id == 1) {
            def = this.serveur.getCoordinatesp2();
            att = this.serveur.getAttacksp1();

        } else {
            def = this.serveur.getCoordinatesp1();
            att = this.serveur.getAttacksp2();
        }

        if (def[a1][a2] == 1) {
            this.pw.println("Touché," + a1 + "," + a2);
            def[a1][a2] = 0;
            att[a1][a2] = 1;

        } else {
            this.pw.println("Coulé," + a1 + "," + a2);
            att[a1][a2] = 2;

        }
        if (this.id == 1) {
            this.serveur.setAttacksp1(att);
            this.serveur.setCoordinatesp2(def);
        }
        if (this.id == 2) {
            this.serveur.setAttacksp2(att);
            this.serveur.setCoordinatesp1(def);

        }


    }

    public boolean hasWin(int[][] tab) {
        int bat = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (tab[i][j] == 1) bat += 1;
            }
        }

        if (bat == 0) return true;
        else return false;

    }

    public void Turn() throws InterruptedException {
        if (this.serveur.isAttacking() == true) {
            if (hasWin(this.serveur.getCoordinatesp1())) {
                if (this.id == 1) this.pw.println("Tu as perdu");
                if (this.id == 2) this.pw.println("Tu as gagné");
                Thread.sleep(10);
                this.serveur.setEndgame(true);
                return;
            }
            if (hasWin(this.serveur.getCoordinatesp2())) {
                if (this.id == 2) this.pw.println("Tu as perdu");
                if (this.id == 1) this.pw.println("Tu as gagné");
                Thread.sleep(10);
                this.serveur.setEndgame(true);
                return;
            }

            if (this.id == this.serveur.getActive()) {
                this.pw.println("A toi de jouer");
                this.ready = false;
            } else if (this.id != this.serveur.getActive()) {
                this.pw.println("Ton adversaire est en train de jouer");
                Thread.sleep(100);
            }
            this.serveur.setSendattack(this.serveur.getSendattack() + 1);
            if (this.serveur.getSendattack() % 2 == 0) {
                this.serveur.setAttacking(false);
            }
            //}
        }
    }


    public void Actualiser() {
        String coordonnees1 = "coordonnees ";
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (this.id == 1) coordonnees1 += this.serveur.getCoordinatesp1()[i][j] + ",";
                if (this.id == 2) coordonnees1 += this.serveur.getCoordinatesp2()[i][j] + ",";
            }
        }
        this.pw.println(coordonnees1);

        String attacks1 = "attacks ";
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (this.id == 1) attacks1 += this.serveur.getAttacksp1()[i][j] + ",";
                if (this.id == 2) attacks1 += this.serveur.getAttacksp2()[i][j] + ",";
            }
        }
        this.pw.println(attacks1);
    }

    private void Relaunch() throws IOException {
        String str = this.br.readLine();
        this.placeboat = false;
        this.ready = false;
        this.attacked = false;

        if (str.equals("oui")) {
            this.serveur.setOui(this.serveur.getOui() + 1);
        }
        if (str.equals("non")) {
            this.serveur.setNon(this.serveur.getNon() + 1);
        }
        while (this.serveur.getOui()<2) {
            if (this.serveur.getNon() > 0) {

                this.socket.close();

            }
        }

        this.serveur.setOui(0);
        this.serveur.setCoordinatesp1(new int[10][10]);
        this.serveur.setCoordinatesp2(new int[10][10]);
        this.serveur.setAttacksp1(new int[10][10]);
        this.serveur.setAttacksp2(new int[10][10]);
        this.serveur.setPlaceboat(false);
        this.serveur.setAttacking(true);
        this.serveur.setSendattack(0);
        this.serveur.setPready(0);
        this.serveur.setActive(1);
        this.serveur.setEndgame(false);
        this.pw.println("Début de la partie");


    }

    public void run() {
        try {
            this.pw.println("Début de la partie");

            while (true) {

                while (serveur.isEndgame() == false) {

                    Actualiser();
                    if (this.placeboat == false) {
                        this.pw.println("Placez vos bateaux");
                        this.placeboat = true;

                    }


                    if (this.ready == false) {
                        String str = this.br.readLine();

                        if (str.contains("Attaque")) {
                            Attack(str);
                            System.out.println("actif : " + this.serveur.getActive());

                            if (this.serveur.getActive() == 1) this.serveur.setActive(2);
                            else this.serveur.setActive(1);
                            this.serveur.setAttacking(true);
                            this.ready = true;
                        }

                        if (str.contains("Bateaux")) {
                            if (this.id == 1) {
                                for (int i = 0; i < 10; i++) {
                                    for (int j = 0; j < 10; j++) {
                                    }
                                }
                                this.serveur.setCoordinatesp1(Parse(str));
                            }
                            if (this.id == 2) {
                                for (int i = 0; i < 10; i++) {
                                    for (int j = 0; j < 10; j++) {
                                    }
                                }
                                this.serveur.setCoordinatesp2(Parse(str));
                            }
                        }


                        if (str.equals("Je suis prêt")) {
                            this.serveur.setPready(this.serveur.getPready() + 1);
                            this.ready = true;
                        }


                    }
                    if (this.serveur.getPready() == 2) {
                        Turn();
                    }
                }

                Relaunch();
                run();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


    }



    }




