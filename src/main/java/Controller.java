import game.*;
import game.Players.GuiPlayer;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
    public Label askForGameMode;
    public Button start;
    public Label getGameMode;
    private Board board = new Board(3);
    private final Game game = new Game(this, board);
    public Button HumanVsHuman;
    public Button HumanVsComputer;
    GuiPlayer guiPlayer = new GuiPlayer(X);

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
        return String.valueOf(guiPlayer.currentMove);
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
        game.switchPlayer();

        if (!(game.currentPlayer instanceof GuiPlayer)) {
            int computerMove = game.currentPlayer.playMove(board);
            board = board.updateMove(computerMove, game.currentPlayer.getMark());
            displayBoard(board.grid, 3);
            checkGameIsNotOver();
            game.switchPlayer();
        }
    }

    private void markBoard(Button buttonPressed, int moveNumber) {
        board = board.updateMove(moveNumber - 1, game.currentPlayer.getMark());
        buttonPressed.setText(String.valueOf(game.currentPlayer.getMark()));
    }

    private void checkGameIsNotOver() {
        if (board.gameIsOver()) {
            Result winner = board.findWinner();
            announceWinner(winner);
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

    public void setUp(ActionEvent actionEvent) {
        getGameMode.setText("Enter 8 for Human Vs Human and 9 for Human Vs Computer");
    }
}
