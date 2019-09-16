package Controller;

import Chat.Main.ChatterClient;
import intercode.Client;
import intercode.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;


public class MainScreenController {

    @FXML
    Label conversation;

    @FXML
    TextArea message;

    private Client client;

    public void setClient(Client client) {
        this.client = client;
    }


    public void sendMessageButton(ActionEvent event) {

        Main.NAME = message.getText();

        Main.CHATTER = new ChatterClient(Main.NAME,"localhost",5555);

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../FXML/scene2.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.PRIMARYSTAGE.setScene(new Scene(root,Main.WIDTH,Main.HEIGHT));

    }
}
