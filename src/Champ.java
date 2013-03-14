import java.util.ArrayList;
import java.util.Random;


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
		
		if(winningMove != null)
			return winningMove;
		else {
			// see if there is a position that opponent can win
			Position block = game.canWin(getOpponent());
			// get available corners
			ArrayList<Position> corners = game.getBoard().getEmptyCorners();
			
			if (block != null)
				return block;
			else if (corners.size() > 0) 
				return corners.get(0);
			else if(game.getBoard().isEmpty(new Position(1,1)))
				return new Position(1,1);
			Board b = game.getBoard();
			// select position that can win
			Position empty = null;

			
			// check diagonals
			empty = null;
			for (int i=0; i < b.getN(); i++) {
				// get position that has empty cells or this players moves
				if (empty == null && b.isEmpty(i, i))
					empty = new Position(i,i);
				else if (b.get(i, i) == getOpponent().getValue()) {
					empty = null;
					break;
				}
			}

			if (empty != null)
				return empty;

			empty = null;
			for (int i=0; i < b.getN(); i++) {
				// get position that has empty cells or this players moves
				if (empty == null && b.isEmpty(b.getN()-1-i, i))
					empty = new Position(b.getN()-1-i,i);
				else if (b.get(b.getN()-1-i, i) == getOpponent().getValue()) {
					empty = null;
					break;
				}
			}

			if (empty != null)
				return empty;
			
			empty = null;
			// check best position for horizontal 
			for (int i=0; i < b.getN(); i++) {
				for (int j=0; j < b.getN(); j++) {
					// get position that has empty cells or this players moves
					if (empty == null && b.isEmpty(i, j))
						empty = new Position(i,j);
					else if (b.get(i, j) == getOpponent().getValue()) {
						empty = null;
						break;
					}
				}
				if (empty != null)
					return empty;
			}
			
			// check positions for vertical
			for (int j=0; j < b.getN(); j++) {
				for (int i=0; i < b.getN(); i++) {
					// get position that has empty cells or this players moves
					if (empty == null && b.isEmpty(i, j))
						empty = new Position(i,j);
					else if (b.get(i, j) == getOpponent().getValue()) {
						empty = null;
						break;
					}
				}
				if (empty != null)
					return empty;
			}
	
			// randomly select any available position
			Random r = new Random(System.currentTimeMillis());
			ArrayList<Position> emptyPositions = b.getEmptyPositions();
			
			int rand =  r.nextInt(emptyPositions.size());
			Position p =  b.getEmptyPositions().get(rand);
			return p;
		}
	}
}
