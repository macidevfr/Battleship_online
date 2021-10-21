package TP2;

import TP2.Client.Client;

import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class Actions {
    int a1;
    int a2;
    int b1;
    int b2;
    int bateaux = 0;
    private int[][] coordinates;

    boolean placed = false;
    public Actions() {
        this.placed =placed;
        this.bateaux = bateaux;
        this.coordinates = new int[10][10];
    }

    public int[][] getCoordinates() {
        return coordinates;
    }

    public void Placeship(int a, Client client) {


            a1=1;
            a2=1;
            b1=1;
            b2 = 1;
            while (Verifyconditions(a1, b1, a2, b2, a, coordinates) == false) {
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
            System.out.println("Placement réussi");



        this.placed = true;


    }

    public boolean Verifyconditions(int a1, int b1, int a2, int b2, int size, int[][] coordinates) {
        if (a1<=0 || a1>10 || a2<=0 || a2>10 || b1<=0 || b1>10 || b2<=0 || b2>10 ){
            System.out.println("Les coordonnées doivent être comprises entre 1 et 10");
            return false;
        }
        if (a1 < b1) {
            if (Math.abs(b1 - a1) == size - 1) {
                for (int i = a1; i <= b1; i++) {
                    if (Alreadyplaced(i, a2, coordinates)) {
                        coordinates[i-1][a2-1] = 1;
                    } else {
                        System.out.println("Il y a déjà un bateau ici!");
                        return false;
                    }
                }
                return true;
            }
            else {
                System.out.println("Dimensions incorrectes");
                return false;
            }
        }
        if (a1 > b1) {
            if (Math.abs(b1 - a1) == size - 1) {
                for (int i = a1; i >= b1; i--) {
                    if (Alreadyplaced(i, a2, coordinates)) {
                        coordinates[i-1][a2-1] = 1;
                    } else {
                        System.out.println("Il y a déjà un bateau ici!");
                        return false;
                    }
                }
                return true;
            }
            else {
                System.out.println("Dimensions incorrectes");
                return false;
            }
        }

        if (a2 < b2) {
            if (Math.abs(b2 - a2) == size - 1) {
                for (int i = a2; i <= b2; i++) {

                    if (Alreadyplaced(a1, i, coordinates)) {
                        coordinates[a1-1][i-1] = 1;
                    } else {
                        System.out.println("Il y a déjà un bateau ici!");

                        return false;
                    }
                }
                return true;
            }
            else {
                System.out.println("Dimensions incorrectes");
                return false;
            }
        }
        if (a2 > b2) {
            if (Math.abs(b2 - a2) == size - 1) {
                for (int i = a2; i >= b2; i--) {

                    if (Alreadyplaced(a1, i, coordinates)) {
                        coordinates[a1-1][i-1] = 1;
                    } else {
                        System.out.println("Il y a déjà un bateau ici!");
                        return false;
                    }
                }
                return true;
            }
            else {
                System.out.println("Dimensions incorrectes");
                return false;
            }
        }
        return false;
    }

    public boolean Alreadyplaced(int i, int j, int[][] coordinates) {
        if (coordinates[i-1][j-1] == 1) {
            System.out.println();
            System.out.println("Bateau déjà placé ici!");
            return false;
        }
        return true;
    }



    public int Shipsremaining(int[][] tab){
        int bat = 0;
        for (int i=0; i<10; i++){
            for (int j=0; j<10; j++){

                if (tab[i][j]==1){
                    this.bateaux += 1;
                }
            }
        }
        return bat;
    }




}


