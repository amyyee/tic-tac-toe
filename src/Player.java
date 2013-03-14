abstract class Player {
	
	protected String name;
	protected TicTacToe game;
	protected int value;

	Player() {}

	Player(TicTacToe game) {
		this.game = game;
	}
	Player(TicTacToe game, int value) {
		this.game = game;
		this.value = value;
	}
	
	abstract Position move();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	public TicTacToe getGame() {
		return game;
	}
	public void setGame(TicTacToe game) {
		this.game = game;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (value != other.value)
			return false;
		return true;
	}

	public Player getOpponent() {
		if (game == null)
			return null;
		return game.getPlayer1().equals(this) ? game.getPlayer2() : game.getPlayer1();
	}

}
