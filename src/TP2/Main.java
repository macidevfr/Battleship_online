package TP2;

public class Main {
    public static void main( String[] args )
    {
        Player joueurun= new Player("mael");
        Player joueurdeux= new Player("rayane");

        Game partie = new Game(joueurun,joueurdeux);

        partie.MainGame(joueurun,joueurdeux);


    }
}
