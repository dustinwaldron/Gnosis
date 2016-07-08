package org.dwaldron.gnosis.games;

public class User {
	int _id;
 	String _username;
 	int _score;

 	public User(){

 	}

 	public User(int id, String username, int score){
 		this._id = id;
 		this._username = username;
 		this._score = score;
 		
 	}

 	//Constructor without id param
 	public User(String username, int score){
 		this._username = username;
 		this._score = score;
 		
 	}

 	public int getID(){
 		return this._id;
 	}

 	public void setID(int id){
 		this._id = id;
 	}

 	public String getUsername(){
 		return this._username;
 	}

 	public void setUsername(String usr){
 		this._username = usr;
 	}

 	public int getScore(){
 		return this._score;
 	}

 	public void setScore(int score){
 		this._score = score;
 	}
}
