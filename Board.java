import edu.princeton.cs.algs4.In;

import java.util.LinkedList;

public class Board {
    // stores the size of the board
    private int size;
    // copy of the board
    private int[][] copy;
    // hamming distance
    private int ham;
    // manhatten distance
    private int manhatten;
    // private int inversions;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        if (tiles == null) {
            throw new IllegalArgumentException();
        }
        // initialzes variables
        size = tiles.length;
        copy = new int[size][size];
        ham = 0;
        manhatten = 0;
        // creates variables to get back the expected row and columns
        int expected_row;
        int expected_col;

        // copies the tiles into copy and gets back the hamming and manhatten distances
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                copy[i][j] = tiles[i][j];
                if (copy[i][j] != getId(i, j) && copy[i][j] != 0) {
                    ham++;
                    expected_row = (copy[i][j] - 1) / size;
                    expected_col = (copy[i][j] - 1) % size;
                    // if (copy[i][j] % size == 0) {
                    //     num_tilescol = copy[i][j] % size;
                    // }
                    // else {
                    //     num_tilescol = copy[i][j] % size - 1;
                    // }
                    // manhatten = (i - num_tilescol) + (j - num_tilesrow);
                    int item_manhatten = Math.abs(j - expected_col) + Math.abs(i - expected_row);

                    manhatten += item_manhatten;

                    // System.out.printf("i: %d, j: %d, item_manhatten: %d, \n", i, j, item_manhatten);

                }
            }
        }


    }

    // private method that gets the correct Id or what the correct number should be
    // in the tile
    private int getId(int row, int col) {
        if (row < 0 || row > size - 1 || col < 0 || col > size - 1) {
            throw new IllegalArgumentException("Enter a valid row and column");
        }
        return size * row + col + 1;
    }

    // string representation of this board
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(size).append("\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                s.append(String.format("%2d ", tileAt(i, j)));
                // if (j < size - 1) {
                //     s += " ";
                // }

            }
            s.append("\n");
        }
        return s.toString();

    }

    // tile at (row, col) or 0 if blank
    public int tileAt(int row, int col) {
        if (row < 0 || row > size - 1 || col < 0 || col > size - 1) {
            throw new IllegalArgumentException("Enter a valid row and column");
        }
        return copy[row][col];
    }

    // board size n
    public int size() {
        return size;
    }

    // number of tiles out of place
    public int hamming() {
        return ham;

    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return manhatten;

    }


    // is this board the goal board?
    public boolean isGoal() {
        if (manhatten == 0 && copy[size - 1][size - 1] == 0 && ham == 0) {
            return true;
        }
        return false;


    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }
        if (y.getClass() != this.getClass()) {
            return false;
        }
        Board that = (Board) y;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (copy[i][j] != that.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;

    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        LinkedList<Board> neighbor = new LinkedList<Board>();
        // variable declaration and initialization
        int blankrow = 0;
        int blankcol = 0;
        int[][] tempclone = new int[size][size];
        int holder;

        // creates a deep copy of the board and gets blankrow and blank col
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (copy[i][j] == 0) {
                    blankrow = i;
                    blankcol = j;
                }
                tempclone[i][j] = copy[i][j];
            }

        }
        // checking up
        if (blankrow - 1 >= 0) {
            // for (int i = 0; i < size; i++) {
            //     for (int j = 0; j < size; j++) {
            //         tempclone[i][j] = copy[i][j];
            //     }
            // }
            // copies the element up to the element of the blank spot
            holder = tempclone[blankrow][blankcol];
            tempclone[blankrow][blankcol] = tempclone[blankrow - 1][blankcol];
            tempclone[blankrow - 1][blankcol] = holder;
            Board upneighbor = new Board(tempclone);
            neighbor.add(upneighbor);
            // copies it back
            tempclone[blankrow][blankcol] = copy[blankrow][blankcol];
            tempclone[blankrow - 1][blankcol] = copy[blankrow - 1][blankcol];

        }
        // checking down
        if (blankrow + 1 <= size - 1) {
            // for (int i = 0; i < size; i++) {
            //     for (int j = 0; j < size; j++) {
            //         tempclone[i][j] = copy[i][j];
            //     }
            // }
            // copies the element down to the element of the blank spot
            holder = tempclone[blankrow][blankcol];
            tempclone[blankrow][blankcol] = tempclone[blankrow + 1][blankcol];
            tempclone[blankrow + 1][blankcol] = holder;
            Board downneighbor = new Board(tempclone);
            neighbor.add(downneighbor);
            // copies it back
            tempclone[blankrow][blankcol] = copy[blankrow][blankcol];
            tempclone[blankrow + 1][blankcol] = copy[blankrow + 1][blankcol];
        }
        // checking left
        if (blankcol - 1 >= 0) {
            // for (int i = 0; i < size; i++) {
            //     for (int j = 0; j < size; j++) {
            //         tempclone[i][j] = copy[i][j];
            //     }
            // }
            // copies the element left to the element of the blank spot
            holder = tempclone[blankrow][blankcol];
            tempclone[blankrow][blankcol] = tempclone[blankrow][blankcol - 1];
            tempclone[blankrow][blankcol - 1] = holder;
            Board leftneighbor = new Board(tempclone);
            neighbor.add(leftneighbor);
            // copies it back
            tempclone[blankrow][blankcol] = copy[blankrow][blankcol];
            tempclone[blankrow][blankcol - 1] = copy[blankrow][blankcol - 1];

        }
        // checking right
        if (blankcol + 1 <= size - 1) {
            // for (int i = 0; i < size; i++) {
            //     for (int j = 0; j < size; j++) {
            //         tempclone[i][j] = copy[i][j];
            //     }
            // }
            // copies the element right to the element of the blank spot
            holder = tempclone[blankrow][blankcol];
            tempclone[blankrow][blankcol] = tempclone[blankrow][blankcol + 1];
            tempclone[blankrow][blankcol + 1] = holder;
            Board rightneighbor = new Board(tempclone);
            neighbor.add(rightneighbor);
            // copies it back
            tempclone[blankrow][blankcol] = copy[blankrow][blankcol];
            tempclone[blankrow][blankcol + 1] = copy[blankrow][blankcol + 1];
        }


        return neighbor;

    }


    // is this board solvable?
    public boolean isSolvable() {
        // variable declaration
        int inversion = 0;
        int getrowzero = 0;
        // these two variables are very important. Makes sure that its only checking
        // elements in front of it
        int first = 0;
        int second = 0;


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                first++;
                for (int y = 0; y < size; y++) {
                    for (int z = 0; z < size; z++) {
                        second++;
                        if (first < second && copy[y][z] != 0) {
                            if (copy[i][j] > copy[y][z]) {
                                // System.out.printf("i: %d, j: %d, y: %d, z: %d, \n",
                                //                   i, j, y, z);
                                inversion++;
                            }
                        }
                        if (copy[y][z] == 0) {
                            getrowzero = y;
                        }
                    }
                }
                // need to set it to zero because that is after it check's all elements
                second = 0;
            }
        }

        // inversions = inversion;

        // System.out.println(inversion + "inversion");
        // System.out.println(getrowzero + "getrowzero");

        // checks for the specific even or odd boards
        if (size % 2 == 0) {
            if ((inversion + getrowzero) % 2 != 1) {
                return false;
            }
            return true;
        }
        else if (size % 2 == 1) {
            if (inversion % 2 == 1) {
                return false;
            }
            return true;
        }
        return true;


    }


    // unit testing (required)
    public static void main(String[] args) {
        In in = new In(args[0]);
        int size = in.readInt();

        int[][] test = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                test[i][j] = in.readInt();
            }
        }


        Board t = new Board(test);

        System.out.println(t.size());
        System.out.println(t.tileAt(0, 0));
        System.out.println(t.hamming());
        System.out.println(t.manhattan());
        System.out.println(t.isSolvable());
        System.out.println(t.isGoal());
        System.out.println(t.neighbors());
        System.out.println(t.toString());


    }

}
