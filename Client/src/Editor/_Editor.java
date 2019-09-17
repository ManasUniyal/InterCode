package Editor;

import intercode.Main;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class _Editor {

	private SupportedKeywords kw = new SupportedKeywords();
	private HighlightText languageHighlighter = new HighlightText(Color.GRAY);
	AutoComplete autocomplete;
	private boolean hasListener = false;
	private boolean edit = false;

	/*      ShortCuts       */

	public static final KeyCombination clearShortcut = new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN);

	private Button clear, bold, italic;


	private JTextArea textArea ;

	public JTextArea getEditor(){
		return this.textArea;
	}


	private AnchorPane pane;


	private JToolBar mainToolbar;

	Button newButton, openButton, saveButton, clearButton, quickButton,  closeButton, boldButton, italicButton, aboutMeButton;


	JScrollPane scrollPane;





	public void start() {
		pane = new AnchorPane();

		final SwingNode swingNode = new SwingNode();
		createAndSetSwingContent(swingNode);

		AnchorPane.setTopAnchor(swingNode, 100d);
		AnchorPane.setBottomAnchor(swingNode, 20d);
		AnchorPane.setRightAnchor(swingNode, 20d);
		AnchorPane.setLeftAnchor(swingNode, 20d);

		pane.getChildren().add(swingNode);



		buttonInitialize();
		buttonActionSet();

		Main.PRIMARYSTAGE.setScene(new Scene(pane, 1200, 1000));
		Main.PRIMARYSTAGE.show();
	}

	private void buttonInitialize() {
		clear = new Button("Clear");
		clear.setText("Clear");
		pane.getChildren().add(clear);
	}

	private void buttonActionSet() {
		clear.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				textArea.setText("");
			}
		});
	}

	private void createAndSetSwingContent(final SwingNode swingNode) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
//				final JTextArea textArea ;
				textArea = new JTextArea("", 0, 0);
				textArea.setFont(new Font("Century Gothic", Font.PLAIN, 12));
				textArea.setTabSize(2);
				textArea.setLineWrap(true);

				textArea.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent ke) {
						edit = true;
						languageHighlighter.highLight(textArea, kw.getCppKeywords());
						languageHighlighter.highLight(textArea, kw.getJavaKeywords());
					}
				});

				scrollPane = new JScrollPane(textArea);
				textArea.setWrapStyleWord(true);
				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

//				toolbar();


				//          actions adding

//				textArea.addKeyListener(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
//					@Override
//					public void handle(KeyEvent event) {
//						if (clearShortcut.match(event)) {
//
//						}
//					}
//
//				});

//				textArea.addActionListener(this);
//				textArea.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_MASK));







				swingNode.setContent(scrollPane);

			}
		});
	}







	/*





	selectAllAction = new SelectAllAction("Select All", clearIcon, "Select all text", new Integer(KeyEvent.VK_A),
                textArea);









	 */






	/*public void toolbar(){
		mainToolbar = new JToolBar();
		scrollPane.add(mainToolbar, BorderLayout.NORTH);
		// used to create space between button groups
		newButton = new JButton("New");
		newButton.setTooltip(new Tooltip("New"));
//		newButton.addActionListener(this);
		mainToolbar.add(newButton);
		mainToolbar.addSeparator();

//		openButton = new JButton("Open");
//		openButton.setToolTipText("Open");
//		openButton.addActionListener(this);
//		mainToolbar.add(openButton);
//		mainToolbar.addSeparator();

//		saveButton = new JButton("Save");
//		saveButton.setToolTipText("Save");
//		saveButton.addActionListener(this);
		mainToolbar.add(saveButton);
		mainToolbar.addSeparator();

		clearButton = new JButton("Clear");
//		clearButton.setToolTipText("Clear All");
//		clearButton.addActionListener(this);
		mainToolbar.add(clearButton);
		mainToolbar.addSeparator();

		quickButton = new JButton("Search");
//		quickButton.setToolTipText("Quick Search");
//		quickButton.addActionListener(this);
		mainToolbar.add(quickButton);
		mainToolbar.addSeparator();

		aboutMeButton = new JButton(aboutMeIcon);
//		aboutMeButton.setToolTipText("About Me");
		aboutMeButton.addActionListener(this);
		mainToolbar.add(aboutMeButton);
		mainToolbar.addSeparator();
//
		aboutButton = new JButton(aboutIcon);
		aboutButton.setToolTipText("About NotePad PH");
//		aboutButton.addActionListener(this);
		mainToolbar.add(aboutButton);
		mainToolbar.addSeparator();
//
		closeButton = new JButton("Close");
		closeButton.setToolTipText("Quit");
//		closeButton.addActionListener(this);
		mainToolbar.add(closeButton);
		mainToolbar.addSeparator();

		boldButton = new JButton("Bold");
		boldButton.setToolTipText("Bold");
//		boldButton.addActionListener(this);
		mainToolbar.add(boldButton);
		mainToolbar.addSeparator();

		italicButton = new JButton("Itallic");
		italicButton.setToolTipText("Italic");
//		italicButton.addActionListener(this);
		mainToolbar.add(italicButton);
		mainToolbar.addSeparator();

	}*/



}