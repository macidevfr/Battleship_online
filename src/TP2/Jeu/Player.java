package TP2.Jeu;

import TP2.Actions;

public class Player {
    private String id;
    private Actions action = new Actions();
    private Grid selfgrid = new Grid(true);
    private Grid publicgrid = new Grid(false);
    private int[][] attacks = new int[10][10];



    public Player(String id){

        this.selfgrid = selfgrid;
        this.publicgrid = publicgrid;
        this.action = action;
        this.id = id;
        this.attacks = attacks;
    }

    public String getId() {
        return id;
    }

    public Actions getAction() {
        return action;
    }

    public Grid getSelfgrid() {
        return selfgrid;
    }

    public Grid getPublicgrid() {
        return publicgrid;
    }

    public int[][] getAttacks() {
        return attacks;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAction(Actions action) {
        this.action = action;
    }

    public void setSelfgrid(Grid selfgrid) {
        this.selfgrid = selfgrid;
    }

    public void setPublicgrid(Grid publicgrid) {
        this.publicgrid = publicgrid;
    }

    public void setAttacks(int[][] attacks) {
        this.attacks = attacks;
    }
}
