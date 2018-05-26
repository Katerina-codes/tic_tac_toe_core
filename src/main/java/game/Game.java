package game;

import game.Players.Player;
import game.Players.PlayerFactory;

import java.util.List;

public class Game {

    private final UI ui;
    private Board board;
    public Player currentPlayer;
    private Player playerOne;
    private Player playerTwo;
    private PlayerFactory playerTypes ;

    public Game(UI ui, Board board) {
        this.ui = ui;
        this.board = board;
        this.playerTypes = new PlayerFactory(this.ui);
    }

    public void run() {
        displayBoard();
        while (gameIsNotOver() && currentPlayer.hasMove()) {
            playNextMove();
            displayBoard();
            switchPlayer();
        }
        endGame();
    }

    public void endGame() {
        endResult();
        playAgain();
    }

    public void playerSetUp() {
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
        Result gameStatus = board.gameStatus();
        if (gameIsOver(gameStatus)) {
            ui.announceGameStatus(gameStatus);
        }
    }

    private boolean gameIsOver(Result result) {
        return !result.equals(Result.GAME_IN_PROGRESS);
    }

    private void playNextMove() {
        int move = getMove();
        playMove(move);
    }

    private int getMove() {
        ui.askForMove(currentPlayer.getMark(), this.board.grid);
        return currentPlayer.playMove(board);
    }

    public void playMove(int move) {
        board = board.updateMove(move, currentPlayer.getMark());
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

    public void switchPlayer() {
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

    public void receiveGameMode(String gameMode) {
        setPlayers(gameMode, playerTypes);
        currentPlayer = playerOne;
    }
}
