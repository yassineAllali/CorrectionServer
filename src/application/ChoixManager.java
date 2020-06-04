package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ChoixManager extends TableManager
{

	public ChoixManager(Connection connection) 
	{
		super(connection);
		this.table = "choix";
		this.generalColumns = new String[3];
		this.generalColumns[0] = "idQuestion";
		this.generalColumns[1] = "text";
		this.generalColumns[2] = "correcte";
		this.generalIndex = "id";
	}
	/************************************************************************/
	///  reading //
	public Object[] determinerChoixQcm(int idQuestion)
	{
		ResultSet result = readRecordBasic("idQuestion" , idQuestion );
		return toObjectsTable(result);
	}
	
	// Creating //
	
	public int creerChoix(int idQuestion, String text, int correcte)
	{
		Object[] values = { idQuestion, text, correcte };
		return createColumnsRecord(values);
	}
	
	
	/*****************************************************************/
	@Override
	protected Object newObject(ResultSet result) 
	{
		try {
			Choix choix = new Choix(result.getInt(1),
					result.getInt(2), result.getString(3), result.getInt(4));
			return choix;
		} catch (SQLException e) 
		{
			System.out.println("Error in creating object from ResultSet");
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
}
