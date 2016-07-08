package org.dwaldron.gnosis.bible;

public class Verse {
	int _id;
	int _number;
 	String _text;
 	Chapter _chapter;

 	//Empty Verse Constructor
 	public Verse(){

 	}

 	//Constructor with id, text, and chapter params
 	public Verse(int id, int number, String text, Chapter chapter){
 		this._id = id;
 		this._number = number;
 		this._text = text;
 		this._chapter = chapter;
 	}

 	//Constructor without id param
 	public Verse( int number, String text, Chapter chapter){
 		this._number = number;
 		this._text = text;
 		this._chapter = chapter;
 	}

 	public int getID(){
 		return this._id;
 	}

 	public void setID(int id){
 		this._id = id;
 	}
 	
 	public int getNumber(){
 		return this._number;
 	}
 	
 	public void setNumber(int num){
 		this._number = num;
 	}

 	public String getText(){
 		return this._text;
 	}

 	public void setText(String text){
 		this._text = text;
 	}

 	public Chapter getChapter(){
 		return this._chapter;
 	}

 	public void setChapter(Chapter chapt){
 		this._chapter = chapt;
 	}
}
