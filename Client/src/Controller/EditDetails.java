package Controller;

import RequestClasses.GetResume;
import RequestClasses.Response;
import RequestClasses.UpdateDetails;
import Windows.AlertBox;
import intercode.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class EditDetails {

    @FXML
    TextField userNameField;

    @FXML
    TextField emailAddressField;

    @FXML
    TextField phoneNumberField;

    @FXML
    Hyperlink viewResumeLink;

    private File resume;
    private String resumeExtension;


    public void initialize(){

        userNameField.setText(Main.USER.getUserName());
        emailAddressField.setText(Main.USER.getEmailAddress());
        phoneNumberField.setText(Main.USER.getPhoneNumber());

        try{
            Main.GAMER.send_message(new GetResume(Main.USER.getUserID()));
            GetResume getResume = (GetResume) Main.GAMER.receive_message();
            new File("src/Resume").mkdir();
            resume = new File("src/Resume/"+getResume.getUserID()+"."+getResume.getExtension());
            resumeExtension = getResume.getExtension();
            Files.write(resume.toPath(), getResume.getContent());
            viewResumeLink.setText(Main.USER.getUserName()+"'s Resume");
            return;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            viewResumeLink.setText("");
    }

    public void uploadResumePressed(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("PDF (*pdf)", "*pdf");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            resume = file;
            resumeExtension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
            viewResumeLink.setText(resume.getName());
        }
    }

    public void ViewResumePressed(ActionEvent event) {
        try {
            File file = new File("src/Resume/" + Main.USER.getUserID() + "." + Main.USER.getResumeExtension());
            if (file.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(file);
                } else {
                    new AlertBox("Error", "File not supported by system");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Can implement regex check, if time permits
    private boolean check(){
        boolean status = true;
        if(userNameField.getText().length() == 0){
            status = false;
            new AlertBox("Error","User Name field cannot be empty").start();
        } else if(emailAddressField.getText().length() == 0){
            status = false;
            new AlertBox("Error","Email Address field cannot be empty").start();
        } else if(phoneNumberField.getText().length() == 0){
            status = false;
            new AlertBox("Error","Phone number field cannot be empty").start();
        } else if(viewResumeLink.getText().length() == 0){
            status = false;
            new AlertBox("Error", "Upload resume").start();
        }
        return status;
    }

    public void saveButton(ActionEvent event) {
        if(check() == false){
            return;
        }
        try {
            byte[] content = Files.readAllBytes(resume.toPath());
            Main.GAMER.send_message(new UpdateDetails(Main.USER.getUserID(), userNameField.getText(), emailAddressField.getText(), phoneNumberField.getText(), content, resumeExtension));
            Response response = (Response) Main.GAMER.receive_message();

            Main.USER.setUserName(userNameField.getText());
            Main.USER.setEmailAddress(emailAddressField.getText());
            Main.USER.setPhoneNumber(phoneNumberField.getText());

            if(response.getStatus() == 1){
                new AlertBox("Error","").start();
            } else {
                new AlertBox("Success", "Details saved successfully");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
