package gui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.dao.BookDao;
import model.dao.DaoFactory;
import model.dao.StudentDao;
import model.dao.TakeDao;
import model.entities.AvailableBook;
import model.entities.Student;
import model.entities.Take;

public class DashboardController implements Initializable {

	@FXML
	private ImageView take_availableBookImage;

	@FXML
	private TextField take_FirstName;

	@FXML
	private ComboBox<?> take_Gender;

	@FXML
	private Label take_IssuedDate;

	@FXML
	private TextField take_LastName;

	@FXML
	private Label take_StudentNumber;

	@FXML
	private Label take_author_label;

	@FXML
	private TextField take_bookTitle;

	@FXML
	private Button take_clearBtn;

	@FXML
	private Label take_date_label;

	@FXML
	private Label take_genre_label;

	@FXML
	private Button take_takeBtn;

	@FXML
	private Label take_title_label;

	@FXML
	private Label currentForm_lbl;

	@FXML
	private AnchorPane savedBook_form;

	@FXML
	private AnchorPane returnBook_form;

	@FXML
	private AnchorPane issue_form;

	@FXML
	private Button halfNav_availableBookBtn;

	@FXML
	private AnchorPane halfNav_form;

	@FXML
	private Button halfNav_returnBtn;

	@FXML
	private Button halfNav_saveBtn;

	@FXML
	private Button halfNav_takeBtn;

	@FXML
	private AnchorPane mainCenter_form;

	@FXML
	private AnchorPane nav_form;

	@FXML
	private Button bars_btn;

	@FXML
	private Button arrow_btn;

	@FXML
	private Button close_btn;

	@FXML
	private Button minimize_btn;

	@FXML
	private Button availableBooks_btn;

	@FXML
	private AnchorPane availableBooks_form;

	@FXML
	private ImageView availableBooks_img;

	@FXML
	private TableView<AvailableBook> availableBooks_table;

	@FXML
	private Label availableBooks_title;

	@FXML
	private Circle circle_image;

	@FXML
	private TableColumn<AvailableBook, String> col_av_author;

	@FXML
	private TableColumn<AvailableBook, String> col_av_bookTitle;

	@FXML
	private TableColumn<AvailableBook, String> col_av_bookType;

	@FXML
	private TableColumn<AvailableBook, String> col_av_publishedDate;

	@FXML
	private Button edit_btn;

	@FXML
	private Button issueBooks_btn;

	@FXML
	private Button logout_btn;

	@FXML
	private Button returnBooks_btn;

	@FXML
	private Button save_btn;

	@FXML
	private Button savedBooks_btn;

	@FXML
	private Label studentNumber_lbl;

	@FXML
	private Button take_btn;

	private Image image;

	private String comboBox[] = { "Male", "Female", "Others" };

	public void gender() {

		List<String> combo = new ArrayList<String>();

		for (String data : comboBox) {

			combo.add(data);
		}

		ObservableList list = FXCollections.observableList(combo);

		take_Gender.setItems(list);
	}

	public void findBook(ActionEvent event) {
		BookDao bookDao = DaoFactory.createBookDao();

		Alert alert;

		if (take_bookTitle.getText().isEmpty()) {
			showAlert(AlertType.ERROR, "Admin message", "Please select the book.");
		} else if (take_FirstName.getText().isEmpty() || take_LastName.getText().isEmpty()
				|| take_Gender.getSelectionModel().isEmpty()) {

			showAlert(AlertType.ERROR, "Admin Message", "Please inform all of the student details.");

		} else {
			AvailableBook book = bookDao.findByTitle(take_bookTitle.getText());
			if (book == null) {
				take_title_label.setText("Book not available");
				take_author_label.setText("");
				take_genre_label.setText("");
				take_date_label.setText("");
				take_availableBookImage.setImage(null);
			} else {
				take_title_label.setText(book.getTitle());
				take_author_label.setText(book.getAuthor());
				take_genre_label.setText(book.getGenre());
				take_date_label.setText(book.getDate().toString());

				String uri = "file:" + book.getImage();

				image = new Image(uri, 134, 171, false, true);
				take_availableBookImage.setImage(image);
			}
		}
	}
	
