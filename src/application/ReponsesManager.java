package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;




public class ReponsesManager extends TableManager
{

	public ReponsesManager(Connection connection) 
	{
		super(connection);
		this.table = "reponses";
		this.generalColumns = new String[3];
		this.generalColumns[0] = "idExam";
		this.generalColumns[1] = "idQuestion";
		this.generalColumns[2] = "reponse";
		this.generalIndex = "idExam";
	}
	/**************************************************************************/
	
	// Reading //
	
	public Reponse[] chercherReponses(int idExam)
	{
		ResultSet r = readColumnsOfRecord(idExam);
		Object[] reponses = toObjectsTable(r);
		// casting the object[] table to Question[]
		if(reponses != null)
			return Arrays.copyOf(reponses, reponses.length, Reponse[].class);
		return null;
	}
	
	// Creating //
	public int creerReponse(int idExam, int idQuestion, int reponse)
	{
		Object[] values = {idExam , idQuestion, reponse}; 
		int id = createColumnsRecord(values);
		return id;
	}
	
	/***************************************************************/
	@Override
	protected Object newObject(ResultSet result) 
	{
		try 
		{
			Reponse reponse = new Reponse(result.getInt(1), result.getInt(2), result.getInt(3));
			return reponse;
		} catch (SQLException e) 
		{
			System.out.println("Error in creating object from ResultSet");
			e.printStackTrace();
			return null;
		}
	}

	
}
