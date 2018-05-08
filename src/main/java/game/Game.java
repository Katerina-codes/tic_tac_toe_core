package game;

import game.Players.Player;
import game.Players.PlayerFactory;

import java.util.List;

public class Game {

    private final UI ui;
    private Board board;
    private Player currentPlayer;

    public Game(UI ui, Board board) {
        this.ui = ui;
        this.board = board;
    }

    public void run() {
        ui.askForGameMode();
        String gameMode = ui.getUserChoice();
        PlayerFactory playerTypes = new PlayerFactory(ui);
        List<Player> players = playerTypes.getPlayerTypes(gameMode);

        Player playerOne = players.get(0);
        Player playerTwo = players.get(1);

        currentPlayer = playerOne;
        displayBoard();

        while (gameIsNotOver()) {
            ui.askForMove(currentPlayer.getMark(), this.board.grid);
            board = currentPlayer.playMove(board);
            displayBoard();
            switchPlayer(playerOne, playerTwo);
        }
        Result result = board.findWinner();
        ui.announceWinner(result);

        if (ui.replay()) {
            ui.askForBoardSize();
            int size = ui.getBoardSize();
            this.board = new Board(size);
            run();
        }
    }

    private void switchPlayer(Player playerOne, Player playerTwo) {
        if (currentPlayer == playerOne) {
            currentPlayer = playerTwo;
        } else {
            currentPlayer = playerOne;
        }
    }

    private boolean gameIsNotOver() {
        return !this.board.gameIsOver();
    }

    private void displayBoard() {
        ui.displayBoard(this.board.grid, this.board.size);
    }
}
