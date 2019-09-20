package Controller;

import DataClasses.InterviewDetails;
import RequestClasses.*;
import Windows.AlertBox;
import intercode.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Candidate {

    @FXML
    Label userNameLabel;

    @FXML
    Label emailAddressLabel;

    @FXML
    ImageView imageView;

    @FXML
    Button groupButton;

    @FXML
    Button viewResults;

    @FXML
    private TableView<InterviewDetails> tableView;

    @FXML
    private TableColumn<InterviewDetails, String> interViewer;

    @FXML
    private TableColumn<InterviewDetails, String> time;

    @FXML
    private TableColumn<InterviewDetails, String> duration;

    public void initialize(){

        //Setting userName
        userNameLabel.setText(Main.USER.getUserName());
        //Setting emailAddress
        emailAddressLabel.setText(Main.USER.getUserID());

        if(Main.USER.getMode() == 1){
            groupButton.setText("Conduct an interview");
        } else {
            groupButton.setText("Join an interview");
        }

        if(Main.USER.getMode() == 2){
            viewResults.setVisible(false);
        }

        try {

            //Setting profile picture
            Main.GAMER.send_message(new GetProfilePicture(Main.USER.getUserID()));
            GetProfilePicture getProfilePicture = (GetProfilePicture) Main.GAMER.receive_message();
            if(getProfilePicture.getContent() == null){
                imageView.setImage(new Image(new File("src/Icons/newUserIcon.png").toURI().toURL().toString()));
                new AlertBox("Error", "Unable to download picture");
            } else {
                new File("src/ProfilePicture").mkdir();
                File destinationFile = new File("src/ProfilePicture/" + getProfilePicture.getUserID() + "." + getProfilePicture.getExtension());
                Files.write(destinationFile.toPath(), getProfilePicture.getContent());
                imageView.setImage(new Image(destinationFile.toURI().toURL().toString()));
            }

            //For table view

            Main.GAMER.send_message(new InterviewLog(Main.USER.getUserID(), Main.USER.getMode()));
            InterviewLog interviewLog = (InterviewLog) Main.GAMER.receive_message();

            interViewer.setCellValueFactory(new PropertyValueFactory<InterviewDetails, String>("userDetails"));
            time.setCellValueFactory(new PropertyValueFactory<InterviewDetails, String>("time"));
            duration.setCellValueFactory(new PropertyValueFactory<InterviewDetails, String>("duration"));

            ObservableList observableList = FXCollections.observableArrayList();
            observableList.add(interviewLog.getDetails());
            tableView.setItems(observableList);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void JoinGroupButton(ActionEvent event) {
    }

    public void editDetailsButton(ActionEvent event) {
        Stage stage = new Stage();
//        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Edit details");
        System.out.println("Edit button pressed");
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("../FXML/EditDetails.fxml"));
            stage.setScene(new Scene(parent));
            stage.showAndWait();
            userNameLabel.setText(Main.USER.getUserName());
            emailAddressLabel.setText(Main.USER.getEmailAddress());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeProfilePictureLink(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.png, *jpg, *jpeg, *img)", "*.png", "*.jpeg", "*.img", "*jpg");
        fileChooser.getExtensionFilters().add(extFilter);
        File file_temp = fileChooser.showOpenDialog(null);
        if(file_temp != null) {
            try {
                File file = file_temp;
                Image image = new Image(file.toURI().toURL().toString());
                String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
                imageView.setImage(image);
                byte[] content = Files.readAllBytes(file.toPath());

                Main.GAMER.send_message(new SetProfilePicture(Main.USER.getUserID(), content, extension));
                Response response = (Response) Main.GAMER.receive_message();
                if(response.getStatus() == 1){
                    new AlertBox("Error",response.getMessage());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void changePasswordButton(ActionEvent event){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Change password");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../FXML/ChangePasswordScreen.fxml"));
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void viewResultsButton(ActionEvent event) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Results");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../FXML/ViewResults.fxml"));
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
