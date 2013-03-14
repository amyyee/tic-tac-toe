
import java.util.ArrayList;
import java.util.Random;


public class George extends Player {
	
	George() {
		this.name = "George";
	}
	public George(String name) {
		this.name = name;
	}
	George(TicTacToe game) {
		super(game);
		this.name = "George";
	}
	George(TicTacToe game, int value) {
		super(game, value);
		this.name = "George";
	}

	@Override
	public Position move() {		
		Board b = game.getBoard();
		// randomly select any available position
		Random r = new Random(System.currentTimeMillis());
		ArrayList<Position> emptyPositions = b.getEmptyPositions();
		
		int rand =  r.nextInt(emptyPositions.size());
		Position p =  b.getEmptyPositions().get(rand);
		//System.out.println(this.name + " selected row: " + (p.getX() + 1) + ", col:" + (p.getY()+1));
		return p;
	}

}
