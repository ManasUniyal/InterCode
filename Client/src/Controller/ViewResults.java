package Controller;

import DataClasses.InterviewDetails;
import RequestClasses.InterviewLog;
import intercode.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class ViewResults {

    @FXML
    private TableView<InterviewDetails> tableView;

    @FXML
    private TableColumn<InterviewDetails, String> candidateName;

    @FXML
    private TableColumn<InterviewDetails, String> duration;

    @FXML
    private TableColumn<InterviewDetails, String> rating;

    @FXML
    private TableColumn<InterviewDetails, String> reviews;

    public void initialize(){
        try {

            Main.GAMER.send_message(new InterviewLog(Main.USER.getUserID(), Main.USER.getMode()));
            InterviewLog interviewLog = (InterviewLog) Main.GAMER.receive_message();

            candidateName.setCellValueFactory(new PropertyValueFactory<InterviewDetails, String>("userDetails"));
            duration.setCellValueFactory(new PropertyValueFactory<InterviewDetails, String>("duration"));
            rating.setCellValueFactory(new PropertyValueFactory<InterviewDetails, String>("rating"));
            reviews.setCellValueFactory(new PropertyValueFactory<InterviewDetails, String>("reviews"));

            ObservableList observableList = FXCollections.observableArrayList();
            observableList.add(interviewLog.getDetails());
            tableView.setItems(observableList);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
