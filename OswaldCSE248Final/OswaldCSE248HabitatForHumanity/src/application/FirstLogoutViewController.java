package application;

import javafx.event.ActionEvent;

public class FirstLogoutViewController {
	Main main = new Main();

	public void logout(ActionEvent event) {
		main.hideAlertStage();
		// main.setAlertStage("SecondLogoutView.fxml");

	}

	public void back(ActionEvent event) {
		main.hideAlertStage();
	}

}
