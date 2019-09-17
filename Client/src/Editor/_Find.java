package Editor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.swing.*;

public class _Find {

	@FXML
	private TextField textF;

	@FXML
	private TextField textR;

	private JTextArea txt;

	int startIndex=0;
	int select_start=-1;

	public JTextArea getTxt() {
		return txt;
	}

	public void setTxt(JTextArea txt) {
		this.txt = txt;
	}

	public void find(ActionEvent actionEvent) {
		select_start = txt.getText().toLowerCase().indexOf(textF.getText().toLowerCase());
		if(select_start == -1)
		{
			startIndex = 0;
			return;
		}
		if(select_start == txt.getText().toLowerCase().lastIndexOf(textF.getText().toLowerCase()))
		{
			startIndex = 0;
		}
		int select_end = select_start + textF.getText().length();
		txt.select(select_start, select_end);

	}

	public void replace(ActionEvent actionEvent) {

		try
		{
			find(actionEvent);
			if (select_start != -1)
				txt.replaceSelection(textR.getText());
		}
		catch(NullPointerException e)
		{
			System.out.print("Null Pointer Exception: "+e);
		}

	}

	public void findNext(ActionEvent actionEvent) {
		String selection = txt.getSelectedText();
		try
		{
			selection.equals("");
		}
		catch(NullPointerException e)
		{
			selection = textF.getText();
			try
			{
				selection.equals("");
			}
			catch(NullPointerException e2)
			{
				selection = JOptionPane.showInputDialog("Find:");
				textF.setText(selection);
			}
		}
		try
		{
			int select_start = txt.getText().toLowerCase().indexOf(selection.toLowerCase(), startIndex);
			int select_end = select_start+selection.length();
			txt.select(select_start, select_end);
			startIndex = select_end+1;

			if(select_start == txt.getText().toLowerCase().lastIndexOf(selection.toLowerCase()))
			{
				startIndex = 0;
			}
		}
		catch(NullPointerException e)
		{}
	}

	public void replaceAll(ActionEvent actionEvent) {
		txt.setText(txt.getText().toLowerCase().replaceAll(textF.getText().toLowerCase(), textR.getText()));
	}
}
