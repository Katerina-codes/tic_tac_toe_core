package game;

import java.util.List;

public interface UI {
    String HUMAN_VS_HUMAN = "1";
    String HUMAN_VS_COMPUTER = "2";
    String COMPUTER_VS_HUMAN = "3";
    String COMPUTER_VS_COMPUTER = "4";
    String HUMAN_VS_UNBEATABLE_PLAYER = "5";
    String UNBEATABLE_PLAYER_VS_HUMAN = "6";
    String UNBEATABLE_PLAYER_VS_UNBEATABLE_PLAYER = "7";
    String GUI_PLAYER_VS_GUI_PLAYER = "8";
    String GUI_PLAYER_VS_UNBEATABLE_PLAYER = "9";
    String UNBEATABLE_PLAYER_VS_GUI_PLAYER = "10";
    int THREE_BY_THREE = 3;

    void askForGameMode();

    String getUserChoice();

    void askForMove(Mark playerMark, List<Mark> boardSize);

    String getMove(Board board);

    void displayBoard(List<Mark> rows, int size);

    void announceGameStatus(Result winner);

    boolean replay();

    int getBoardSize();

    void askForBoardSize();
}
