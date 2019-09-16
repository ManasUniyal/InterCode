package Controller;

import intercode.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;

public class Controller2 {

//	Button welcomeClick;

	public void create_room(){

		System.out.println("Room Creating initiated");

		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("../FXML/CreateRoom.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Main.PRIMARYSTAGE.setScene(new Scene(root,Main.WIDTH,Main.HEIGHT));

	}

	public void join_room(){
		System.out.println("Room Joining Initiated");

		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("../FXML/JoinRoom.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Main.PRIMARYSTAGE.setScene(new Scene(root,Main.WIDTH,Main.HEIGHT));

	}

}
