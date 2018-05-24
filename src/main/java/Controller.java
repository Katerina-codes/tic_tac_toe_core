import game.*;
import game.Players.GuiPlayer;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.List;

import static game.Mark.X;
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
    private Label askForGameMode;
    public Button start;
    public Label getGameMode;
    public Label movePrompt;
    public Button guiPlayerVsguiPlayer;
    public Button guiPlayerVsComputer;
    private Board board = new Board(3);
    private final Game game = new Game(this, board);
    private GuiPlayer guiPlayer = new GuiPlayer(X);

    @Override
    public void askForGameMode() {
        askForGameMode.setText("Press 8 for Human Vs Human\nEnter 9 for Human Vs Computer");
    }

    @Override
    public String getUserChoice() {
        return "1";
    }

    @Override
    public void askForMove(Mark playerMark, List<Mark> boardSize) {
        String askForMove = String.format("Player %s, play a move!", playerMark);
        movePrompt.setText(askForMove);
        displayThenRemovePrompt();
    }

    private void displayThenRemovePrompt() {
        PauseTransition visibleText;
        movePrompt.setVisible(true);
        visibleText = new PauseTransition(Duration.seconds(1));
        visibleText.setOnFinished(event -> movePrompt.setVisible(false));
        visibleText.play();
    }

    @Override
    public String getMove(Board board) {
        return String.valueOf(guiPlayer.currentMove);
    }

    @Override
    public void announceGameStatus(Result result) {
        String verdict;
        if (result.equals(Result.TIE)) {
            verdict = "Game Over! It's A Tie!";
        } else {
            verdict = String.format("Game Over! %s Won!", result.getResult());
        }
        announceResult.setText(verdict);
    }

    @Override
    public boolean replay() {
        return false;
    }

    @Override
    public int getBoardSize() {
        return board.size;
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


    public void setUp() {
        getGameMode.setText("Enter 8 for Human Vs Human and 9 for Human Vs Computer");
    }


    public void makeMove(ActionEvent actionEvent) {
        int moveNumber = markBoard(actionEvent);
        game.playMove(moveNumber);
        guiPlayer.receiveMove(moveNumber);
        checkGameIsNotOver();
    }

    private int markBoard(ActionEvent actionEvent) {
        Button buttonPressed = (Button) actionEvent.getTarget();
        int moveNumber = getMoveNumber(buttonPressed) - 1;
        buttonPressed.setText(String.valueOf(game.currentPlayer.getMark()));
        return moveNumber;
    }

    private void checkGameIsNotOver() {
        if (board.gameIsOver()) {
            Result winner = board.findResult();
            announceGameStatus(winner);
        } else {
            game.switchPlayer();
            game.run();
        }
    }

    public void gameMode(ActionEvent actionEvent) {
        Button gameModeButton = (Button) actionEvent.getTarget();
        String gameMode = gameModeButton.getText();
        game.receiveGameMode(gameMode);
    }

    private int getMoveNumber(Button buttonPressed) {
        String move = buttonPressed.getText();
        return Integer.parseInt(move);
    }
}
