package com.krishnan.balaji.jfx.ch2.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class FXMLDocumentController implements Initializable {

	@FXML
	private Label label;
	@FXML
	private Text actiontarget;

	@FXML
	private void handleSubmitButtonAction(ActionEvent event) {
		actiontarget.setText("Sign in button was pressed");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

}