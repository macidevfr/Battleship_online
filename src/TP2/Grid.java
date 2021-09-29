package TP2;

public class Grid {
    Actions action;
    char[][] grid = new char[10][10];
    boolean visible;
    char water = 'W';
    char missed = 'O';
    char hitted = 'X';
    char ship = 'S';
    char nohit = '-';
    //Integer[] size = new Integer[]{5, 3, 2, 3, 4};
    Integer[] size = new Integer[]{2};



    public Grid(boolean visible,Actions action) {
        this.visible = visible;
        this.action = action;
    }

    public void Printgrid() {
        int[][] ships = this.action.Getcoordinates();
        int[][] attacks = this.action.Getattacks();

        System.out.print("    ");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println("");
        for (int row = 0; row < 10; row++) {
            if (row < 9) {
                System.out.print(" " + (row + 1) + " ");
            } else {
                System.out.print(row + 1 + " ");
            }

            if (this.visible == true) {
                for (int col = 0; col < 10; col++) {
                    grid[row][col] = water;
                    if (ships[row][col] == 1) {
                        grid[row][col] = ship;
                    }

                    System.out.print("|" + grid[row][col]);
                }
            }
            else {
                for (int col = 0; col < 10; col++) {
                    grid[row][col] = nohit;
                    if (attacks[row][col] == 1) {
                        grid[row][col] = hitted;
                    }
                    else if (attacks[row][col] == 2) {
                        grid[row][col] = missed;
                    }

                    System.out.print("|" + grid[row][col]);
                }
            }
            System.out.println();
        }



        while (action.placed == false) {
            action.Placeship(size);
            Printgrid();
            System.out.println();
        }
    }
}












