import game.Board;
import game.Mark;
import game.Result;
import game.UI;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import java.util.List;

import static java.util.Arrays.asList;

public class Display implements UI {
    public Button one;
    public Button two;
    public Button three;
    public Button four;
    public Button five;
    public Button six;
    public Button seven;
    public Button eight;
    public Button nine;

    @Override
    public void askForGameMode() {

    }

    @Override
    public String getUserChoice() {
        return null;
    }

    @Override
    public void askForMove(Mark playerMark, List<Mark> boardSize) {

    }

    @Override
    public String getMove(Board board) {
        return null;
    }

    @Override
    public void displayBoard(List<Mark> grid, int size) {
        List<Button> buttons = asList(one, two, three, four, five, six, seven, eight, nine);
        for (int i = 0; i < grid.size(); i++) {
            for (Button button : buttons) {

                if (grid.get(i) == Mark.EMPTY) {
                    button.setText(" ");
                } else {
                    button.setText(String.valueOf(i));
                }
            }
        }

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

    public void makeMove(ActionEvent actionEvent) {
        Button buttonPressed = (Button) actionEvent.getTarget();
        buttonPressed.setText("O");
    }
}
