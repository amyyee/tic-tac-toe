import java.util.ArrayList;
import java.util.List;
import java.util.Pair;

public class Board {
    private static final int EMPTY = 0;

    private int n;
    private int[][] board;

    // default board is 3 x 3
    Board() {
        this.n = 3;

        initBoard();
    }

    // create an n x n board
    Board(int n) {
        this.n = n;

        initBoard();
    }

    public void initBoard() {
        this.board = new int[n][n];
        clearBoard();
    }

    public void clearBoard() {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                board[row][col] = EMPTY;
            }
        }
    }

    public int getN() {
        return n;
    }

    public void setN(int rows) {
        this.n = rows;
    }

    public boolean isEmpty(int row, int col) {
        return (board[row][col] == EMPTY);
    }

    public boolean isEmpty(Position p) {
        return (board[p.getX()][p.getY()] == EMPTY);
    }

    // only add move if position at row, col is empty
    public void addMove(Position p, int value) {
        board[p.getX()][p.getY()] = value;
    }

    public int get(int row, int col) {
        return board[row][col];
    }

    public List<Position> getEmptyPositions() {
        List<Position> emptyCells = new ArrayList<Position>();

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (board[row][col] == EMPTY) {
                    emptyCells.add(new Position(row, col));
                }
            }
        }

        return emptyCells;
    }

    public List<Position> getEmptyCorners() {
        List<Position> empty = new ArrayList<Position>();

        Position[] corners = {new Position(0, 0), new Position(0, n - 1), new Position(n - 1, 0), new Position(n - 1, n - 1)};

        for (int i = 0; i < corners.length; i++) {
            if (isEmpty(corners[i])) {
                empty.add(corners[i]);
            }
        }

        return empty;

    }

    public Position getCenterPosition() {
        // there is no center position for even number n
        if (n % 2 == 0) {
            return null;
        } else {
            return new Position(n/2, n/2);
        }
    }
}
