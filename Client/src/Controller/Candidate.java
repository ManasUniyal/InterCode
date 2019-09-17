package Controller;

import RequestClasses.InterviewHistory;
import RequestClasses.UploadResume;
import intercode.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Candidate {

    @FXML
    Label userName;

    public void initialize(){
        userName.setText(Main.USER.getUserName());
    }

    public void uploadResumeButton(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("PDF (*pdf)", "*pdf");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(null);
        if(file == null)
            return;
        try {
            byte[] content = Files.readAllBytes(file.toPath());
            String extension = file.getName().substring(file.getName().lastIndexOf(".")+1);
            Main.GAMER.send_message(new UploadResume(Main.USER.getUserID(), content, extension));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updatePictureButton(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.png, *jpg, *jpeg, *img)", "*.png", "*.jpeg", "*.img", "*jpg");
        fileChooser.getExtensionFilters().add(extFilter);

        /*File selectedFileTemp = (fileChooser.showOpenDialog(null));
        selectedFile = (selectedFileTemp == null)? selectedFile : selectedFileTemp;

        try {
            if(selectedFile != null){

                Image image = new Image(selectedFile.toURI().toURL().toString());
                imageView.setImage(image);
                FileExtension fileExtension = new FileExtension(selectedFile);
                extension = fileExtension.getFileExtension();
                System.out.println(extension);
            }
        } catch (MalformedURLException e) {
//            e.printStackTrace();
        }    */
    }

    public void JoinGroupButton(ActionEvent event) {
    }

    public void InterviewHistoryButton(ActionEvent event) {
        try {
            Main.GAMER.send_message(new InterviewHistory(Main.USER.getUserID()));
            Main.GAMER.receive_message();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
