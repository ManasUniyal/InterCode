package Controller;

import Chat.Main.ChatterClient;
import Editor.*;
import Windows.AlertBox;
import intercode.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainController {

    @FXML
    Button uploadQuestion;

    @FXML
    Button downloadCode;

    @FXML
    ComboBox selectLanguage;

    @FXML
    private SwingNode swingContainer;

    @FXML
    TextField questionTextField;

    private JTextArea textArea;
    private SupportedKeywords kw = new SupportedKeywords();
    private HighlightText languageHighlighter = new HighlightText(Color.GRAY);
    private String language;

    AutoComplete autocomplete;
    String[] fonts;

    public void initialize(){

//        if(Main.USER.getMode() == 2) {    //Candidate
//            uploadQuestion.setVisible(false);
//            downloadCode.setVisible(false);
//        }

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("C");
        arrayList.add("C++");
        arrayList.add("Java");
        arrayList.add("Python");

        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        selectLanguage.setItems(observableList);

        textArea = new JTextArea("", 100, 200);
        textArea.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        textArea.setTabSize(2);
        textArea.setLineWrap(true);

        /*      Setting constants       */

        fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        /*      Text Area actions       */

        /*      Text Highlight Feature      */

        textArea.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                languageHighlighter.highLight(textArea, kw.getKeywords());
            }
        });

        /*          Auto Complete       */

        autocomplete = new AutoComplete(textArea, new ArrayList(Arrays.asList(kw.getKeywords())));

        /*              Auto Complete       */

        textArea.getDocument().addDocumentListener(autocomplete);

        /*      Putting text area on scroll on Anchor pane      */

        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setWrapStyleWord(true);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        swingContainer.setContent(scrollPane);
    }

    public void addQuestionPressed(ActionEvent event) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Upload question");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../FXML/AddQuestion.fxml"));
            Parent parent = fxmlLoader.load();
            AddQuestion addQuestion = fxmlLoader.getController();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();
            addQuestion.setFields(questionTextField, stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*      ShortCuts       */

//	public static final KeyCombination clearShortcut = new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN);

    public JTextArea getEditor() {
        return this.textArea;
    }


    public void clear(ActionEvent actionEvent) {
        textArea.setText("");
    }

    public void changeStyle(ActionEvent actionEvent) {

        String p = fonts[new Random().nextInt(20)];
        //Getting size of the current font or text
        int s = textArea.getFont().getSize();
        textArea.setFont(new Font(p, Font.PLAIN, s));

    }

    public void findReplace(ActionEvent actionEvent) {
        Stage window = new Stage();

        //Block events to other windows

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Find Replace");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../Editor/find.fxml"));
        try {
            Parent parent = fxmlLoader.load();
            _Find _find = fxmlLoader.getController();
            Scene scene = new Scene(parent);
            window.setScene(scene);
            window.show();
            _find.setTxt(textArea);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void italic(ActionEvent actionEvent) {
        int sizeOfFont = textArea.getFont().getSize();
//		String fontFamily = textArea.getFont().getFamily();
//		sizeOfFont += 1;
        String fontFamily = textArea.getFont().getFamily();
//		String fontFamily = textArea.getFont().getFamily();
        int fontStyle = textArea.getFont().getStyle();
        if (fontStyle == Font.ITALIC){
            fontStyle = Font.PLAIN;
        }else {
                fontStyle = Font.ITALIC;
        }
        Font font1 = new Font(fontFamily, fontStyle, sizeOfFont);
        textArea.setFont(font1);

    }

    public void bold(ActionEvent actionEvent) {
        int sizeOfFont = textArea.getFont().getSize();
//		String fontFamily = textArea.getFont().getFamily();
//		sizeOfFont += 1;
        String fontFamily = textArea.getFont().getFamily();
        int fontStyle = textArea.getFont().getStyle();
        if (fontStyle == Font.BOLD){
            fontStyle = Font.PLAIN;
        }else {
            fontStyle = Font.BOLD;
        }
        Font font1 = new Font(fontFamily, fontStyle, sizeOfFont);
        textArea.setFont(font1);

    }

    public void increaseSize(ActionEvent actionEvent) {
        int sizeOfFont = textArea.getFont().getSize();
//		String fontFamily = textArea.getFont().getFamily();
        sizeOfFont += 1;
        String fontFamily = textArea.getFont().getFamily();
        int fontStyle = textArea.getFont().getStyle();
        Font font1 = new Font(fontFamily, fontStyle, sizeOfFont);
        textArea.setFont(font1);

    }

    public void decreaseSize(ActionEvent actionEvent){

        int sizeOfFont = textArea.getFont().getSize();
//		String fontFamily = textArea.getFont().getFamily();
        sizeOfFont -= 1;
        if (sizeOfFont<2){
            sizeOfFont = 2;
        }
        String fontFamily = textArea.getFont().getFamily();
        int fontStyle = textArea.getFont().getStyle();
        Font font1 = new Font(fontFamily, fontStyle, sizeOfFont);
        textArea.setFont(font1);

    }

    public void find(ActionEvent actionEvent){
        new Find(textArea);
    }

    public void downloadCodePressed(ActionEvent event) {
        String text = textArea.getText();
        if(selectLanguage.getValue() != null) {
            File file = new File("Code." + selectLanguage.getValue());
            try {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(text);
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void compileButtonPressed(ActionEvent event) {
        if(selectLanguage.getValue() == null){
            new AlertBox("Error", "Select a language").start();
            return;
        }
        //Run on sample test cases
    }

    public void customInputPressed(ActionEvent event){
        Stage stage = new Stage();
        stage.setTitle("Custom Input");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../FXML/CustomInput.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
            CustomInput customInput = fxmlLoader.getController();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            customInput.setStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runPressed(ActionEvent event){

    }

    public void compileButtonPressed(){
        if(selectLanguage.getValue() == null){
            new AlertBox("Error", "Select a language");
            return;
        }
        String code = textArea.getText();
        String directoryPath = "src/"+ ChatterClient.GROUP;
        new File(directoryPath).mkdir();
        File file = new File(directoryPath+"/input."+selectLanguage.getValue());
        try {
            FileWriter fileWriter = new FileWriter(file);
            byte[] content = Files.readAllBytes(file.toPath());
            //Write query for compiling, dta to be sent ot server
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