	public void studentNumberLabel(String studentNumber) {
		take_StudentNumber.setText(studentNumber);
	}
	
	public void clearTakeData() {
		take_bookTitle.setText("");
		take_title_label.setText("");
		take_author_label.setText("");
		take_genre_label.setText("");
		take_date_label.setText("");
		take_availableBookImage.setImage(null);
	}

	/*public void clearFindData() {
		take_bookTitle.setText("");
		take_title_label.setText("");
		take_author_label.setText("");
		take_genre_label.setText("");
		take_date_label.setText("");
	}*/
	
	public void takeBook(){
		
		Alert alert;
		
		if(take_FirstName.getText().isEmpty() 
				|| take_LastName.getText().isEmpty()
				|| take_Gender.getSelectionModel().isEmpty()) {
			
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Admin Message");
			alert.setHeaderText(null);
			alert.setContentText("Please type complete Student Details");
			alert.showAndWait();
		}else if(take_title_label.getText().isEmpty()){
			alert = new Alert(AlertType.ERROR);
			alert.setTitle("Admin Message");
			alert.setHeaderText(null);
			alert.setContentText("Please indicate the book you want to take.");
			alert.showAndWait();
		}else {
			
			TakeDao takeDao = DaoFactory.createTakeDao();
			BookDao bookDao = DaoFactory.createBookDao();
			
			AvailableBook book = bookDao.findByTitle(take_title_label.getText());
			
			Take take = new Take();
			take.setStudentNumber(take_StudentNumber.getText());
			take.setFirstName(take_FirstName.getText());
			take.setLastName(take_LastName.getText());
			take.setGender(take_Gender.getValue().toString());
			take.setBookTitle(book.getTitle());
			take.setImage(book.getImage());
			take.setDate(new Date());
			
			String check = "Not Return";
			
			take.setCheckReturn(check);
			
			takeDao.insert(take);
			
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Admin Message");
			alert.setHeaderText(null);
			alert.setContentText("Successful!");
			alert.showAndWait();
			
			clearTakeData();
		}
	}

