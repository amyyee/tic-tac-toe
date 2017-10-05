import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final int EMPTY = 0;
    private static final int DEFAULT_SIZE = 3;

    private int n;
    private int[][] board;
    private Position[][] positions;

    // default board is 3 x 3
    Board() {
        this.n = DEFAULT_SIZE;

        initBoard();
    }

    // create an n x n board
    Board(int n) {
        this.n = n;

        initBoard();
    }

    public void initBoard() {
        this.board = new int[n][n];
        this.positions = new Position[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                positions[i][j] = new Position(i,j);
            }
        }
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

    public Position getPosition(int row, int col) {
        return positions[row][col];
    }

    public List<Position> getEmptyPositions() {
        List<Position> emptyCells = new ArrayList<Position>();

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (board[row][col] == EMPTY) {
                    emptyCells.add(positions[row][col]);
                }
            }
        }

        return emptyCells;
    }

    public List<Position> getEmptyCorners() {
        List<Position> empty = new ArrayList<Position>();

        Position[] corners = {positions[0][0], positions[0][n-1], positions[n-1][0], positions[n-1][n-1]};

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
            return positions[n/2][n/2];
        }
    }
}
