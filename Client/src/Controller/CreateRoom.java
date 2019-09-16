package Controller;

import intercode.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateRoom {

	@FXML
	private TextField name;

	public void pressed(ActionEvent actionEvent) {
		System.out.println("Creating Room");
		Main.GROUP = name.getText();
		Main.CHATTER.createGroup(name.getText());
		System.out.println("Created");
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("../FXML/ChatArea.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Main.PRIMARYSTAGE.setScene(new Scene(root,Main.WIDTH,Main.HEIGHT));
	}

}
