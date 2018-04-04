package application;
	

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Main extends Application {
	Stage mainStage = new Stage();
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader myLoader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
			Pane myPane = (Pane)myLoader.load();
			LoginController controller = (LoginController) myLoader.getController();
			controller.setPrevStage(primaryStage);
			Scene scene = new Scene(myPane);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
