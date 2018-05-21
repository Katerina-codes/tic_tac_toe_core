package game.Players;

import game.Board;
import game.Mark;

public class GuiPlayer implements Player {


    private final Mark mark;
    public int currentMove;
    private boolean moveSet;

    public GuiPlayer(Mark mark) {
        this.mark = mark;
    }

    public boolean hasMove() {
        return moveHasBeenSet();
    }

    private boolean moveHasBeenSet() {
        return moveSet;
    }

    @Override
    public int playMove(Board board) {
        return currentMove;
    }

    @Override
    public Mark getMark() {
        return mark;
    }
}
