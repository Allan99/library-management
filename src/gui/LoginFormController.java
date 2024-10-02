package gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
	
	private double x = 0;
	private double y = 0;
	
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
					// OPEN NEW DASHBOARD SCENE
					String path = "/gui/Dashboard.fxml";
					Parent root = FXMLLoader.load(getClass().getResource(path));
					Stage stage = new Stage();
					
					stage.initStyle(StageStyle.TRANSPARENT);
					
					root.setOnMousePressed((MouseEvent e) -> {
						x = e.getSceneX();
						y = e.getSceneY();
					});
					
					root.setOnMouseDragged((MouseEvent e) -> {
						stage.setX(e.getScreenX() - x);
						stage.setY(e.getScreenY() - y);
					});
					
					FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
					root = loader.load();
					
					DashboardController dashboardController = loader.getController();
					dashboardController.studentNumber(studentNumber);
					
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
	
	public void numbersOnly(KeyEvent event) {
		if(event.getCharacter().matches("[^\\e\t\r\\d+$]")) {
			event.consume();
			txtStudentNumber.setStyle("-fx-border-color: #e04040");
		}else {
			txtStudentNumber.setStyle("-fx-border-color: #fff");
		}
	}

	private void showAlert(AlertType alertType, String title, String contentText) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(contentText);
		alert.showAndWait();
	}

}
