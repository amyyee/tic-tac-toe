import java.util.List;
import java.util.ArrayList;
import java.util.Random;

// Champ: has the goal of always winning the game.
public class Champ extends Player {

    public Champ() {
        this.name = "Champ";
    }

    public Champ(String name) {
        this.name = name;
    }

    public Champ(TicTacToe g) {
        super(g);
        this.name = "Champ";
    }

    public Champ(TicTacToe g, int value) {
        super(g, value);
        this.name = "Champ";
    }

    @Override
    public Position move() {
        Position winningMove = game.canWin(this);

        if (winningMove != null) {
            return winningMove;
        } else {
            Board board = game.getBoard();
            // see if there is a position that opponent can win
            Position block = game.canWin(getOpponent());
            // get available corners
            List<Position> corners = game.getBoard().getEmptyCorners();

            if (block != null) {
                return block;
            } else if (corners.size() > 0) {
                return corners.get(0);
            } else if (board.getCenterPosition() != null && board.isEmpty(board.getCenterPosition())) {
                return board.getCenterPosition();
            }
            // select position that can win
            Position empty = null;


            // check diagonals
            empty = null;
            for (int i = 0; i < board.getN(); i++) {
                // get position that has empty cells or this players moves
                if (empty == null && board.isEmpty(i, i)) {
                    empty = board.getPosition(i, i);
                } else if (board.get(i, i) == getOpponent().getValue()) {
                    empty = null;
                    break;
                }
            }

            if (empty != null) {
                return empty;
            }

            empty = null;
            for (int i = 0; i < board.getN(); i++) {
                // get position that has empty cells or this players moves
                if (empty == null && board.isEmpty(board.getN() - 1 - i, i)) {
                    empty =  board.getPosition(board.getN() - 1 - i, i);
                } else if (board.get(board.getN() - 1 - i, i) == getOpponent().getValue()) {
                    empty = null;
                    break;
                }
            }

            if (empty != null) {
                return empty;
            }

            empty = null;
            // check best position for horizontal
            for (int i = 0; i < board.getN(); i++) {
                for (int j = 0; j < board.getN(); j++) {
                    // get position that has empty cells or this players moves
                    if (empty == null && board.isEmpty(i, j)) {
                        empty = board.getPosition(i, j);
                    } else if (board.get(i, j) == getOpponent().getValue()) {
                        empty = null;
                        break;
                    }
                }
                if (empty != null) {
                    return empty;
                }
            }

            // check positions for vertical
            for (int j = 0; j < board.getN(); j++) {
                for (int i = 0; i < board.getN(); i++) {
                    // get position that has empty cells or this players moves
                    if (empty == null && board.isEmpty(i, j)) {
                        empty = board.getPosition(i, j);
                    } else if (board.get(i, j) == getOpponent().getValue()) {
                        empty = null;
                        break;
                    }
                }
                if (empty != null) {
                    return empty;
                }
            }

            // randomly select any available position
            Random r = new Random(System.currentTimeMillis());
            List<Position> emptyPositions = board.getEmptyPositions();

            int rand = r.nextInt(emptyPositions.size());
            Position p = board.getEmptyPositions().get(rand);
            return p;
        }
    }
}
