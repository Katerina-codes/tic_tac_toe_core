package game.Players;

import game.Board;
import game.Mark;

import java.util.List;

public class Computer implements Player {

    private final Mark mark;

    public Computer(Mark mark) {
        this.mark = mark;
    }

    public int playMove(Board board) {
        return move(board);
    }

    public Mark getMark() {
        return mark;
    }

    public boolean hasMove() {
        return true;
    }

    public int move(Board board) {
        List<Integer> possibleMoves = board.availableMoves();
        return possibleMoves.get(0);
    }
}
