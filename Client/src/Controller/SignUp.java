package Controller;

import DataClasses.ClientDetails;
import RequestClasses.CheckUserID;
import RequestClasses.Response;
import RequestClasses.SignUpRequest;
import Windows.AlertBox;
import intercode.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;

public class SignUp {

    @FXML
    ImageView imageView;

    @FXML
    Label interviewerLabel;

    @FXML
    Label candidateLabel;

    @FXML
    TextField userNameField;

    @FXML
    TextField userIDField;

    @FXML
    TextField passwordField;

    @FXML
    TextField emailField;

    @FXML
    TextField phoneField;

    private int mode = 0;
    private Image image;
    private String imageExtension;
    private File file;

    public void initialize(){
        try {
            file = new File("src/Icons/newUserIcon.png");
            image = new Image(file.toURI().toURL().toString());
            imageView.setImage(image);
            imageExtension = "png";
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    //Can implement regex check, if time permits
    private boolean check(){
        boolean status = true;
        if(userNameField.getText().length() == 0){
            status = false;
            new AlertBox("Error","User Name field cannot be empty").start();
        } else if(userIDField.getText().length() == 0){
            status = false;
            new AlertBox("Error","User ID field cannot be empty").start();
        } else if(passwordField.getText().length() == 0){
            status = false;
            new AlertBox("Error","Password field cannot be empty").start();
        } else if(emailField.getText().length() == 0){
            status = false;
            new AlertBox("Error","Email field cannot be empty").start();
        } else if(phoneField.getText().length() == 0){
            status = false;
            new AlertBox("Error","Phone number field cannot be empty").start();
        } else if(mode == 0){
            status = false;
            new AlertBox("Error", "Select one mode amongst candidate and interviewer").start();
        }
        return status;
    }

    private boolean checkUserIDExists(){
        boolean status = false;
        try {
            Main.GAMER.send_message(new CheckUserID(userIDField.getText()));
            Response response = (Response) Main.GAMER.receive_message();
            status = (response.getStatus()==0)?false:true;
            if(status){
                new AlertBox("Error", "User ID already exists").start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return status;
    }

    public void registerButton(ActionEvent event) {
        if((check() == false) || (checkUserIDExists() == true))
            return;
        try {
            byte[] pictureContent = Files.readAllBytes(file.toPath());
            Main.GAMER.send_message(new SignUpRequest(userNameField.getText(), userIDField.getText(), passwordField.getText(), emailField.getText(), phoneField.getText(), pictureContent, imageExtension, mode));
            Response response = (Response) Main.GAMER.receive_message();
            if(response.getStatus() == 0){
                Main.USER = new ClientDetails(userIDField.getText(), userNameField.getText(), emailField.getText(), phoneField.getText(), imageExtension, null, mode);
//                if(mode == 1) {
//                    new FXMLInitiator("../FXML/Interviewer.fxml").start(Main.PRIMARYSTAGE);
//                } else {
//                    new FXMLInitiator("../FXML/Candidate.fxml").start(Main.PRIMARYSTAGE);
//                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public void changePictureButton(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.png, *jpg, *jpeg, *img)", "*.png", "*.jpeg", "*.img", "*jpg");
        fileChooser.getExtensionFilters().add(extFilter);
        File file_temp = fileChooser.showOpenDialog(null);
        if(file_temp != null) {
            try {
                file = file_temp;
                image = new Image(file.toURI().toURL().toString());
                imageExtension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
                imageView.setImage(image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
