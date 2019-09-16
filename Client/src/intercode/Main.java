package intercode;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static final double WIDTH = 1000;
    public static final double HEIGHT = 1000;
    public static Stage PRIMARYSTAGE;
    public static Client GAMER;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        GAMER = new Client("localhost", 6000,"Welcome");
        PRIMARYSTAGE = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/MainScreen.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
