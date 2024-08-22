package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginFormController {

	@FXML
	private Button btnClose;

	@FXML
	private Button btnLogin;

	@FXML
	private Button btnMinimize;

	@FXML
	private TextField txtStudentNumber;

	@FXML
	private PasswordField txtStudentPassword;

	@FXML
	public void minimize() {
		Stage stage = (Stage)btnMinimize.getScene().getWindow();
		stage.setIconified(true);
	}
	
	@FXML
	public void exit() {
		System.exit(0);
	}

}
