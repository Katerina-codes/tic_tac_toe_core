import game.*;
import game.Players.HumanPlayer;
import game.Players.Player;
import game.Players.PlayerFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.List;

import static java.util.Arrays.asList;

public class Controller implements UI {
    public Button one;
    public Button two;
    public Button three;
    public Button four;
    public Button five;
    public Button six;
    public Button seven;
    public Button eight;
    public Button nine;
    public Label announceResult;
    public Label askForGameMode;
    public Button start;
    public Label getGameMode;
    private Board board = new Board(3);
    private final Game game = new Game(this, board);
    public Button HumanVsHuman;
    public Button HumanVsComputer;
    private PlayerFactory playerTypes = new PlayerFactory(this);
    private Player currentPlayer;
    private Player playerOne;
    private Player playerTwo;

    @Override
    public void askForGameMode() {
        askForGameMode.setText("Press 1 for Human Vs Human\nEnter 5 for Human Vs Computer");
    }

    @Override
    public String getUserChoice() {
        return "1";
    }

    @Override
    public void askForMove(Mark playerMark, List<Mark> boardSize) {
        List<Button> buttons = asList(one, two, three, four, five, six, seven, eight, nine);

        for (int i = 0; i < buttons.size(); i++) {
            int finalI = i;
            buttons.get(i).setOnAction(click -> {
                String move = buttons.get(finalI).getText();
                game.playMove(Integer.parseInt(move));
            });
        }
    }

    @Override
    public String getMove(Board board) {
        return "";
    }

    @Override
    public void announceWinner(Result winner) {
        String result;
        if (winner.equals(Result.TIE)) {
            result = "Game Over! It's A Tie!";
        } else {
            result = String.format("Game Over! %s Won!", winner.getResult());
        }
        announceResult.setText(result);
    }

    @Override
    public boolean replay() {
        return false;
    }

    @Override
    public int getBoardSize() {
        return 3;
    }

    @Override
    public void askForBoardSize() {

    }

    @Override
    public void displayBoard(List<Mark> grid, int size) {
        List<Button> buttons = asList(one, two, three, four, five, six, seven, eight, nine);
        for (int i = 0; i < grid.size(); i++) {
            if (grid.get(i) == Mark.EMPTY) {
                buttons.get(i).setText(String.valueOf((i + 1)));
            } else {
                buttons.get(i).setText(String.valueOf(grid.get(i)));
            }
        }
    }

    public void makeMove(ActionEvent actionEvent) {
        displayBoard(board.grid, 3);
        Button buttonPressed = (Button) actionEvent.getTarget();
        int moveNumber = getMoveNumber(buttonPressed);
        markBoard(buttonPressed, moveNumber);
        checkGameIsNotOver();
        switchPlayer(playerOne, playerTwo);

        if (!(playerTwo instanceof HumanPlayer)) {
            int computerMove = currentPlayer.playMove(board);
            board = board.updateMove(computerMove, currentPlayer.getMark());
            displayBoard(board.grid, 3);
            checkGameIsNotOver();
            switchPlayer(playerOne, playerTwo);
        }
    }

    private void markBoard(Button buttonPressed, int moveNumber) {
        board = board.updateMove(moveNumber - 1, currentPlayer.getMark());
        buttonPressed.setText(String.valueOf(currentPlayer.getMark()));
    }

    private void checkGameIsNotOver() {
        if (board.gameIsOver()) {
            Result winner = board.findWinner();
            announceWinner(winner);
        }
    }

    public void gameMode(ActionEvent actionEvent) {
        Button gameModeButton = (Button) actionEvent.getTarget();
        String buttonText = gameModeButton.getText();
        List<Player> players = playerTypes.getPlayerTypes(buttonText);
        playerOne = players.get(0);
        playerTwo = players.get(1);
        currentPlayer = playerOne;
    }

    private int getMoveNumber(Button buttonPressed) {
        String move = buttonPressed.getText();
        return Integer.parseInt(move);
    }

    private void switchPlayer(Player playerOne, Player playerTwo) {
        if (currentPlayer == playerOne) {
            currentPlayer = playerTwo;
        } else {
            currentPlayer = playerOne;
        }
    }

    public void setUp(ActionEvent actionEvent) {
        getGameMode.setText("Enter 1 for Human Vs Human and 5 for Human Vs Computer");
    }
}
