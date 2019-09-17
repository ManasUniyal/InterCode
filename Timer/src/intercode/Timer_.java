package intercode;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.awt.*;


public class Timer_ {

    @FXML
    Label lbl;

    private int tmp;
    private Timeline animation;

    public void initialize(){
        tmp = 60;
        lbl.setText(tmp+"");
        animation = new Timeline(new KeyFrame(Duration.seconds(1), event -> timelabel()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    private void timelabel() {
        if(tmp > 0){
            tmp--;
        }
        lbl.setText(tmp+"");
    }

    public void setLabel(String str){
        lbl.setText(str);
    }



}
