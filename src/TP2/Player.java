package TP2;

public class Player {
    String id;
    Actions action = new Actions();
    Grid selfgrid = new Grid(true,action);
    Grid publicgrid = new Grid(false,action);
    int[][] attacks = new int[10][10];



    Player(String id){

        this.selfgrid = selfgrid;
        this.publicgrid = publicgrid;
        this.action = action;
        this.id = id;
        this.attacks = attacks;
    }
}
