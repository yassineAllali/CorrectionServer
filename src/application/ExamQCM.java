package application;


public class ExamQCM extends ExamBasic
{

	// whene retriving data from database
	public ExamQCM(int idExam, int idFiliere, String codeNationalEtudiant, int note) 
	{
		super(idExam, idFiliere, codeNationalEtudiant, 20, note);
		setQuestions();
		setResponses();
	}	
	
	public ExamQCM(int idExam, int idFiliere, String codeNational)
	{
		super(idExam, idFiliere, codeNational, 20);
		setQuestions();
	}
	
	public void setResponses()
	{
		DataBaseManager db = new DataBaseManager();
		ReponsesManager table = new ReponsesManager(db.getConnection());
		Reponse[] reponses = table.chercherReponses(idExam);
		db.closeConnection();
		if(reponses != null)
		{
			for(int i = 0 ; i < reponses.length ; i++)
			{
				if(questions[i] != null)
				{
					if(questions[i].getId() == reponses[i].getIdQuestion())
						questions[i].setReponse(reponses[i]);
				}
				
			}
		}
	}
	
	protected void setQuestions()
	{
		DataBaseManager db = new DataBaseManager();
		QuestionsManager manager = new QuestionsManager(db.getConnection());
		Question[] questions = manager.chercherQuestionsFiliere(idFiliere);
		db.closeConnection();
		if( questions.length < nombresQuestions)
		{
			this.nombresQuestions = questions.length;
		}
		for (int i = 0 ; i < nombresQuestions ; i++)
		{
			ajouterQuestion(questions[i], i);
		}
	}
	
	@Override
	public String toString() 
	{
		return "Exam[ Code National : " + codeNationalEtudiant + ", Note : " + note + " ]";
	}

}
