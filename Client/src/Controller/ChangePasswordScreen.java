package Controller;

import RequestClasses.ChangePassword;
import RequestClasses.Response;
import Windows.AlertBox;
import intercode.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangePasswordScreen {

    @FXML
    PasswordField oldPasswordField;

    @FXML
    PasswordField newPasswordField;

    @FXML
    PasswordField confirmPasswordField;

    private Stage stage;

    private boolean check() {
        boolean state = true;
        if(oldPasswordField.getText().length() == 0){
            state = false;
            new AlertBox("Error", "Old password field cannot be empty").start();
        } else if(newPasswordField.getText().length() == 0){
            state = false;
            new AlertBox("Error", "New password cannot be empty");
        } else if(confirmPasswordField.getText().length() == 0){
            state = false;
            new AlertBox("Error", "Confirm password field cannot be empty");
        }
        return state;
    }

    public void saveButton(ActionEvent event) {
        if(check() == false) {
            return;
        }
        try {
            Main.GAMER.send_message(new ChangePassword(Main.USER.getUserID(), oldPasswordField.getText(), newPasswordField.getText(), confirmPasswordField.getText()));
            Response response = (Response) Main.GAMER.receive_message();
            if(response.getStatus() == 0){
                new AlertBox("Success", "Password changed successfully").start();
            } else {
                new AlertBox("Error", response.getMessage()).start();
                oldPasswordField.setText("");
                newPasswordField.setText("");
                confirmPasswordField.setText("");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
