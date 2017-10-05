import java.util.Scanner;

public class TicTacToe {

    private static final char PLAYER1_CELL = 'X';
    private static final char PLAYER2_CELL = 'O';
    private static final char EMPTY_CELL = ' ';

    private static final int EMPTY = 0;
    private static final int PLAYER1_VAL = 1;
    private static final int PLAYER2_VAL = -1;

    private static final char COL_SEPARATOR = '|';
    private static final char ROW_SEPARATOR = '-';

    private static final String[] opponents = {"Champ", "Toby", "George"};

    private Board board;
    private Player player1;
    private Player player2;
    private Scanner scInput;
    private int movesLeft;

    TicTacToe() { // default constructor is command line
        board = new Board();
        scInput = new Scanner(System.in);

        player1 = new Human(this, PLAYER1_VAL, scInput);
        player2 = new George(this, PLAYER2_VAL); // defaultPlayer = George
        movesLeft = board.getN() * board.getN();

        while (printMenu()) {
            ;
        }
    }

    TicTacToe(Player p1, Player p2) { // takes 2 players // assuming is computer player
        board = new Board();

        this.player1 = p1;
        this.player1.setValue(PLAYER1_VAL);
        this.player1.setGame(this);
        this.player2 = p2;
        this.player2.setValue(PLAYER2_VAL);
        this.player2.setGame(this);
        movesLeft = board.getN() * board.getN();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    // start new game
    public void startGame() {
        board.clearBoard();
        movesLeft = board.getN() * board.getN();
    }

    public void quitGame() {
        scInput.close();
    }

    // display the text based menu
    public boolean printMenu() {
        System.out.println("=====================================");
        System.out.println("1 - Start new game");
        System.out.println("2 - Select an opponent");
        System.out.println("3 - Complete a move");
        System.out.println("4 - Determine if there is a winner or if game is not winnable");
        System.out.println("5 - Quit game");
        System.out.println("=====================================");

        String response = "";
        do {
            System.out.println("Please select an item 1 to 5 from menu:");
            System.out.print(">");
            response = scInput.next();
        } while (!response.matches("[12345]"));

        int responseInt = new Integer(response);
        switch (responseInt) {
            case 1:
                startGame();
                break;
            case 2:
                player2 = choosePlayer();
                player2.setValue(PLAYER2_VAL);
                break;
            case 3:
                makeMove();
                break;
            case 4:
                displayResults();
                break;
            case 5:
                quitGame();
                return false;
        }

        return true;
    }

    // select player from command line
    public Player choosePlayer() {
        System.out.println("Please select your opponent (1-3):");
        for (int i = 1; i <= opponents.length; i++) {
            String selected = "";
            if (opponents[i - 1].equals(player2.getName())) {
                selected = " (current)";
            }
            System.out.println(i + "." + opponents[i - 1] + selected);
        }

        String response;
        do {
            System.out.print("Input >");
            response = scInput.next();
        } while (!response.matches("[123]"));

        //return appropriate player
        int player = new Integer(response).intValue();
        switch (player) {
            case 1:
                System.out.println("You selected Champ");
                return new Champ(this);
            case 2:
                System.out.println("You selected Toby");
                return new Toby(this);
            case 3:
                System.out.println("You selected George");
                return new George(this);
        }

        return null;
    }

    public void play() {
        Player p = player1;
        while (getWinner() == null && movesLeft > 0) {
            board.addMove(p.move(), p.getValue());
            if (p.equals(player1)) {
                p = player2;
            } else {
                p = player1;
            }
            //printBoard();
            movesLeft--;
        }
    }

    // have user input move, and play computers move if game is not over
    public void makeMove() {
        // check if game is over
        if (checkWin()) {
            System.out.println("Game is over. Please start new game.");

        } else if (movesLeft <= 0) {
            System.out.println("Game is over. No more moves");

        } else {
            // game is not over, player1 make move
            printBoard();
            board.addMove(player1.move(), player1.getValue());
            movesLeft--;
            if (checkWin()) {
                System.out.println(player1.getName() + " won!");
            } else {

                // game is not over, player2 make move
                if (movesLeft > 0) {
                    board.addMove(player2.move(), player2.getValue());
                    movesLeft--;
                    if (checkWin()) {
                        System.out.println(player2.getName() + " won!");
                    }
                }
            }

        }
        printBoard();
    }

    // prints the tictactoe board
    public void printBoard() {
        for (int row = 0; row < board.getN(); row++) {
            for (int col = 0; col < board.getN(); col++) {
                if (board.get(row, col) == PLAYER1_VAL) {
                    System.out.print(PLAYER1_CELL);
                } else if (board.get(row, col) == PLAYER2_VAL) {
                    System.out.print(PLAYER2_CELL);
                } else {
                    System.out.print(EMPTY_CELL);
                }

                if (col < (board.getN() - 1)) {
                    System.out.print(COL_SEPARATOR);
                }

            }
            System.out.println();
            if (row < (board.getN() - 1)) {
                for (int i = 0; i < (2 * board.getN() - 1); i++) {
                    System.out.print(ROW_SEPARATOR);
                }
                System.out.println();
            }
        }

        System.out.println();
    }

    // returns true if there is a winner, false otherwise
    public boolean checkWin() {
        return getWinner() != null;
    }

    // display if there is a winner, or if game is not winnable
    public void displayResults() {
        Player winner = getWinner();
        if (winner == null && movesLeft <= 0) {
            System.out.println("Draw. No winner");
        } else if (winner != null) {
            System.out.println(winner.getName() + " won!");
        } else {
            System.out.print("Game still in process. ");
            if (isWinnable()) {
                System.out.println("Game is winnable");
            } else {
                System.out.println("Game is not winnable, will end in draw");
            }
        }

    }

    // check win
    public Player getWinner() {
        int sumDiag1 = 0;
        int sumDiag2 = 0;
        for (int i = 0; i < board.getN(); i++) {
            int sumRow = 0;
            int sumCol = 0;
            for (int j = 0; j < board.getN(); j++) {
                sumRow += board.get(i, j);
                sumCol += board.get(j, i);
                if (i == j) { // only calculate diagonals once
                    sumDiag1 += board.get(i, i);
                    sumDiag2 += board.get(i, board.getN() - i - 1);
                }
            }

            if (Math.abs(sumRow) == board.getN()) {
                return sumRow > 0 ? player1 : player2;
            } else if (Math.abs(sumCol) == board.getN()) {
                return sumCol > 0 ? player1 : player2;
            } else if (Math.abs(sumDiag1) == board.getN()) {
                return sumDiag1 > 0 ? player1 : player2;
            } else if (Math.abs(sumDiag2) == board.getN()) {
                return sumDiag2 > 0 ? player1 : player2;
            }
        }
        // no winner
        return null;
    }

    // returns Position that Player p can play to win the game
    public Position canWin(Player p) {
        int sumDiag1 = 0;
        int sumDiag2 = 0;
        for (int i = 0; i < board.getN(); i++) {
            int sumRow = 0;
            int sumCol = 0;
            for (int j = 0; j < board.getN(); j++) {
                sumRow += board.get(i, j);
                sumCol += board.get(j, i);
                if (i == j) // only calculate diagonals once
                {
                    sumDiag1 += board.get(i, i);
                    sumDiag2 += board.get(i, board.getN() - i - 1);
                }
            }

            if (sumRow == (board.getN() - 1) * p.getValue()) {
                // get empty cell in row i
                for (int col = 0; col < board.getN(); col++) {
                    if (board.isEmpty(i, col)) {
                        return board.getPosition(i, col);
                    }
                }
            } else if (sumCol == (board.getN() - 1) * p.getValue()) {
                // get empty cell in col i
                for (int row = 0; row < board.getN(); row++) {
                    if (board.isEmpty(row, i)) {
                        return board.getPosition(row, i);
                    }
                }
            } else if (sumDiag1 == (board.getN() - 1) * p.getValue()) {
                //get empty cell in diagonal 1
                for (int a = 0; a < board.getN(); a++) {
                    if (board.isEmpty(a, a)) {
                        return board.getPosition(a, a);
                    }
                }
            } else if (sumDiag2 == (board.getN() - 1) * p.getValue()) {
                //get empty cell in diagonal 2
                for (int a = 0; a < board.getN(); a++) {
                    if (board.isEmpty(a, board.getN() - 1 - a)) {
                        return board.getPosition(a, a);
                    }
                }
            }
        }
        // no winning move
        return null;
    }

    // returns
    // true: if game is still winnable by either player 1 or player2
    // false: if game will end in draw. this is when every row, col, and diag as both player 1 and player 2 occupied
    //        in one of the positions
    public boolean isWinnable() {
        boolean hasPlayer1;
        boolean hasPlayer2;
        for (int i = 0; i < board.getN(); i++) {
            hasPlayer1 = false;
            hasPlayer2 = false;
            for (int j = 0; j < board.getN(); j++) {
                if (board.get(i, j) == player1.getValue()) {
                    hasPlayer1 = true;
                } else if (board.get(i, j) == player2.getValue()) {
                    hasPlayer2 = true;
                }

            }
            if (!(hasPlayer1 && hasPlayer2)) {
                return true;
            }

        }
        for (int j = 0; j < board.getN(); j++) {
            hasPlayer1 = false;
            hasPlayer2 = false;
            for (int i = 0; i < board.getN(); i++) {
                if (board.get(i, j) == player1.getValue()) {
                    hasPlayer1 = true;
                } else if (board.get(i, j) == player2.getValue()) {
                    hasPlayer2 = true;
                }
            }
            if (!(hasPlayer1 && hasPlayer2)) {
                return true;
            }
        }


        hasPlayer1 = false;
        hasPlayer2 = false;
        for (int i = 0; i < board.getN(); i++) {
            if (board.get(i, i) == player1.getValue()) {
                hasPlayer1 = true;
            } else if (board.get(i, i) == player2.getValue()) {
                hasPlayer2 = true;
            }
        }
        if (!(hasPlayer1 && hasPlayer2)) {
            return true;
        }

        hasPlayer1 = false;
        hasPlayer2 = false;
        for (int i = 0; i < board.getN(); i++) {
            if (board.get(i, board.getN() - 1 - i) == player1.getValue()) {
                hasPlayer1 = true;
            } else if (board.get(i, board.getN() - 1 - i) == player2.getValue()) {
                hasPlayer2 = true;
            }
        }
        if (!(hasPlayer1 && hasPlayer2)) {
            return true;
        }

        return false;
    }

    public boolean playerIsInRow(int row, Player player) {
        for (int i = 0 ; i < board.getN(); i++) {
            if (board.get(row, i) == player.getValue()) {
                return true;
            }
        }
        return false;
    }

    public boolean playerIsInCol(int col, Player player) {
        for (int i = 0 ; i < board.getN(); i++) {
            if (board.get(i, col) == player.getValue()) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
    }
}