	public void displayDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		
		take_IssuedDate.setText(date);
	}
	
	public ObservableList<AvailableBook> dataList() {

		BookDao bookDao = DaoFactory.createBookDao();

		ObservableList<AvailableBook> listBooks = FXCollections.observableList(bookDao.findAll());

		return listBooks;

	}

	private ObservableList<AvailableBook> listBooks;

	public void showAvailableBooks() {
		listBooks = dataList();

		col_av_bookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
		col_av_author.setCellValueFactory(new PropertyValueFactory<>("author"));
		col_av_bookType.setCellValueFactory(new PropertyValueFactory<>("genre"));
		col_av_publishedDate.setCellValueFactory(new PropertyValueFactory<>("date"));

		availableBooks_table.setItems(listBooks);
	}

	public void abTakeButton(ActionEvent event) {

		if (event.getSource() == take_btn) {
			issue_form.setVisible(true);
			savedBook_form.setVisible(false);
			availableBooks_form.setVisible(false);
			returnBook_form.setVisible(false);
		}
	}

	public void navButtonDesign(ActionEvent event) {
		if (event.getSource() == availableBooks_btn || event.getSource() == halfNav_availableBookBtn) {

			currentForm_lbl.setText("Available Books");

			issue_form.setVisible(false);
			savedBook_form.setVisible(false);
			availableBooks_form.setVisible(true);
			returnBook_form.setVisible(false);

			availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
			issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
			returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
			savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

			halfNav_availableBookBtn
					.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
			halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
			halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
			halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

		} else if (event.getSource() == issueBooks_btn || event.getSource() == halfNav_takeBtn) {

			currentForm_lbl.setText("Issue Books");

			issue_form.setVisible(true);
			savedBook_form.setVisible(false);
			availableBooks_form.setVisible(false);
			returnBook_form.setVisible(false);

			issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
			availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
			returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
			savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

			halfNav_availableBookBtn
					.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
			halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
			halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
			halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

		} else if (event.getSource() == returnBooks_btn || event.getSource() == halfNav_returnBtn) {

			currentForm_lbl.setText("Return Books");

			issue_form.setVisible(false);
			savedBook_form.setVisible(false);
			availableBooks_form.setVisible(false);
			returnBook_form.setVisible(true);

			returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
			availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
			issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
			savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

			halfNav_availableBookBtn
					.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
			halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
			halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
			halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

		} else if (event.getSource() == savedBooks_btn || event.getSource() == halfNav_saveBtn) {

			currentForm_lbl.setText("Saved Books");

			issue_form.setVisible(false);
			savedBook_form.setVisible(true);
			availableBooks_form.setVisible(false);
			returnBook_form.setVisible(false);

			savedBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");
			availableBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
			issueBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
			returnBooks_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");

			halfNav_availableBookBtn
					.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
			halfNav_takeBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
			halfNav_returnBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #344275, #3a6389);");
			halfNav_saveBtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #46589a, #4278a7);");

		}
	}

	public void selectAvailableBooks() {
		AvailableBook bookData = availableBooks_table.getSelectionModel().getSelectedItem();

		int num = availableBooks_table.getSelectionModel().getFocusedIndex();

		if ((num - 1) < -1)
			return;

		availableBooks_title.setWrapText(true);
		availableBooks_title.setText(bookData.getTitle());

		String uri = "file:" + bookData.getImage();

		image = new Image(uri, 134, 171, false, true);
		availableBooks_img.setImage(image);
	}

	public void studentNumber(String studentNumber) {
		studentNumber_lbl.setText(studentNumber);
	}

	private double x = 0;
	private double y = 0;

	public void sliderArrow() {
		TranslateTransition slide = new TranslateTransition();
		slide.setDuration(Duration.seconds(.5));
		slide.setNode(nav_form);
		slide.setToX(-234);

		TranslateTransition slide1 = new TranslateTransition();
		slide1.setDuration(Duration.seconds(.5));
		slide1.setNode(mainCenter_form);
		slide1.setToX(-234 + 90);

		TranslateTransition slide2 = new TranslateTransition();
		slide2.setDuration(Duration.seconds(.5));
		slide2.setNode(halfNav_form);
		slide2.setToX(0);

		slide.setOnFinished((ActionEvent event) -> {

			arrow_btn.setVisible(false);
			bars_btn.setVisible(true);

		});

		slide2.play();
		slide1.play();
		slide.play();
	}

	public void sliderBars() {
		TranslateTransition slide = new TranslateTransition();
		slide.setDuration(Duration.seconds(.5));
		slide.setNode(nav_form);
		slide.setToX(0);

		TranslateTransition slide1 = new TranslateTransition();
		slide1.setDuration(Duration.seconds(.5));
		slide1.setNode(mainCenter_form);
		slide1.setToX(0);

		TranslateTransition slide2 = new TranslateTransition();
		slide2.setDuration(Duration.seconds(.5));
		slide2.setNode(halfNav_form);
		slide2.setToX(-90);

		slide.setOnFinished((ActionEvent event) -> {

			arrow_btn.setVisible(true);
			bars_btn.setVisible(false);

		});

		slide2.play();
		slide1.play();
		slide.play();
	}

	public void logout(ActionEvent event) {
		try {
			if (event.getSource() == logout_btn) {

				Parent root = FXMLLoader.load(getClass().getResource("/gui/LoginForm.fxml"));
				Stage stage = new Stage();
				Scene scene = new Scene(root);

				root.setOnMousePressed((MouseEvent e) -> {

					x = e.getSceneX();
					y = e.getSceneY();

				});

				root.setOnMouseDragged((MouseEvent e) -> {
					stage.setX(e.getScreenX() - x);
					stage.setY(e.getScreenY() - y);
				});

				stage.initStyle(StageStyle.TRANSPARENT);

				stage.setScene(scene);
				stage.show();

				logout_btn.getScene().getWindow().hide();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void exit() {
		System.exit(0);
	}

	public void minimize() {

		Stage stage = (Stage) minimize_btn.getScene().getWindow();
		stage.setIconified(true);
	}

	private void showAlert(AlertType alertType, String title, String contentText) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(contentText);
		alert.showAndWait();
	}

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		showAvailableBooks();
		
		gender();
		
		displayDate();

	}

}
