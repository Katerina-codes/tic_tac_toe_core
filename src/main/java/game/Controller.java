package game;

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
    private Game game;
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
    public Button start;
    public Label getGameMode;
    public Label movePrompt;
    public Button guiPlayerVsGuiPlayer;
    public Button guiPlayerVsComputer;
    public Button computerVsGuiPlayer;
    private Board board = new Board(3);
    private GuiPlayer guiPlayer = new GuiPlayer(X);

    public Controller() {
        this.game = new Game(this, board);
    }

    @Override
    public int getBoardSize() {
        return board.size;
    }

    @Override
    public void askForBoardSize() {

    }

    public void setUp() {
        askForGameMode();
    }

    @Override
    public void askForGameMode() {
        getGameMode.setText("Press 8 for Human Vs Human\nPress 9 for Human Vs Computer\nPress 10 for Computer Vs Human");
    }

    public void gameMode(ActionEvent actionEvent) {
        Button gameModeButton = (Button) actionEvent.getTarget();
        String gameMode = gameModeButton.getText();
        game.receiveGameMode(gameMode);
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

    @Override
    public String getUserChoice() {
        return "1";
    }

    @Override
    public void askForMove(Mark playerMark, List<Mark> boardSize) {
        String askForMove = String.format("Player %s, play a move!", playerMark);
        movePrompt.setText(askForMove);
        displayThenRemoveMovePrompt();
    }

    private void displayThenRemoveMovePrompt() {
        PauseTransition visibleText;
        movePrompt.setVisible(true);
        visibleText = new PauseTransition(Duration.seconds(1));
        visibleText.setOnFinished(event -> movePrompt.setVisible(false));
        visibleText.play();
    }

    public void makeMove(ActionEvent actionEvent) {
        int moveNumber = getMoveNumber(actionEvent);
        GuiPlayer player = (GuiPlayer) game.currentPlayer;
        player.receiveMove(moveNumber);
        game.run();
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

    private int getMoveNumber(ActionEvent actionEvent) {
        Button buttonPressed = (Button) actionEvent.getTarget();
        String move = buttonPressed.getText();
        return Integer.parseInt(move) - 1;
    }

    public Game getGame() {
        return this.game;
    }
}
