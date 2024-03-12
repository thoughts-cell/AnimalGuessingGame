package cqu.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader;

        loader = new FXMLLoader(getClass().getResource("FXML.fxml"));
        Parent root = loader.load();

        Controller c = loader.getController();
        Game g = new Game(c);
        c.bind(g);
        
        Scene s = new Scene(root);

        stage.setScene(s);
        stage.show();

    }
 

    

    public static void main(String[] args) {
        launch();
    }

}
