package intercode;

import Chat.Main.ChatterClient;
import DataClasses.ClientDetails;
import Controller.MainScreenController;
import Editor.Trie;
import Editor._Editor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class Main extends Application {

    public static final double WIDTH = 1000;
    public static final double HEIGHT = 1000;
    public static Stage PRIMARYSTAGE;
    public static Client GAMER;
    public static ChatterClient CHATTER;
    public static String NAME = "SAM";
    public static String GROUP;
    public static ClientDetails USER;

    public static void main(String[] args) {
        launch(args);
    }

    /*@Override
    public void start(Stage primaryStage) throws Exception {

        GAMER = new Client("localhost", 6000,"Welcome");
        USER = new ClientDetails();
//        GAMER = new Client("localhost", 6000,"Welcome");
        PRIMARYSTAGE = primaryStage;

//        new _Editor().start();
//
//
        Parent root = FXMLLoader.load(getClass().getResource("../Editor/Editor.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

//        Trie trie = new Trie();
//
//        trie.addWord("abc");
//        trie.addWord("abcd");
//        trie.addWord("abcde");
//        trie.addWord("abs");
//        trie.addWord("adde");
//        trie.addWord("abfde");
//        trie.addWord("abfdeg");
//        trie.addWord("abfdegh");
//
//        for (String X: trie.wordsByPrefix("ab")) {
//            System.out.println(X);
//        }

    }*/

    @Override
    public void start(Stage primaryStage) throws IOException {

        PRIMARYSTAGE = primaryStage;
        Parent parent = FXMLLoader.load(getClass().getResource("../Editor/Editor.FXML"));
        primaryStage.setScene(new Scene(parent, HEIGHT, WIDTH));
        primaryStage.show();

    }
}
