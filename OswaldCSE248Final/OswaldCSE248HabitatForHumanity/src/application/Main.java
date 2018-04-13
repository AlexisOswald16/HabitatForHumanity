package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class Main extends Application {
	Stage primaryStage;
	Stage alertStage = new Stage();

	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("LoginView.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void changeScene(String fxml, Label onSceneLbl) throws IOException {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
			Stage stage = (Stage) onSceneLbl.getScene().getWindow();
			Scene scene = new Scene(loader.load());
			stage.setScene(scene);
		} catch (IOException io) {
			io.printStackTrace();
		}
	}
	

}
