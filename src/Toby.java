import java.util.ArrayList;


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
		
		if (block != null)
			return block;
		// check if center is empty
		else if (game.getBoard().isEmpty(new Position(1,1)))
			return new Position(1,1);
		else {
			Position winningMove = game.canWin(this);
			ArrayList<Position> availableCorners = game.getBoard().getEmptyCorners();
			availableCorners.remove(winningMove);
			if(availableCorners.size() > 0)
				return availableCorners.get(0);
			else {
				ArrayList<Position> available = game.getBoard().getEmptyPositions();
				available.remove(winningMove);
				if (available.size() > 0)
					return available.get(0);
			}
				return winningMove;
		}
	}

}
