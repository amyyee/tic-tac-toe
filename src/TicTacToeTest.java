
public class TicTacToeTest {
	public static void trial (Player player1, Player player2, int numTrials) {
		int player1Wins=0;
		int player2Wins=0;
		int draws=0;
		for (int i = 0; i < numTrials; i++) {
			TicTacToe game = new TicTacToe(player1,player2);
			game.play();
			if (game.getWinner() == null)
				draws++;
			else if (game.getWinner().equals(player1))
					player1Wins++;
			else
				player2Wins++;
		
		}
		
		System.out.println("Over " + numTrials + " games between " +player1.getName() + " and " + player2.getName() + ", where " +player1.getName() + " goes first");
		System.out.println("Draws: " +((double)draws/numTrials)*100 + "% of the time");
		System.out.println(player1.getName() + " wins " +((double)player1Wins/numTrials)*100 + "% of the time");
		System.out.println(player2.getName() + " wins " +((double)player2Wins/numTrials)*100 + "% of the time");
		System.out.println("=======================================");
		
		
	}
	
	public static void main(String[] args) {
			final int NUM_TRIALS = 10000;
		
			Champ champ = new Champ();
			Toby toby = new Toby();
			George george = new George();
			
			TicTacToeTest.trial(champ,toby,NUM_TRIALS);
			TicTacToeTest.trial(toby,champ,NUM_TRIALS);
			TicTacToeTest.trial(champ,george,NUM_TRIALS);
			TicTacToeTest.trial(george,champ,NUM_TRIALS);
			TicTacToeTest.trial(toby,george,NUM_TRIALS);
			TicTacToeTest.trial(george,toby,NUM_TRIALS);
			
		}
}
