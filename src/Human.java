import java.util.Scanner;

public class Human extends Player {
    private Scanner scanner;

    public Human() {
        super();
        this.name = "You";
        scanner = new Scanner(System.in);
    }

    public Human(TicTacToe game, int value) {
        super(game, value);
        this.name = "You";
        scanner = new Scanner(System.in);
    }

    public Human(TicTacToe game, int value, Scanner scanner) {
        super(game, value);
        this.name = "You";
        this.scanner = scanner;
    }

    @Override
    public Position move() {
        Board b = game.getBoard();

        int row;
        int col;
        do {
            System.out.print("Please enter row (1-" + b.getN() + ")>");
            row = scanner.nextInt();
        } while (row <= 0 || row > b.getN());

        do {
            System.out.print("Please enter col (1-" + b.getN() + ")>");
            col = scanner.nextInt();
        } while (col <= 0 || col > b.getN());

        if (!b.isEmpty(row - 1, col - 1)) {
            System.out.println("Cell is already occupied. Try again.");
            return move();
        } else {
            System.out.println("You selected row:" + row + ", col:" + col);
        }
        return new Position(row - 1, col - 1);
    }
}
