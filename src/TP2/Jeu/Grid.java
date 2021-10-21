package TP2.Jeu;

public class Grid {

    char[][] grid = new char[10][10];


    boolean visible;
    char water = 'W';
    char missed = 'O';
    char hitted = 'X';
    char ship = 'S';
    char nohit = '-';

    int[][] grille;


    public Grid(boolean visible) {
        this.visible = visible;


    }

    public String Printgrid(int[][] grille) {
        String str = "";


        str += "    ";
        for (int i = 0; i < 10; i++) {
            str+=(i + 1 + " ");
        }
        str += "\n";
        for (int row = 0; row < 10; row++) {
            if (row < 9) {
                str += (" " + (row + 1) + " ");
            } else {
                str += (row + 1 + " ");
            }

            if (this.visible == true) {
                for (int col = 0; col < 10; col++) {
                    grid[row][col] = water;
                    if (grille[row][col] == 1) {
                        grid[row][col] = ship;
                    }

                    str += ("|" + grid[row][col]);
                }
            }
            else {
                for (int col = 0; col < 10; col++) {
                    grid[row][col] = nohit;
                    if (grille[row][col] == 1) {
                        grid[row][col] = hitted;
                    }
                    else if (grille[row][col] == 2) {
                        grid[row][col] = missed;
                    }

                    str +=("|" + grid[row][col]);
                }
            }
            str += "\n";
        }

        return str;
    }
}












