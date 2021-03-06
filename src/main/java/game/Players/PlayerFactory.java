package game.Players;

import game.Mark;
import game.UI;

import java.util.Arrays;
import java.util.List;

import static game.Mark.O;
import static game.Mark.X;

public class PlayerFactory {

    private final UI ui;
    private final Mark playerOne;
    private final Mark playerTwo;

    public PlayerFactory(UI ui) {
        this.ui = ui;
        this.playerOne = X;
        this.playerTwo = O;
    }

    public List<Player> getPlayerTypes(String players) {
        Player[] humanVsHuman = {new HumanPlayer(ui, playerOne), new HumanPlayer(ui, playerTwo)};
        Player[] humanVsComputer = {new HumanPlayer(ui, playerOne), new Computer(playerTwo)};
        Player[] computerVsHuman = {new Computer(playerOne), new HumanPlayer(ui, playerTwo)};
        Player[] computerVsComputer = {new Computer(playerOne), new Computer(playerTwo)};
        Player[] humanVsUnbeatablePlayer = {new HumanPlayer(ui, playerOne), new UnbeatableComputer(playerTwo)};
        Player[] unbeatablePlayerVsHuman = {new UnbeatableComputer(playerOne), new HumanPlayer(ui, playerTwo)};
        Player[] unbeatableVsUnbeatable = {new UnbeatableComputer(playerOne), new UnbeatableComputer(playerTwo)};
        Player[] guiVsGui = {new GuiPlayer(playerOne), new GuiPlayer(playerTwo)};
        Player[] guiVsUnbeatable = {new GuiPlayer(playerOne), new UnbeatableComputer(playerTwo)};
        Player[] unbeatableVsGui ={new UnbeatableComputer(playerOne), new GuiPlayer(playerTwo)};

        switch (players) {
            case UI.HUMAN_VS_HUMAN:
                return Arrays.asList(humanVsHuman);
            case UI.HUMAN_VS_COMPUTER:
                return Arrays.asList(humanVsComputer);
            case UI.COMPUTER_VS_HUMAN:
                return Arrays.asList(computerVsHuman);
            case UI.COMPUTER_VS_COMPUTER:
                return Arrays.asList(computerVsComputer);
            case UI.HUMAN_VS_UNBEATABLE_PLAYER:
                return Arrays.asList(humanVsUnbeatablePlayer);
            case UI.UNBEATABLE_PLAYER_VS_HUMAN:
                return Arrays.asList(unbeatablePlayerVsHuman);
            case UI.UNBEATABLE_PLAYER_VS_UNBEATABLE_PLAYER:
                return Arrays.asList(unbeatableVsUnbeatable);
            case UI.GUI_PLAYER_VS_GUI_PLAYER:
                return Arrays.asList(guiVsGui);
            case UI.GUI_PLAYER_VS_UNBEATABLE_PLAYER:
                return Arrays.asList(guiVsUnbeatable);
            case UI.UNBEATABLE_PLAYER_VS_GUI_PLAYER:
                return Arrays.asList(unbeatableVsGui);
            default:
                throw new RuntimeException("Unsupported player type");
        }
    }

}
