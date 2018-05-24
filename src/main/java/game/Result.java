package game;

public enum Result {
    PLAYER_ONE_WIN("X"),
    PLAYER_TWO_WIN("O"),
    TIE("Tie"),
    GAME_IN_PROGRESS("The game is still in progress!");

    private final String result;

    Result(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}