package TP2;

import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class Actions {
    int a1 = 0;
    int a2 = 0;
    int b1 = 0;
    int b2 = 0;
    int bateaux = 0;

    int[][] coordinates = new int[10][10];
    int[][] attacks = new int[10][10];
    boolean placed = false;
    Actions() {
        this.coordinates = coordinates;
        this.placed =placed;
        this.bateaux = bateaux;
        this.attacks = attacks;
    }


    public void Placeship(Integer[] taille) {
        Integer[] size = taille;


        for (int a : size) {
            a1=0;
            a2=0;
            b1=0;
            b2 = 0;
            System.out.println(a+"   "+a1+a2+b1+b2);
            while (Verifyconditions(a1, b1, a2, b2, a) == false) {
                System.out.println();
                System.out.println();

                System.out.println("Taille du bateau : " + a);

                Scanner scanner = new Scanner(System.in);
                System.out.println("Rentrez les premières coordonnées du bateau: ");
                System.out.print("Ligne : ");
                a1 = scanner.nextInt();
                System.out.print("Colonne : ");
                a2 = scanner.nextInt();


                System.out.println("Rentrez les dernières coordonnées du bateau: ");
                System.out.print("Ligne : ");
                b1 = scanner.nextInt();
                System.out.print("Colonne : ");
                b2 = scanner.nextInt();


            }

            Printcoordinates();
            System.out.println();

        }

        this.placed = true;


    }

    public boolean Verifyconditions(int a1, int b1, int a2, int b2, int size) {
        if (a1 != b1) {
            if (a1 <= 10 && a1 >= 1 && b1 <= 10 && b1 >= 1 && Math.abs(b1 - a1) == size - 1) {
                for (int i = a1; i <= b1; i++) {
                    System.out.println(i);

                    if (Alreadyplaced(i, a2)) {
                        this.coordinates[i-1][a2-1] = 1;
                    } else {
                        System.out.println("Dimensions incorrectes!");
                        return false;
                    }
                }
                return true;
            }
        }

        if (a2 != b2) {
            if (a2 <= 10 && a2 >= 1 && b2 <= 10 && b2 >= 1 && Math.abs(b2 - a2) == size - 1) {
                for (int i = a2; i <= b2; i++) {

                    if (Alreadyplaced(a1, i)) {
                        this.coordinates[a1-1][i-1] = 1;
                    } else {
                        System.out.println("Dimensions incorrectes!");

                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public boolean Alreadyplaced(int i, int j) {
        System.out.println(this.coordinates[i-1][j-1]);
        if (this.coordinates[i-1][j-1] == 1) {
            System.out.println();
            System.out.println("Bateau déjà placé ici!");
            return false;
        }
        return true;
    }

    public int[][] Getcoordinates() {
        return this.coordinates;
    }

    public int[][] Getattacks() {
        return this.attacks;
    }

    public void Printcoordinates(){
        for (int i=0;i<10;i++){
            System.out.println("");
            for (int j=0;j<10;j++){
                System.out.print(this.coordinates[i][j]);
            }
        }
    }

    public void Attack(Player joueur_actif, Player p){
        System.out.println(joueur_actif.id);
        this.attacks = joueur_actif.attacks;
        joueur_actif.publicgrid.Printgrid();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Rentrez les coordonnées de l'attaque: ");
        System.out.print("Ligne : ");
        a1 = scanner.nextInt()-1;
        System.out.print("Colonne : ");
        a2 = scanner.nextInt()-1;

        if (p.selfgrid.action.coordinates[a1][a2] == 1){
            this.attacks[a1][a2] = 1;
            System.out.println("Touché!");
            p.action.coordinates[a1][a2] = 0;
        }
        else {
            this.attacks[a1][a2] = 2;
            System.out.println("Raté...");
        }
        System.out.println("");
        joueur_actif.attacks = this.attacks;
    }

    public int Shipsremaining(){
        this.bateaux = 0;
        for (int i=0; i<10; i++){
            for (int j=0; j<10; j++){

                if (this.coordinates[i][j]==1){
                    this.bateaux += 1;
                }
            }
        }
        return this.bateaux;
    }




}



