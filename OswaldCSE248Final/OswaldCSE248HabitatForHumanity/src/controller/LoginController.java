package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import model.LoginModel;

public class LoginController implements Initializable {
	private LoginModel loginModel = new LoginModel();
	Main main = new Main();
 
	@FXML
	private Label messageLbl;
	@FXML
	private TextField userNameField;
	@FXML
	private PasswordField passwordField;

	public void login(ActionEvent event) throws IOException, SQLException {
		
		if(loginModel.isLogin(userNameField.getText(), passwordField.getText())) {
			main.changeScene("FirstMainSceneView.fxml", messageLbl);
		} else {
			messageLbl.setText("Login Failed, please try again.");
		}

	}

	public void createAccount(ActionEvent event) throws IOException {
		main.changeScene("CreateAccountView.fxml", messageLbl);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		if (loginModel.isDBConnected()) {
//			messageLbl.setText("Connected");
//		} else {
//			messageLbl.setText("Not Connected");
//		}
	}

}
