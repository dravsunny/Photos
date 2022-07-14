package View.Helper;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import View.Utility.readwriteObjects;
import javafx.util.Callback;

public class Admin implements Serializable{
	
	public String username;
	public String password;
	
	public HashMap<String, User> listofUsers;
	
	
	public Admin(String username, String password)
	{
		this.username = username;
		this.password = password;
		
		listofUsers = new HashMap<String, User>();
		
	}
	
	public String getUsername()
	{
		return username;
	}
	public String getPassword()
	{
		return password;
	}
	public void addUser(String u, User user) throws IOException 
	{
		readwriteObjects.writeUser(user);
		listofUsers.put(u, user);
	}
	public void deleteUser(String u)
	{
		listofUsers.remove(u);
	}
	public HashMap <String, User> getlistofUsers()
	{
		return listofUsers;
	}

}
