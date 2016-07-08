package org.dwaldron.gnosis.bible;

import java.util.ArrayList;

public class Chapter {
	int _id;
 	int _number;
 	Book _book;
 	ArrayList<Verse> _verses;

 	//Empty Chapter Constructor
 	public Chapter(){

 	}

 	//Constructor with id, number, book, and verses params
 	public Chapter(int id, int num, Book book, ArrayList<Verse> verses){
 		this._id = id;
 		this._number = num;
 		this._book = book;
 		this._verses = verses;
 	}

 	//Constructor without id param
 	public Chapter( int num, Book book, ArrayList<Verse> verses){
 		this._number = num;
 		this._book = book;
 		this._verses = verses;
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

 	public Book getBook(){
 		return this._book;
 	}

 	public void setBook(Book book){
 		this._book = book;
 	}
 	
 	public ArrayList<Verse> getVerses(){
 		return this._verses;
 	}

 	public void setVerses(ArrayList<Verse> verses){
 		this._verses.clear();
 		for (Verse v : verses){
 			this._verses.add(v);
 		}
 	}
}
