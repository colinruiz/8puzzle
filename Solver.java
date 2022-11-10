import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.Stopwatch;

public class Solver {
    // minPQ of SearchNodes
    private MinPQ<SearchNode> minPQ;
    // gets the solution moves from the Stack
    private int solution_moves;
    // gets the potential neighbors from the board
    private Iterable<Board> potentialneigbors;
    // stack is last in first out that returns the steps to get to goal
    private Stack<Board> solution;
    // short for


    // private class that implements comparable data type of type searchNode
    private class SearchNode implements Comparable<SearchNode> {
        // stores the priority
        private int priority;
        // stores the previous searchnode
        private SearchNode previous;
        // stores the board associated with the SearchNode
        private Board board;
        // stores the moves
        private int moves;

        // SearchNode constructore
        public SearchNode(Board b, int moves, SearchNode previous) {
            this.board = b;
            this.moves = moves;
            priority = this.moves + b.manhattan();
            this.previous = previous;
        }

        // returns back the moves of that specific SearchNode
        public int move() {
            return this.moves;
        }

        // compareTo that compares two searchNodes priority.
        public int compareTo(SearchNode y) {

            if (this.priority - y.priority < 0) {
                return -1;
            }
            else if (this.priority - y.priority > 0) {
                return 1;
            }
            else {
                return 0;
            }
        }

    }


    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null || initial.isSolvable() == false) {
            throw new IllegalArgumentException();
        }

        // initializing variables
        int moves = 0;
        SearchNode node = new SearchNode(initial, moves, null);
        minPQ = new MinPQ<SearchNode>();
        // inserts node
        minPQ.insert(node);
        Board current = initial;
        solution = new Stack<Board>();
        // System.out.println(current);


        while (!current.isGoal()) {
            potentialneigbors = current.neighbors();
            node = minPQ.delMin();
            moves = node.move() + 1;
            // solution.add(node.board);
            // iterates through all the potential neighbors and see's which ones are valid
            for (Board board : potentialneigbors) {
                SearchNode neighbor = new SearchNode(board, moves, node);
                if (moves > 1) {
                    if (!(neighbor.previous.previous.board.equals(neighbor.board))) {
                        minPQ.insert(neighbor);
                    }
                }
                else {
                    minPQ.insert(neighbor);
                }

            }
            current = minPQ.min().board;
            // System.out.println(current);
        }
        // if (current.isGoal()) {
        //     while (current != initial) {
        //         solution.add(current);
        //         current = node.previous.board;
        //     }
        // }
        // System.out.println(solution);
        // works back to get the specific boards that lead to the solution
        SearchNode curr = minPQ.min();
        while (curr.previous != null) {
            solution.push(curr.board);
            curr = curr.previous;
        }
        solution.push(initial);
        solution_moves = solution.size();


    }

    // min number of moves to solve initial board
    public int moves() {


        return solution_moves - 1;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {

        // System.out.println(solution);
        return solution;


    }

    // test client (see below)
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

        Solver s = new Solver(t);

        Stopwatch timer = new Stopwatch();

        // System.out.println(s);
        // System.out.println(s.moves());
        System.out.println(s.solution());
        System.out.println(timer.elapsedTime());


    }

}


