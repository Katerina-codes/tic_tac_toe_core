package game;

import game.Players.Player;
import game.Players.PlayerFactory;

import java.util.List;

public class Game {

    private final UI ui;
    private Board board;
    private Player currentPlayer;
    private Player playerOne;
    private Player playerTwo;
    private PlayerFactory playerTypes ;

    public Game(UI ui, Board board) {
        this.ui = ui;
        this.board = board;
        this.playerTypes = new PlayerFactory(this.ui);
    }

    public void run() {
        playerSetUp();
        displayBoard();

        while (gameIsNotOver()) {
            playNextMove();
            displayBoard();
            switchPlayer();
        }
        endResult();
        playAgain();
    }

    private void playerSetUp() {
        String gameMode = getGameMode();
        setPlayers(gameMode, playerTypes);
        currentPlayer = playerOne;
    }

    private void playAgain() {
        if (ui.replay()) {
            ui.askForBoardSize();
            int size = ui.getBoardSize();
            this.board = new Board(size);
            run();
        }
    }

    private void endResult() {
        Result result = board.findWinner();
        ui.announceWinner(result);
    }

    private void playNextMove() {
        ui.askForMove(currentPlayer.getMark(), this.board.grid);
        board = currentPlayer.playMove(board);
    }

    private void setPlayers(String gameMode, PlayerFactory playerTypes) {
        List<Player> players = playerTypes.getPlayerTypes(gameMode);

        playerOne = players.get(0);
        playerTwo = players.get(1);
    }

    private String getGameMode() {
        ui.askForGameMode();
        return ui.getUserChoice();
    }

    private void switchPlayer() {
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
