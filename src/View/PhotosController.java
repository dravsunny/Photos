package View;

import java.io.*;

import View.Helper.Admin;
import View.Helper.User;
import View.Utility.readwriteObjects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PhotosController{
	
	
	@FXML
	TextField username;
	@FXML
	TextField password;
	@FXML
	Button Login;
	@FXML
	Label Login_Error;
	
	
	public Stage stage = new Stage();
	
	
	public void initiate(Stage stage)
	{
		this.stage = stage;
	}
	public void loginUser(ActionEvent e) throws ClassNotFoundException, IOException
	{
		
		
		String user = username.getText();
		String pass = password.getText();
		
		if(user.equals("") || pass.equals(""))
		{
			Login_Error.setText("Must put in PASSWORD and USERNAME");
		}
		if(user.equals("admin"))
		{
			Admin a = readwriteObjects.readAdmin(user);
			
			if(a == null)
			{
				Admin admin = new Admin("admin", "admin");
				User stock = new User("stock", "stock");
				admin.addUser(stock.getUsername(), stock);
				
				FXMLLoader loader = new FXMLLoader();   
				loader.setLocation(
						getClass().getResource("/View/av.fxml"));
				
				AnchorPane root = (AnchorPane)loader.load();

				AdminController listController = loader.getController();
				listController.initiate(stage, admin);

				Scene scene = new Scene(root, 600, 500);
				
				stage.setScene(scene);
				stage.setResizable(false);
				stage.setTitle("Administrator");
				stage.show(); 
				
			}
			else
			{
				if(pass.equals(a.getPassword()))
				{
					FXMLLoader loader = new FXMLLoader();   
					loader.setLocation(
							getClass().getResource("/View/av.fxml"));
					
					AnchorPane root = (AnchorPane)loader.load();

					AdminController listController = loader.getController();
					listController.initiate(stage, a);

					Scene scene = new Scene(root, 600, 500);
					
					stage.setScene(scene);
					stage.setResizable(false);
					stage.setTitle("Administrator");
					stage.show(); 
				}
				else
				{
					Login_Error.setText("Incorrect Password");
				}
			}
		}
		else
		{
			User u = readwriteObjects.readUser(user);
			
			if(u == null)
			{
				Login_Error.setText("No user exists with this USERNAME");
			}
			else
			{
				if(!pass.equals(u.getPassword()))
				{
					Login_Error.setText("Incorrect Password");
				}
				else
				{
					FXMLLoader loader = new FXMLLoader();   
					loader.setLocation(
							getClass().getResource("/View/uv.fxml"));
					
					TabPane root = (TabPane)loader.load();

					UserController listController = loader.getController();
					listController.initiate(stage, u);

					Scene scene = new Scene(root, 900, 600);
					
					stage.setScene(scene);
					stage.setResizable(false);
					stage.centerOnScreen();
					stage.setTitle(user);
					stage.show(); 
				}
			}
			
			
		}
		
		

		
	}
	
	
}
