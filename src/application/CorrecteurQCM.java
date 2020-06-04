package application;


public class CorrecteurQCM extends Correcteur 
{

	public CorrecteurQCM(IPassable exam) 
	{
		super(exam);
	}

	@Override
	public boolean courigerQuestion(ICorrectable reponse)
	{
		try 
		{
			boolean correction = (int)reponse.getReponse().getReponse() == (int)reponse.getReponseCorrecte();
			return correction;
		} catch (Exception e) 
		{
			return false;
		}

	}
	
}
