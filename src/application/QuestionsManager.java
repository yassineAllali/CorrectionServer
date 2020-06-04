package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;



public class QuestionsManager extends TableManager 
{

	public QuestionsManager(Connection connection)
	{
		super(connection);
		this.table = "questions";
		this.generalColumns = new String[3];
		this.generalColumns[0] = "idFiliere";
		this.generalColumns[1] = "inonce";
		this.generalColumns[2] = "inonceType";
		this.generalIndex = "id";
	}
	/**********************************************************************/
	
	// reading //
	public Question[] chercherQuestionsFiliere(int idFiliere)
	{
		ResultSet r = readRecordBasic("idFiliere" , idFiliere);
		Object[] questionsObject = toObjectsTable(r);
		// casting the object[] table to Question[]
		return Arrays.copyOf(questionsObject, questionsObject.length, Question[].class);
	}
	
	// Creating //
	
	public int creerQuestion(int idFiliere, String inonce, String type)
	{
		Object[] values = { idFiliere, inonce, type };
		return createColumnsRecord(values);
	}
	
	
	/***************************************************************/
	@Override
	protected Object newObject(ResultSet result) 
	{
		try 
		{
			InonceTypeDeterminator type = new InonceTypeDeterminator(result.getString(4));
			QuestionQCM question = new QuestionQCM(result.getInt(1), result.getInt(2),
					result.getString(3), type.determinerType());
			return question;
		} catch (SQLException e) 
		{
			System.out.println("Error in creating object from ResultSet");
			e.printStackTrace();
			return null;
		}
	}

	

}
