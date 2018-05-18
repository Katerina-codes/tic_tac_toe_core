package game.Players;

import game.Board;
import game.Mark;

public interface Player {
    int playMove(Board board);

    Mark getMark();
}
