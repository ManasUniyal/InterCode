package Controller;

import DataClasses.ClientDetails;
import RequestClasses.LoginVerification;
import RequestClasses.Response;
import Utilities.FXMLInitiator;
import Windows.AlertBox;
import intercode.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.IOException;

public class Login {

    @FXML
    TextField loginField;

    @FXML
    TextField passwordField;

    @FXML
    Label interviewerLabel;

    @FXML
    Label candidateLabel;

    private int mode = 0;   //mode = 1 for interviewer and mode = 2 for candidate

    public void selectMode(MouseEvent mouseEvent) {
        Label lbl = (Label)mouseEvent.getSource();
        if(lbl == interviewerLabel){
            interviewerLabel.setTextFill(Color.WHITE);
            candidateLabel.setTextFill(Color.BLACK);
            mode = 1;
        }
        else{
            interviewerLabel.setTextFill(Color.BLACK);
            candidateLabel.setTextFill(Color.WHITE);
            mode = 2;
        }
    }

    //Checks individual field
    private boolean check(){
        boolean state = true;
        if(loginField.getText().length() == 0){
            new AlertBox("Error", "Enter userID or email").start();
            state = false;
        } else if(passwordField.getText().length() == 0 ){
            new AlertBox("Error","Password field cannot be empty").start();;
            state = false;
        } else if(mode == 0){
            new AlertBox("Erorr", "Select mode as interviewer or candidate").start();
            state = false;
        }
        //Resetting each field if an invalid combination is entered
        if(state == false) {
            mode = 0;
            loginField.setText("");
            passwordField.setText("");
        }
        return state;
    }

    //Check for login and password combination
    public void loginButton(ActionEvent event) {
        if(check() == false)
            return;
        String loginCredential = loginField.getText();
        String password = passwordField.getText();
        try {
            Main.GAMER.send_message(new LoginVerification(loginCredential, password, mode));
            LoginVerification loginVerification = (LoginVerification) Main.GAMER.receive_message();
            if (loginVerification.isLoginSuccessful() == true) {
                Main.USER = loginVerification.getClientDetails();
//                if (mode == 1){
//                    new FXMLInitiator("../FXML/Interviewer.fxml").start(Main.PRIMARYSTAGE);
//                } else {
//                    new FXMLInitiator("../FXML/Candidate.fxml").start(Main.PRIMARYSTAGE);
//                }
                new FXMLInitiator("../FXML/Candidate.fxml").start(Main.PRIMARYSTAGE);
            } else {
                new AlertBox("Error",loginVerification.getMessage());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
