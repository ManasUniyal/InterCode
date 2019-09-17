package intercode;

import Chat.Main.ChatterClient;
import Editor._Editor;
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
    public static ChatterClient CHATTER;
    public static String NAME = "SAM";
    public static String GROUP;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        GAMER = new Client("localhost", 6000,"Welcome");
        PRIMARYSTAGE = primaryStage;

//        new _Editor().start();
//
//
        Parent root = FXMLLoader.load(getClass().getResource("../Editor/Editor.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
