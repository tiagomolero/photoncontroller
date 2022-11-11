package photoncontroller.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/inicial.fxml"));
			Scene scene = new Scene(root,350,300);
			
			primaryStage.setMaximized(false);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Photon Controller");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	

}
