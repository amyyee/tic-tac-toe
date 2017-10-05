import java.util.List;
import java.util.Random;

// Toby: has the goal of tieing the game.
public class Toby extends Player {

    public Toby() {
        this.name = "Toby";
    }

    public Toby(String name) {
        this.name = name;
    }

    public Toby(TicTacToe game) {
        super(game);
        this.name = "Toby";
    }

    public Toby(TicTacToe game, int value) {
        super(game, value);
        this.name = "Toby";
    }

    @Override
    // strategy
    // block other player from winning if they have 2 in same row,col,diagonal
    // otherwise choose location that has different row,col from previous played location
    public Position move() {
        Position block = game.canWin(getOpponent());
        Board board = game.getBoard();

        if (block != null) {
            return block;
        } else {
            Position winningMove = game.canWin(this);

            // pick position where Toby doesn't have anything in the same row or column, otherwise pick random
            List<Position> available = board.getEmptyPositions();
            // remove the best positions, winning move, center, and corners
            available.remove(winningMove);
            available.remove(board.getCenterPosition());
            for (Position corner: board.getEmptyCorners()) {
                available.remove(corner);
            }

            for (Position position : available) {
                if (!game.playerIsInRow(position.getX(), this) && !game.playerIsInCol(position.getY(), this)
                    && !position.equals(winningMove)) {
                    return position;
                }
            }

            Random r = new Random(System.currentTimeMillis());
            if (available.size() > 0) {
                // get random
                int rand = r.nextInt(available.size());
                return available.get(rand);
            }

            // otherwise return random of available positions left
            List<Position> nonWinningPositions = board.getEmptyCorners();
            nonWinningPositions.remove(winningMove);
            if (!nonWinningPositions.isEmpty()) {
                int rand = r.nextInt(nonWinningPositions.size());
                return nonWinningPositions.get(rand);
            }

            if (board.isEmpty(board.getCenterPosition())) {
                return board.getCenterPosition();
            }

            return winningMove;
        }
    }

}
