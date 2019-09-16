package Controller;

import Chat.Request.Message;
import intercode.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ChatArea {


	@FXML
	private TextField content;

	@FXML
	private ListView<String> list;



	public void send(ActionEvent actionEvent) {

		System.out.println("sending from "+Main.NAME+Main.GROUP);
		Main.CHATTER.sendMessage(new Message(Main.NAME,Main.GROUP,content.getText()));

	}

}
