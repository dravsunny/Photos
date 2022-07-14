package View.Helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class User implements Serializable{

	public String username;
	public String password;
	
	public HashMap<String, Album> listofAlbums;
	
	public ArrayList<String> listofAttributes;
	
	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
		
		listofAlbums = new HashMap<String, Album>();
		
		listofAttributes = new ArrayList<String>();
		
	}
	
	public String getUsername()
	{
		return username;
	}
	public String getPassword()
	{
		return password;
	}
	
	public Album getAlbum(String albumName)
	{
		return listofAlbums.get(albumName);
	}
	
	public void addAlbum(Album album)
	{
		listofAlbums.put(album.getalbumName(), album);
	}
	public void deleteAlbum(String album)
	{
		listofAlbums.remove(album);
	}
	
	public void editAlbumName(String oldAlbum, String newAlbum)
	{
		Album temp = listofAlbums.get(oldAlbum);
		temp.changealbumName(newAlbum);
		listofAlbums.remove(oldAlbum);
		listofAlbums.put(newAlbum, temp);
		
	}
	
	public HashMap<String, Album> getlistofAlbums()
	{
		return listofAlbums;
	}
	
	public ArrayList<String> getlistofAttributes()
	{
		return listofAttributes;
	}
	public void addAttribute(String attribute)
	{
		listofAttributes.add(attribute);
	}
	
}
