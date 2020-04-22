import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class SnakeAndLadders {

    static String[][] field = new String[10][10];
    static HashMap<Integer, Integer> snakes = new HashMap<>();
    static HashMap<Integer, Integer> ladders = new HashMap<>();
    boolean isGameover = false;

    static {
        snakes.put(15, 3);
        snakes.put(52, 19);
        snakes.put(56, 33);
        snakes.put(64, 60);
        snakes.put(85, 40);
        snakes.put(95, 78);
        snakes.put(98, 75);

        ladders.put(1, 36);
        ladders.put(4, 13);
        ladders.put(9, 32);
        ladders.put(27, 84);
        ladders.put(50, 68);
        ladders.put(71, 92);
        ladders.put(80, 99);
    }

    public void setValues() {
        int count = 100;
        int constant = -1;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = "" + count;
                count = count + constant;
            }
            count -= 10 + constant;
            constant *= -1;
        }
    }

    public void displayField() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j] + " \t");
            }
            System.out.println("\n");
        }
    }

    public int rollDice() {
        Random number = new Random();
        return number.nextInt(6) + 1;
    }

    public int calculatePlayerValue(int playerposition, int dieValue) {
        playerposition += dieValue;
        if (playerposition > 100) {
            playerposition -= dieValue;
            return playerposition;
        }
        else if (snakes.containsKey(playerposition)) {
            System.out.println("Oops! You are swallowed by a snake!!");
            return snakes.get(playerposition);
        }
        else if (ladders.containsKey(playerposition)) {
            System.out.println("Yay! You climbed up the ladder!");
            return ladders.get(playerposition);
        }
        return playerposition;
    }

    public void setPlayer(int player1Position, int player2position) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j].equals(Integer.toString(player1Position))) {
                    if (player1Position == player2position) {
                        field[i][j] = "|P1P2|";
                    }
                    else {
                        field[i][j] = "|P1|";
                    }
                }
                if (field[i][j].equals(Integer.toString(player2position))) {
                    field[i][j] = "|P2|";
                }
            }
        }
    }

    public void checkWin(int playerPosition, String player) {
        if (playerPosition == 100) {
            isGameover = true;
            System.out.println(player + " won!!");
        }
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public int playerTurn(int playerPosition, String player) {
        System.out.println(player + " turn : Press any key to roll a die : ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        int dieValue = rollDice();
        playerPosition = calculatePlayerValue(playerPosition, dieValue);
        clearScreen();
        System.out.println(player + " rolled a " + dieValue + " and moved to position " + playerPosition + "\n");
        checkWin(playerPosition, player);
        if (dieValue == 6) {
            System.out.println("You rolled a six! Roll again!");
            playerTurn(playerPosition, player);
        }
        return playerPosition;
    }

    public void startGame() {
        int player1Position = 0;
        int player2Position = 0;
        int currentPlayer = -1;
        while (!isGameover) {
            if (currentPlayer == -1) {
                player1Position = playerTurn(player1Position, "Player1");
            }
            else {
                player2Position = playerTurn(player2Position, "Player2");
            }
            currentPlayer = -currentPlayer;
            setValues();
            setPlayer(player1Position, player2Position);
            displayField();
        }
    }

    public static void main(String[] ar) {
        SnakeAndLadders game = new SnakeAndLadders();
        game.startGame();
    }
}