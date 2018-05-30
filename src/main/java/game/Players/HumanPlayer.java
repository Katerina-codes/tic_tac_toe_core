package game.Players;

import game.Board;
import game.Mark;
import game.UI;

public class HumanPlayer implements Player {

    private final UI ui;
    private final Mark mark;

    public HumanPlayer(UI ui, Mark mark) {
        this.ui = ui;
        this.mark = mark;
    }

    public int playMove(Board board) {
        return getMove(board);
    }

    public Mark getMark() {
        return mark;
    }

    public boolean hasMove() {
        return true;
    }

    private Integer getMove(Board board) {
        String userMove = ui.getMove(board);
        return Integer.parseInt(String.valueOf(userMove)) - 1;
    }
}
