package View.Utility;


import java.io.*;

import View.Helper.Admin;
import View.Helper.User;

public class readwriteObjects<T> implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	public static final String storeDir = "server";
	
	public static void writeUser(User user)throws IOException 
	{
		ObjectOutputStream oos = new ObjectOutputStream(
		new FileOutputStream(storeDir + File.separator + user.getUsername()));
		oos.writeObject(user);
		oos.close();
	}
	public static User readUser(String username)throws IOException, ClassNotFoundException 
	{
		ObjectInputStream ois;
		User user;
		try {
			ois = new ObjectInputStream(
			new FileInputStream(storeDir + File.separator + username));
			user = (User)ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
		return user;
		
	}
	public static void writeAdmin(Admin admin)throws IOException 
	{
		ObjectOutputStream oos = new ObjectOutputStream(
		new FileOutputStream(storeDir + File.separator + admin.getUsername()));
		oos.writeObject(admin);
		oos.close();
	}
	public static Admin readAdmin(String username) throws ClassNotFoundException 
	{
		ObjectInputStream ois;
		Admin admin;
		try {
			ois = new ObjectInputStream(
			new FileInputStream(storeDir + File.separator + username));
				admin = (Admin)ois.readObject();
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
			}
		
		return admin;
	}

}