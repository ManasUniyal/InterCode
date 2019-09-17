package intercode;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

    public static Timer_ timer_;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("timer.fxml"));
        Parent root = fxmlLoader.load();
        timer_ = fxmlLoader.getController();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("Exited full screen");
        Thread t = new Thread(new GetHint(primaryStage));
        t.start();

//        new Initiator().run_(timer_);
    }
}