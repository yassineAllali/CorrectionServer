package application;


public abstract class Correcteur 
{
	protected IPassable exam;

	public Correcteur(IPassable exam) {
		super();
		this.exam = exam;
	}
	
	protected abstract boolean courigerQuestion(ICorrectable reponse);
	
	public int courigerExam()
	{
		int n = 0;
		ICorrectable[] questions = exam.getReponses();
		for(int i = 0; i < questions.length ; i++)
		{
			if(courigerQuestion(questions[i]))
				n++;
		}
		return n;
	}
}
