package application;

import java.io.Serializable;

public class Reponse implements Serializable 
{
	protected int idExam, idQuestion;
	protected Object reponse;
	
	/*********************************************************************/
	// Setters and Getters
	public Reponse(int idExam, int idQuestion, Object reponse) 
	{
		super();
		this.idExam = idExam;
		this.idQuestion = idQuestion;
		this.reponse = reponse;
	}

	public int getIdExam() {
		return idExam;
	}

	public int getIdQuestion() {
		return idQuestion;
	}

	public Object getReponse() {
		return reponse;
	}
	
	public void setReponse(Object reponse)
	{
		this.reponse = reponse;
	}
	
	
	
	
	
}
