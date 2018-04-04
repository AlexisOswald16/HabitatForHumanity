package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	private LoginModel loginModel = new LoginModel();
	Main main = new Main();
	Stage prevStage;

	@FXML
	private Label messageLbl;
	@FXML
	private TextField userNameField;
	@FXML
	private PasswordField passwordField;

	public void setPrevStage(Stage stage) {
		this.prevStage = stage;
	}

	public void login(ActionEvent event) {
		messageLbl.setText("Button pressed.");

	}

	public void createAccount(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		Pane myPane = null;
		myPane = FXMLLoader.load(getClass().getResource("CreateAccountView.fxml"));
		Scene scene = new Scene(myPane);
		stage.setScene(scene);
		prevStage.close();
		stage.show();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
