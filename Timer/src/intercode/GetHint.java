package intercode;

import javafx.stage.Stage;

public class GetHint implements Runnable {

    private Stage primaryStage;

    public GetHint(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void run() {
        String str = primaryStage.getFullScreenExitHint();
        String str2;
        while(true) {
            str2 = primaryStage.getFullScreenExitHint();
            if(str2.equals(str) == false)
                System.out.println("Error");
        }

    }
}
