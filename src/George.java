import java.util.List;
import java.util.ArrayList;
import java.util.Random;

// George: plays randomly. Each of these players is attempting to maximizing the probability of reaching it's
//   desired end state with each move it makes.
public class George extends Player {

    public George() {
        this.name = "George";
    }

    public George(String name) {
        this.name = name;
    }

    public George(TicTacToe game) {
        super(game);
        this.name = "George";
    }

    public George(TicTacToe game, int value) {
        super(game, value);
        this.name = "George";
    }

    @Override
    public Position move() {
        Board b = game.getBoard();
        // randomly select any available position
        Random r = new Random(System.currentTimeMillis());
        List<Position> emptyPositions = b.getEmptyPositions();

        int rand = r.nextInt(emptyPositions.size());
        Position p = b.getEmptyPositions().get(rand);
        //System.out.println(this.name + " selected row: " + (p.getX() + 1) + ", col:" + (p.getY()+1));
        return p;
    }

}
