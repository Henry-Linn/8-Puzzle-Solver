import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

// A solver based on the A* algorithm for the 8-puzzle and its generalizations.
public class Solver {
   private LinkedStack<Board> solution;
   private int moves;
   
    // Helper search node class.
    private class SearchNode {
        private int move;
        private Board board;
        private SearchNode previous;

        SearchNode(Board board, int moves, SearchNode previous) {
            this.board = board;
            move = moves;
            this.previous = previous;
        }
    }
     
    // Find a solution to the initial board (using the A* algorithm).
    public Solver(Board initial) {
        if (initial == null)
        {
            throw new NullPointerException("Board cannot be empty!");
        } else if (!initial.isSolvable())
        {
            throw new IllegalArgumentException("This board is unsolvable!");
        }
        solution = new LinkedStack<Board>();
        moves = 0;
        MinPQ<SearchNode> pq = new MinPQ<SearchNode>(new ManhattanOrder());
        SearchNode ok = new SearchNode(initial, 0, null);
        pq.insert(ok);
        while (!pq.isEmpty())
        {
            SearchNode no = pq.delMin();
            if (no.board.isGoal())
            {
                moves = no.move;
                for (SearchNode a = no; a.previous != null; a = a.previous)
                {
                    solution.push(a.board);
                }
                break;
            
            } else {
                for (Board x : no.board.neighbors()) {

                    if (no.previous == null)
                    {
                        SearchNode q = new SearchNode(x, 1, no);
                        pq.insert(q);
                    } else if (!no.previous.board.equals(x)) {
                        SearchNode q = new SearchNode(x, no.move+1, no);
                        pq.insert(q);
                    }
                 }
             }
        }
      }

    // The minimum number of moves to solve the initial board.
    public int moves() {
        return moves;
    }

    // Sequence of boards in a shortest solution.
    public Iterable<Board> solution() {
        return solution;
    }

    // Helper hamming priority function comparator.
    private static class HammingOrder implements Comparator<SearchNode> {
        public int compare(SearchNode a, SearchNode b)
        {
            return (a.board.hamming() + a.move) - (b.board.hamming() + b.move);
        }
    }
       
    // Helper manhattan priority function comparator.
    private static class ManhattanOrder implements Comparator<SearchNode> {
         public int compare(SearchNode a, SearchNode b)
         {
             return (a.board.manhattan() + a.move) - (b.board.manhattan() 
             + b.move);
         }
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(args[0]));
        int N = in.nextInt();
        int[][] tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tiles[i][j] = in.nextInt();
            }
        }
        Board initial = new Board(tiles);
        if (initial.isSolvable()) {
            Solver solver = new Solver(initial);
            System.out.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()) {
                System.out.println(board);
            }
        }
        else {
            System.out.println("Unsolvable puzzle");
        }
        in.close();
    }
}
