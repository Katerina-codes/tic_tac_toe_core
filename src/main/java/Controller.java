import game.Board;
import game.Mark;
import game.Players.Player;
import game.Players.PlayerFactory;
import game.Result;
import game.UI;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.List;

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
    private final Board board = new Board(3);
    public Label announceResult;
    private PlayerFactory playerTypes = new PlayerFactory(this);
    private List<Player> players = playerTypes.getPlayerTypes("1");
    private Player playerOne = players.get(0);
    private Player playerTwo = players.get(1);
    private Player currentPlayer = playerOne;

    @Override
    public void askForGameMode() {
    }

    @Override
    public String getUserChoice() {
        return "1";
    }

    @Override
    public void askForMove(Mark playerMark, List<Mark> boardSize) {
    }

    @Override
    public String getMove(Board board) {
        return "";
    }

    @Override
    public void announceWinner(Result winner) {

    }

    @Override
    public boolean replay() {
        return false;
    }

    @Override
    public int getBoardSize() {
        return 0;
    }

    @Override
    public void askForBoardSize() {

    }

    @Override
    public void displayBoard(List<Mark> grid, int size) {
    }

    public void makeMove(ActionEvent actionEvent) {
        Button buttonPressed = (Button) actionEvent.getTarget();
        int moveNumber = getMoveNumber(buttonPressed);
        board.updateMove(moveNumber - 1, currentPlayer.getMark());
        buttonPressed.setText(String.valueOf(currentPlayer.getMark()));
        switchPlayer(playerOne, playerTwo);
        checkGameStatus();
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

    private void checkGameStatus() {
        if (board.gameIsOver()) {
            Result winner = board.findWinner();
            announceWinner(winner.getResult());
        }
    }

    private void announceWinner(String winner) {
        String result;
        if (winner.equals("Tie")) {
            result = "Game Over! It's A Tie!";
        } else {
            result = String.format("Game Over! %s Won!", winner);
        }
        announceResult.setText(result);
    }
}
