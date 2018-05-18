package game.Players;

import game.Board;
import game.Mark;

public class GuiPlayer implements Player {

    public boolean hasMove() {
        return moveHasBeenSet();
    }

    private boolean moveHasBeenSet() {
        return false;
    }

    @Override
    public int playMove(Board board) {

        return 0;
    }

    @Override
    public Mark getMark() {
        return null;
    }
}
