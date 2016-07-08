package org.dwaldron.gnosis.bible;

import java.util.ArrayList;

public class Book {
	int _id;
 	String _title;
 	String _version;
 	ArrayList<Chapter> _chapters;
 	
 	public Book(){
 		
 	}
 	
 	public Book(int id, String title, String version, ArrayList<Chapter> chapters){
 		this._id = id;
 		this._title = title;
 		this._version = version;
 		this._chapters = chapters;
 	}
 	
 	public Book(String title, String version, ArrayList<Chapter> chapters){
 		this._title = title;
 		this._version = version;
 		this._chapters = chapters;
 	}
 	
 	public int getID(){
 		return this._id;
 	}
 	
 	public void setID(int id){
 		this._id = id;
 	}
 	
 	public String getTitle(){
 		return this._title;
 	}
 	
 	public void setTitle(String title){
 		this._title = title;
 	}
 	
 	public String getVersion(){
 		return this._version;
 	}
 	
 	public void setVersion(String version){
 		this._version = version;
 	}
 	
 	public ArrayList<Chapter> getChapters(){
 		return this._chapters;
 	}
 	
 	public void setChapters(ArrayList<Chapter> chapters){
 		this._chapters = chapters;
 	}
}
