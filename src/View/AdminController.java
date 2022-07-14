package View;

import java.io.File;
import java.io.IOException;

import App.Photos;
import View.Helper.Admin;
import View.Helper.User;
import View.Utility.readwriteObjects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AdminController {
	
	@FXML
	Button delete;
	@FXML
	Button create;
	@FXML
	TextField username;
	@FXML
	TextField password;
	@ FXML
	ListView<String> listview;
	@FXML
	Label error;
	
	
	public Stage stage;
	
	public Admin admin;
	
	public ObservableList<String> obsList;
	
	public ObservableMap<String, User> obsmap;
	
	public void initiate(Stage stage, Admin admin)
	{
		this.stage = stage;
		this.admin = admin;
		obsmap = FXCollections.observableMap(admin.getlistofUsers());
		obsList = FXCollections.observableArrayList(obsmap.keySet());
		listview.setItems(obsList);
		
		stage.setOnCloseRequest(event -> {
			try {
				readwriteObjects.writeAdmin(admin);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
	
	}
	public void createUser(ActionEvent e) throws IOException
	{
		
		if(admin.getlistofUsers().keySet().contains(username.getText()))
		{
			error.setText("User alreadys exists with this Username");
		}
		else if(username.getText().equals("") || password.getText().equals("") )
		{
			error.setText("Both USERNAME and PASSWORD must be filled out");
		}
		else
		{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.initOwner(stage);
			alert.setTitle("Add new User");
			alert.setHeaderText("You're about to ADD this user");
			alert.setContentText("Do you want to continue?");
			
			if (alert.showAndWait().get() == ButtonType.OK) {
				User user = new User(username.getText(), password.getText());
				admin.addUser(username.getText(), user);
				listview.getItems().add(username.getText());
				error.setText(" ");
			}	
			
		}
	}
	public void deleteUser(ActionEvent e)
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(stage);
		alert.setTitle("Delete User");
		alert.setHeaderText("You're about to DELETE this user");
		alert.setContentText("Do you want to continue?");
		
		if (alert.showAndWait().get() == ButtonType.OK) {
			
			String filetoDelete = listview.getSelectionModel().getSelectedItem();
			admin.deleteUser(filetoDelete);
			listview.getItems().remove(listview.getSelectionModel().getSelectedIndex());
			File deleteFile = new File("server" + File.separator + filetoDelete);
			deleteFile.delete();
			
		}
		
		
	}
	public void logout(ActionEvent e) throws Exception
	{
		readwriteObjects.writeAdmin(admin);
		Photos logout = new Photos();
		logout.start(stage);
	}
	
}
