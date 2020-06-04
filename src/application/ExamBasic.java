package application;

import java.io.Serializable;

public abstract class ExamBasic implements IPassable, Serializable
{
	protected int idExam, idFiliere;
	protected int note;
	protected String codeNationalEtudiant;
	protected int nombresQuestions;
	protected Question[] questions;
	
	// Using this constroctor when retriving existing exams from the data base
	public ExamBasic(int idExam, int idFiliere, String codeNationalEtudiant, int nombresQuestion, int note) 
	{
		super();
		this.idExam = idExam;
		this.idFiliere = idFiliere;
		this.codeNationalEtudiant = codeNationalEtudiant;
		this.nombresQuestions = nombresQuestion;
		this.questions = new Question[nombresQuestion];
		this.note = note;
	}
	
	// Using this constructor when creating new Exam
	public ExamBasic(int idExam,int idFiliere, String codeNationalEtudiant, int nombresQuestion) 
	{
		super();
		this.idExam = idExam;
		this.idFiliere = idFiliere;
		this.codeNationalEtudiant = codeNationalEtudiant;
		this.nombresQuestions = nombresQuestion;
		this.questions = new Question[nombresQuestion];
	}
	

	/******************************************************************/
	// ajouter une question
	// add question
	protected void ajouterQuestion(Question question, int index)
	{
		this.questions[index] = question;
	}
	

	/************************************************************/
	// setters and getters 
	
	public int getIdExam() {
		return idExam;
	}

	public void setIdExam(int idExam) {
		this.idExam = idExam;
	}
	
	public String getCodeNationalEtudiant() {
		return codeNationalEtudiant;
	}
	
	public int getNote()
	{
		return this.note;
	}

	public int getNombresQuestions() {
		return nombresQuestions;
	}

	public void setNombresQuestions(int nombresQuestions) {
		this.nombresQuestions = nombresQuestions;
	}
	
	public Question getQuestion(int index)
	{
		try {
			return questions[index];
		} catch (Exception e) {
			return null;
		}
	}
	/***********************************************************************/

	@Override
	public ICorrectable[] getReponses()
	{
		ICorrectable[] correctables = new ICorrectable[nombresQuestions];
		for(int i = 0 ; i < nombresQuestions ; i++)
		{
			correctables[i] = questions[i];
		}
		return correctables;
	}
	
	@Override
	public String toString() {
		String exam = "Exam[ Code National d'etudiant : " + codeNationalEtudiant + " ]\n";
		for (int i = 0 ; i < nombresQuestions ; i++)
		{
			exam += questions[i] + "\n";
		}
		return exam;
	}

}
