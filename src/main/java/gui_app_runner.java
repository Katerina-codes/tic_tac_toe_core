import game.Board;
import game.Game;
import game.Mark;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class gui_app_runner extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Tic Tac Toe");
        Parent root = FXMLLoader.load(getClass().getResource("Display.fxml"));
        int board = new Board().size;
//        Mark currentMark = new Game(new Display(), new Board()).currentPlayer.getMark();
//        Display display = new Display(board, currentMark);
//        primaryStage.setScene(display);
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
}
