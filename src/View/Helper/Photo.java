package View.Helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
/**
 * 
 * @author Jaideep Duggempudi
 * @author Suneet Dravida
 *
 */
public class Photo implements Serializable{
	/**
	 * stores the filepath of th ephtoo
	 */
	public String filepath;
	/**
	 * stores teh caption of the photo
	 */
	public String caption;
	/**
	 * stores teh date of the photo
	 * 
	 */
	public Date date;
	//peopl3 vlaue: 
	/**
	 * store s a list of tags belignig to the photo
	 */
	public HashMap<String, HashSet<String>> myTags;
	/**
	 * constructor to creates the phtoo object 
	 * 
	 * @param filepath
	 * @param caption
	 * @param date
	 */
	public Photo(String filepath, String caption, Date date)
	{
		this.filepath = filepath;
		this.caption = caption;
		this.date = date;
		
		myTags = new HashMap<String, HashSet<String>>();
	}
	/**
	 * 
	 * @return the filepath of the phtoo
	 */
	public String getFilepath()
	{
		return filepath;
	}
	/**
	 * 
	 * @return the captions of photo
	 */
	public String getCaption()
	{
		return caption;
	}
	/**
	 * 
	 * @param newCaption the new caption to be changed to
	 */
	public void changeCaption(String newCaption)
	{
		caption = newCaption;
	}
	/**
	 * 
	 * @return the date in a stiring format
	 */
	public String getstringDate()
	{
		return date + "";
	}
	/**
	 * returns the date object
	 * @return date
	 */
	public Date getDate()
	{
		return date;
	}
	
	/**
	 * used to add new tags to a photos
	 * @param tag the tag type
	 * @param value the value of the tage
	 */
	public void addnewTag(String tag, String value)
	{
		if(myTags.get(tag) == null)
		{
			HashSet<String> temp = new HashSet<String>();
			temp.add(value);
			
			myTags.put(tag, temp);
		}
		else
		{
			myTags.get(tag).add(value);
		}
	}
	/**
	 * used to remove a tag
	 * @param tag tag type
	 * @param value to be deleted
	 */
	public void removeTag(String tag, String value)
	{
		myTags.get(tag).remove(value);
		if(myTags.get(tag).isEmpty())
		{
			myTags.remove(tag);
		}
	}
	/**
	 * serached if you have the tag
	 * @param tag search aprmater
	 * @param value paramete
	 * @return 
	 */
	public boolean doIhaveTag(String tag, String value)
	{
		if(myTags.containsKey(tag) && myTags.get(tag).contains(value))
		{
			return true;
		}
	
		return false;
		
	}
	public HashMap<String, HashSet<String>> getlistofTags()
	{
		return myTags;
	}
	public HashSet<String> gettagValues(String tagtype)
	{
		return myTags.get(tagtype);
	}
	public String stringofTags()
	{
		String string = "";
		for(String i: myTags.keySet())
		{
			string = string + i + " : " + myTags.get(i).toString() + ", ";
		}
		
		return string;
	}
	
	
	
}
