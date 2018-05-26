import game.Board;
import game.Mark;
import game.Result;
import game.UI;

import java.io.*;
import java.util.List;

public class CommandLineUI implements UI {

    private static final int FOUR_BY_FOUR = 4;
    private static final int FIRST_CHOICE = 1;
    private static final String REPLAY = "1";
    private static final String ONE = "1";
    private static final String TWO = "2";
    private final PrintStream output;
    private final BufferedReader input;

    public CommandLineUI(PrintStream output, InputStream input) {
        this.output = output;
        this.input = new BufferedReader(new InputStreamReader(input));
    }

    public void askForBoardSize() {
        output.println("Please enter " + ONE + " for a 3x3 grid\n" +
                "Please enter " + TWO + " for a 4x4 grid:");
    }

    public int getBoardSize() {
        int userChoice = Integer.parseInt(getUserChoice());
        if (userChoice == FIRST_CHOICE) {
            return THREE_BY_THREE;
        } else {
            return FOUR_BY_FOUR;
        }
    }


    public void askForGameMode() {
        output.println("\n\nEnter " + HUMAN_VS_HUMAN + " for Human vs Human\n" +
                "Enter " + HUMAN_VS_COMPUTER + " for Human vs Computer\n" +
                "Enter " + COMPUTER_VS_HUMAN + " for Computer vs Human\n" +
                "Enter " + COMPUTER_VS_COMPUTER + " for Computer vs Computer\n" +
                "Enter " + HUMAN_VS_UNBEATABLE_PLAYER + " for Human vs Unbeatable Computer\n" +
                "Enter " + UNBEATABLE_PLAYER_VS_HUMAN + " for Unbeatable Computer vs Human\n" +
                "Enter " + UNBEATABLE_PLAYER_VS_UNBEATABLE_PLAYER + " for Unbeatable Computer vs Unbeatable Computer");
    }

    public String getUserChoice() {
        String choice = "";
        try {
            choice = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            output.println("Nothing was entered.");
        }
        return choice;
    }

    public void askForMove(Mark playerMark, List<Mark> grid) {
        output.format("Player %s place your mark! Pick a move from 1 - %s:\n\n", playerMark, Integer.toString(grid.size()));
    }

    public String getMove(Board board) {
        String move = getMove();

        int convertedMove = Integer.parseInt(move);

        while (moveIsNotAvailable(board, convertedMove)) {
            move = getValidMove();
            convertedMove = Integer.parseInt(move);

        }
        return move;
    }

    public void displayBoard(List<Mark> grid, int size) {
        StringBuilder sb = new StringBuilder();
        String formattedRows = null;
        sb.append("\n");

        formatRowLines(size, sb);

        for (int i = 0; i < grid.size(); i++) {
            sb.append("| ");
            if (grid.get(i) == Mark.EMPTY) {
                sb.append(i + 1);
                if (i + 1 <= 9) {
                    sb.append(" ");
                }
            } else {
                sb.append(grid.get(i));
                sb.append(" ");
            }
            formatEndOfRow(size, sb, i);
            formattedRows = sb.toString();
        }
        output.println(formattedRows);
    }

    @Override
    public void announceGameStatus(Result winner) {

    }

    public void announceWinner(Result result) {
        if (result.equals(Result.TIE)) {
            output.println("It's a tie!");
        } else {
            output.format("%s won!", result.getResult());
        }
    }

    public boolean replay() {
        askUserIfTheyWantToPlayAgain();
        String choice = getUserChoice();
        return choice.equals(REPLAY);
    }

    private boolean moveIsNotAvailable(Board board, int convertedMove) {
        return !board.isMoveAvailable(convertedMove);
    }

    private String getValidMove() {
        output.println("\nThis move is taken. Place another one:");
        return getMove();
    }

    private String getMove() {
        String move = "";
        try {
            move = input.readLine();
        } catch (IOException e) {
            output.println("Nothing was entered.");
        }
        return move;
    }

    private void askUserIfTheyWantToPlayAgain() {
        output.println("\nWould you like to play again?\n" +
                "Enter 1 for yes\n" +
                "Enter 2 for no.");
    }

    private void formatEndOfRow(int size, StringBuilder sb, int i) {
        if ((i + 1) % size == 0) {
            sb.append("|\n");
            formatRowLines(size, sb);
        }
    }

    private void formatRowLines(int size, StringBuilder sb) {
        if (size == 3) {
            sb.append(" --- --- ---\n");
        } else {
            sb.append(" --- --- --- ---\n");
        }
    }

}
