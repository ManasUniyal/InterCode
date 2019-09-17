package Editor;

import intercode.Main;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Editor {

	@FXML
	private SwingNode swingContainer;






	private SupportedKeywords kw = new SupportedKeywords();
	private HighlightText languageHighlighter = new HighlightText(Color.GRAY);
	AutoComplete autocomplete;
	private boolean hasListener = false;
	private boolean edit = false;

	/*      ShortCuts       */

	public static final KeyCombination clearShortcut = new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN);



	private JTextArea textArea ;

	public JTextArea getEditor(){
		return this.textArea;
	}


	public void initialize(){

		textArea = new JTextArea("", 100, 200);
		textArea.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		textArea.setTabSize(2);
		textArea.setLineWrap(true);
//		textArea.addKeyListener(new KeyAdapter() {
//			public void keyPressed(KeyEvent ke) {
//				edit = true;
//				languageHighlighter.highLight(textArea, kw.getCppKeywords());
//				languageHighlighter.highLight(textArea, kw.getJavaKeywords());
//			}
//		});
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setWrapStyleWord(true);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		swingContainer.setContent(scrollPane);
		System.out.println("hi");
		System.out.println(swingContainer.getContent());

//		Main.PRIMARYSTAGE.setScene(this.ge);
//		Main.PRIMARYSTAGE.show();

	}

	public void clear(ActionEvent actionEvent) {
		textArea.setText("");
	}

	public void changeStyle(ActionEvent actionEvent) {
	}

	public void findReplace(ActionEvent actionEvent) {
	}

	public void itallic(ActionEvent actionEvent) {
	}

	public void increaseSIze(ActionEvent actionEvent) {
	}
}
