package org.dwaldron.gnosis.games;

public class Question {
	int _id;
 	String _body;
 	String _answer;
 	String _option1;
 	String _option2;
 	String _option3;

 	//Empty Question Constructor
 	public Question(){

 	}

 	//Constructor with id, body, answer, option1, option2, option3 as params
 	public Question(int id, String body, String answer, String option1, String option2, String option3){
 		this._id = id;
 		this._body = body;
 		this._answer = answer;
 		this._option1 = option1;
 		this._option2 = option2;
 		this._option3 = option3;
 	}

 	//Constructor without id param
 	public Question( String body, String answer, String option1, String option2, String option3){
 		this._body = body;
 		this._answer = answer;
 		this._option1 = option1;
 		this._option2 = option2;
 		this._option3 = option3;
 	}

 	public int getID(){
 		return this._id;
 	}

 	public void setID(int id){
 		this._id = id;
 	}

 	public String getBody(){
 		return this._body;
 	}

 	public void setBody(String body){
 		this._body = body;
 	}

 	public String getAnswer(){
 		return this._answer;
 	}

 	public void setAnswer(String answer){
 		this._answer = answer;
 	}

 	public String getOption1(){
 		return this._option1;
 	}

 	public void setOption1(String opt){
 		this._option1 = opt;
 	}

 	public String getOption2(){
 		return this._option2;
 	}

 	public void setOption2(String opt){
 		this._option2 = opt;
 	}

 	public String getOption3(){
 		return this._option3;
 	}

 	public void setOption3(String opt){
 		this._option3 = opt;
 	}
 	
 	@Override
	public String toString() {
 		return String.format("%s: 1. %s, 2. %s, 3. %s, 4. %s",this._body, this._answer, this._option1, this._option2, this._option3);
 	}
}
