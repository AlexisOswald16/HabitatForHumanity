package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
	private LoginModel loginModel = new LoginModel();
	
	@FXML 
	private Label messageLbl;
	@FXML
	private TextField userNameField;
	@FXML
	private PasswordField passwordField;
	
	public void login(ActionEvent event){
		messageLbl.setText("Button pressed.");
	}
	
	public void createAccount(ActionEvent event){
		messageLbl.setText("Create Account pressed");
		
	}
}
