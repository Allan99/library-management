package gui;

import java.io.IOException;
import java.sql.Connection;

import db.DB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dao.DaoFactory;
import model.dao.StudentDao;
import model.entities.Student;

public class LoginFormController {

	StudentDao studentDao = DaoFactory.createStudentDao();
	
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
	public void login() {
		String studentNumber = txtStudentNumber.getText();
		String studentPassword = txtStudentPassword.getText();

		Student studentFound = studentDao.findStudentLogin(studentNumber, studentPassword);

		if (studentNumber.isEmpty() || studentPassword.isEmpty()) {
			showAlert(AlertType.ERROR, "Admin Message", "Please fill all blank fields");
		} else {
			if (studentFound!=null && studentFound.getNumber().equalsIgnoreCase(studentNumber)
					&& studentFound.getPassword().equalsIgnoreCase(studentPassword)) {

				showAlert(AlertType.INFORMATION, "Admin Message", "Your login was successfull!");
				
				btnLogin.getScene().getWindow().hide();

				try {
					Parent root = FXMLLoader.load(getClass().getResource("/gui/Dashboard.fxml"));
					Stage stage = new Stage();
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				showAlert(AlertType.ERROR, "Admin Message", "Wrong Username or Password.");
			}
		}

	}

	@FXML
	public void minimize() {
		Stage stage = (Stage) btnMinimize.getScene().getWindow();
		stage.setIconified(true);
	}

	@FXML
	public void exit() {
		System.exit(0);
	}

	private void showAlert(AlertType alertType, String title, String contentText) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(contentText);
		alert.showAndWait();
	}

}
