package Controller;

import intercode.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;


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
    }
}
