package gui;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
import model.entities.AvailableBook;

public class DashboardController implements Initializable {

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

	public void selectAvailableBooks() {
		AvailableBook bookData = availableBooks_table.getSelectionModel().getSelectedItem();

		int num = availableBooks_table.getSelectionModel().getFocusedIndex();

		if ((num - 1) < -1)
			return;

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

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {

		showAvailableBooks();

	}

}
