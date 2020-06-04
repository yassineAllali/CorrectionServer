package application;

import java.io.Serializable;

public class Choix implements Serializable
{
	private int id, idQuestion, correcte;
	private String text;
	
	
	public Choix(int id, int idQuestion, String text, int correcte) 
	{
		super();
		this.id = id;
		this.idQuestion = idQuestion;
		this.correcte = correcte;
		this.text = text;
	}
	
	/******* Getters ******************************************/
	public int getId() {
		return id;
	}


	public int getIdQuestion() {
		return idQuestion;
	}


	public int getCorrecte() {
		return correcte;
	}


	public String getText() 
	{
		return text;
	}
	
	/********************************************************************/
	
	@Override
	public boolean equals(Object obj) 
	{
		Choix autre = (Choix)obj;
		return this.text.equals(autre.text);
	}
	
}
