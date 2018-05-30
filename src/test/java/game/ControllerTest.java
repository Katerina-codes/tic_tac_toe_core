package game;

import game.Players.GuiPlayer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static junit.framework.TestCase.assertTrue;

public class ControllerTest extends ApplicationTest {

    private Scene scene;
    private Game game;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = rootSetup();
        Parent root = loader.load();
        Controller controller = loader.getController();
        game = controller.getGame();
        int height = 400;
        int width = 600;
        scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }

    @Test
    public void moveRetrievedFromGUIPlayerAndPlayedOnGrid() {
        AnchorPane rootNode = (AnchorPane) scene.getRoot();
        Button gameModeButton = from(rootNode).lookup("#guiPlayerVsComputer").query();
        Button moveButton = from(rootNode).lookup("#one").query();

        clickOn(gameModeButton);
        clickOn(moveButton);

        assertTrue(game.board.grid.get(0).equals(Mark.X));
    }

   @Test
   public void gameModeRetrievedAndSet() {
       AnchorPane rootNode = (AnchorPane) scene.getRoot();
       Button gameModeButton = from(rootNode).lookup("#guiPlayerVsGuiPlayer").query();

       clickOn(gameModeButton);
       
       assertTrue(game.playerOne instanceof GuiPlayer);
       assertTrue(game.playerTwo instanceof GuiPlayer);
   }

    private FXMLLoader rootSetup() {
        return new FXMLLoader(getClass().getResource("/Controller.fxml"));
    }
}
