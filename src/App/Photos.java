package App;

//Created by Jaideep Duggempudi and Suneet Dravida
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import View.PhotosController;

public class Photos extends Application{
	
	public void start(Stage primaryStage) throws Exception {
		
		
		// set up FXML loader
		FXMLLoader loader = new FXMLLoader();   
		loader.setLocation(
				getClass().getResource("/View/Login.fxml"));
		
		// load the fxml
		AnchorPane root = (AnchorPane)loader.load();

		// get the controller (Do NOT create a new Controller()!!)
		// instead, get it through the loader
		PhotosController listController = loader.getController();
		listController.initiate(primaryStage);

		Scene scene = new Scene(root, 590, 500);
		
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Photo Library");
		primaryStage.show(); 
		

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}
}

