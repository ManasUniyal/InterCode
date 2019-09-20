package Controller;

import javafx.stage.Stage;

public class CustomInput {

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void runPressed(){
        //Run and write the result into the result section
        stage.close();
    }
}
