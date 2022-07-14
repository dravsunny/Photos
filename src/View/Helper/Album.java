package View.Helper;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import javafx.scene.control.TreeView;
/**
 * 
 * @author Jaideep Duggempudi
 * @author Suneet Dravida
 * 
 *
 */

public class Album implements Serializable{
	/**
	 * stores the albumname
	 */
	public String albumName;
	/**
	 * stores the albums photos
	 */
	public HashMap<String, Photo>listofPhotos;
	/**
	 * stores the dates of each photo in the album
	 */
	public HashSet<Date> listofDates;
	/**
	 * stores the number of photos
	 */
	public int numberofPhotos;
	/**
	 * stores the earliest dae in the album of a photo
	 */
	public Date earliest;
	/**
	 * stores he lates date of a album
	 * 
	 */
	public Date latest;
	
	/**
	 * empty constructor
	 */
	public Album()
	{
		//for temporary Album variables that need to be initialized
	}
	/**
	 * constructor for the album class
	 * 
	 * @param albumname name of the album
	 */
	public Album(String albumname)
	{
		this.albumName = albumname;
		listofPhotos = new HashMap<String, Photo>();
		listofDates = new HashSet<Date>();
		numberofPhotos = 0;
		
	}
	/**
	 * 
	 * @return album name
	 */
	public String getalbumName()
	{
		return albumName;
	}
	/**
	 * 
	 * @param newAlbumname the new labum name that you want to rechange to 
	 */
	public void changealbumName(String newAlbumname)
	{
		albumName = newAlbumname;
	}
	/**
	 * 
	 * @return numbre of photos
	 */
	public int getnumberofPhotos()
	{
		return numberofPhotos;
	}
	/**
	 * this allows you to add a photo to a album
	 * 
	 * @param photo add a phto object to the album
	 */
	public void addPhoto(Photo photo)
	{
		listofPhotos.put(photo.getCaption(), photo);
		listofDates.add(photo.getDate());
		numberofPhotos++;
			
		if(!listofDates.isEmpty())
		{
			earliest = Collections.min(listofDates);
			latest = Collections.max(listofDates);
		}
			
		
		
	}
	/**
	 * allows you to dlete a photo by getting the photos name
	 * 
	 * @param photo name to be dleted
	 */
	public void deletePhoto(String photo)
	{
		listofDates.remove(listofPhotos.get(photo).getDate());
		listofPhotos.remove(photo);
		numberofPhotos--;
		if(!listofDates.isEmpty())
		{
			earliest = Collections.min(listofDates);
			latest = Collections.max(listofDates);
		}
		
	}
	/**
	 * Used for renaming a photo
	 * 
	 * @param oldPhoto the old photo name
	 * @param newPhoto  new photo name
	 */
	public void editPhotoCaption(String oldPhoto, String newPhoto)
	{
		Photo temp = listofPhotos.get(oldPhoto);
		temp.changeCaption(newPhoto);
		listofPhotos.remove(oldPhoto);
		listofPhotos.put(newPhoto, temp);
		
	}
	/**
	 * you can used this method to return the actual ohotoobject by its the caption 
	 * 
	 * @param photoCaption the name of the photo
	 * @return the photo object of the photocaption name
	 */
	public Photo getPhoto(String photoCaption)
	{
		return listofPhotos.get(photoCaption);
	
	}
	/**
	 * return s alist of photos int he labum
	 * @return list of photos in th elabum
	 */
	public HashMap<String, Photo> getlistofPhotos()
	{
		return listofPhotos;
	}
	/**
	 * 
	 * @return the range of date from the earliest tot he lates
	 */
	public String getRangeofDates()
	{
		String i = "";
		if(!listofDates.isEmpty())
		{
			String pattern = "MM-dd-yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String dateEarly = simpleDateFormat.format(earliest);
			String dateLate = simpleDateFormat.format(latest);
			i = i + dateEarly + " - " + dateLate;
			
		}
		return i;
	}
	
	

}
