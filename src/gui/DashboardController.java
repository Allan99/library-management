package gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class DashboardController {
	
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
	private TableView<?> availableBooks_table;

	@FXML
	private Label availableBooks_title;

	@FXML
	private Circle circle_image;

	@FXML
	private TableColumn<?, ?> col_av_author;

	@FXML
	private TableColumn<?, ?> col_av_bookTitle;

	@FXML
	private TableColumn<?, ?> col_av_bookType;

	@FXML
	private TableColumn<?, ?> col_av_publishedDate;

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
		slide1.setToX(-234+90);
		
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
}