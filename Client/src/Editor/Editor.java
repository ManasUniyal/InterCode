package Editor;

import intercode.Main;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.Clipboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Editor {

	@FXML
	private SwingNode swingContainer;

	@FXML
	private Button cut;

	String[] fonts;

//	private Clipboard systemClipboard =


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


		fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();





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
	}

	public void itallic(ActionEvent actionEvent) {
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

	public void increaseSIze(ActionEvent actionEvent) {
		int sizeOfFont = textArea.getFont().getSize();
//		String fontFamily = textArea.getFont().getFamily();
		sizeOfFont += 1;
		String fontFamily = textArea.getFont().getFamily();
		int fontStyle = textArea.getFont().getStyle();
		Font font1 = new Font(fontFamily, fontStyle, sizeOfFont);
		textArea.setFont(font1);

	}

}
