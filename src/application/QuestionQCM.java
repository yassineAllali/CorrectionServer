package application;

import java.util.Arrays;

public class QuestionQCM extends Question
{
	private Choix[] choix;
	
	
	public QuestionQCM(int id, int idFiliere,String inonceText, InonceType type) 
	{
		super(id, idFiliere, inonceText,  type);
		setChoix();
		int correcte = determinerReponseCorrecte();
		setReponseCorrecte(correcte);
	}

	public Choix[] getChoix() {
		return choix;
	}
	
	public Choix getSeulChoix(int index)
	{
		try {
			return choix[index];
		} catch (Exception e) {
			return null;
		}
	}
	
	public int getNombreChoix()
	{
		return this.choix.length;
	}
	
	public int getReponseCorrecteInt()
	{
		return (int)this.reponseCorrecte;
	}
	private void setChoix() 
	{
		DataBaseManager manager = new DataBaseManager();
		ChoixManager table = new ChoixManager(manager.getConnection());
		Object[] choixObject = table.determinerChoixQcm(id);
		// casting the object[] table to choix[] table
		this.choix = Arrays.copyOf(choixObject, choixObject.length, Choix[].class);
		manager.closeConnection();
	}
	
	private int determinerReponseCorrecte()
	{
		for(int i = 0; i < this.choix.length ; i++)
		{
			if(this.choix[i].getCorrecte() == 1)
			{
				return choix[i].getId();
			}
		}
		return 0;
	}
	

	/*************************************************************************************/
	private String afficherChoix()
	{
		String choixString = "";
		for(int i = 0 ; i < choix.length ; i++)
		{
			choixString += (i + 1) + ")" + choix[i].getText() + "\n"; 
		}
		return choixString;
	}
	
	@Override
	public String toString() 
	{
		String question = (String) getInonce() + "\n";
		question += afficherChoix();
		return question;
	}

}
