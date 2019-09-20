package Controller;

import Chat.Main.ChatterClient;
import RequestClasses.Response;
import RequestClasses.UploadQuestion;
import Windows.AlertBox;
import intercode.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;

public class AddQuestion {

    @FXML
    Button uploadQuestionButton;

    @FXML
    Button uploadTestCasesButton;

    @FXML
    Button uploadOutputButton;

    @FXML
    Button uploadSampleTestButton;

    @FXML
    Button uploadSampleTestOutputButton;

    @FXML
    Label uploadQuestionLabel;

    @FXML
    Label testCaseLabel;

    @FXML
    Label outputLabel;

    @FXML
    Label sampleTestlabel;

    @FXML
    Label sampleTestOutputLabel;

    private Stage stage;
    private String question;
    private TextField textField;
    private int check = 0;

    public void addQuestionButtonPressed(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Text files (.txt)", ".txt");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(null);
        if(file == null)
            return;
        int type = 0;
        Button source = (Button) event.getSource();
        if (source == uploadQuestionButton) {
            uploadQuestionLabel.setText("Question uploaded");
            question = new String();
            String str;
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                while((str=br.readLine()) != null){
                    question += str+"\n";
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            type = 1;

        } else if (source == uploadTestCasesButton) {
            testCaseLabel.setText("Test cases uploaded");
            type = 2;
        } else if (source == uploadOutputButton) {
            outputLabel.setText("Output uploaded");
            type = 3;
        } else if (source == uploadSampleTestButton) {
            sampleTestlabel.setText("Sample test cases uploaded");
            type = 4;
        } else if (source == uploadSampleTestOutputButton) {
            sampleTestOutputLabel.setText("Sample test case output uploaded");
            type = 5;
        }
        try {
            byte[] content = Files.readAllBytes(file.toPath());
            Main.GAMER.send_message(new UploadQuestion(type, ChatterClient.GROUP, content));
            Response response = (Response) Main.GAMER.receive_message();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        check |= (1<<(type-1));
    }

    public void setFields(TextField textField, Stage stage){
        this.textField = textField;
        this.stage = stage;
    }

    public void okPressed(ActionEvent event){
        if(check != 31){
            new AlertBox("Error","All question details not uploaded");
        } else {
            new AlertBox("Success", "Added question successfully");
            textField.setText(question);
            //Set the same question for the other user too
        }
        stage.close();
    }

}
