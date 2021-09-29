package TP2;

import java.util.Scanner;

public class Game {
    boolean finished;
    Player p1,p2;
    String command;
    Player enemy;
    boolean end,close;
    Game(Player p1, Player p2){
        this.p1 = p1  ;
        this.p2 = p2;
        this.finished = false;
        this.enemy = this.p1;
        this.end =false;
        this.close = false;
    }

    public void Play(Player p){
        if (p==this.p1){this.enemy = this.p2;}
        else {this.enemy = this.p1;}

        String s1 = "/sg";
        String s2 = "/sr";
        String s3 = "/at";


        System.out.println(p.id);

        if (this.end){relaunch();}
        else{
            if (p.action.placed == true) {

                Scanner scanner = new Scanner(System.in);
                System.out.println("Entrer commande (/sg (see your grid),/sr (ships remaining),/at (attack))");
                System.out.print("Commande : ");
                command = scanner.next();
                System.out.println(command.equals(s1));

                if (command.equals((s1))) {
                    p.selfgrid.Printgrid();
                }
                if (command.equals((s2))) {

                    System.out.println("Bateaux restants : "+p.action.Shipsremaining());
                }
                if (command.equals((s3))) {
                    p.action.Attack(p,this.enemy);
                    System.out.println("affiche la grille");

                }
            }
            else {
                p.selfgrid.Printgrid();
                System.out.println("affiche la grille");
            }
            if (p.action.placed && this.enemy.action.placed) {
                Endgame(p);
            }
        }
    }

    public void Endgame(Player p){
        if (p.action.Shipsremaining()==0) {
            System.out.println(p.id+" a perdu");
            this.end = true;

        }
        else if(this.enemy.action.Shipsremaining()==0){
            System.out.println(this.enemy.id+" a perdu");
            this.end = true;
        }
    }

    public void relaunch(){
        System.out.println("Voulez-vous rejouer?");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choix (oui/non) : ");
        command = scanner.next();

        if (command.equals("oui")){
            this.end=false;
            this.p1 = new Player(this.p1.id);
            this.p2 = new Player(this.p2.id);


        }
        else {this.close=true;}
    }

    public void MainGame(Player joueurun, Player joueurdeux){
        this.p1 = joueurun;
        this.p2 = joueurdeux;
        while (this.close==false) {
            Play(this.p1);
            Play(this.p2);
        }
    }
}



