package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;



public class ExamsManager extends TableManager
{
	public ExamsManager(Connection connection) 
	{
		super(connection);
		this.table = "exams";
		this.generalColumns = new String[1];
		this.generalColumns[0] = "codeNatinalEtudiant";
		this.generalIndex = "id";
	}
	/*************************************************************************************/
	
	// Reading //
	@Override
	protected ResultSet readRecord(Object idFiliere) {
		String requete = "SELECT exams.id, etudiants.IdFiliere, exams.codeNatinalEtudiant, exams.note ";
		requete += "FROM etudiants "; 
		requete += "INNER JOIN exams ";
		requete += "on etudiants.CodeNational = exams.codeNatinalEtudiant ";
		requete += "where etudiants.IdFiliere = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(requete);
			preparedStatement.setObject( 1 , idFiliere);
			ResultSet result = preparedStatement.executeQuery();
			return result;
		} catch (SQLException e) {
			System.out.println("Prepared Statement error");
			e.printStackTrace();
			return null;
		}
	}
	
	public ExamQCM[] chercherExamsFiliere(int idFiliere)
	{
		ResultSet r = readRecord(idFiliere);
		Object[] objects = toObjectsTable(r);
		return Arrays.copyOf(objects, objects.length, ExamQCM[].class);
		
	}
	// Creating //
	public int creerExam(String codeEtudiant)
	{
		Object[] values = new Object[1];
		values[0] = codeEtudiant; 
		return createColumnsRecord(values);
	}
	// Updating //
	public int modifierExam(int id, int note)
	{
		String[] columns = { "note" };
		Object[] values = {note };
		return updateRecordBasic(columns , values, "id", id);
	}
	/************************************************************************************************/
	// getting Etudiants and their exams 
	public String[][] chercherExamsEtudiants(int idFiliere)
	{
		String requete = "SELECT  exams.codeNatinalEtudiant, etudiants.nom, etudiants.prenom, exams.note ";
		requete += "FROM etudiants "; 
		requete += "INNER JOIN exams ";
		requete += "on etudiants.CodeNational = exams.codeNatinalEtudiant ";
		requete += "where etudiants.IdFiliere = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(requete);
			preparedStatement.setObject( 1 , idFiliere);
			ResultSet r = preparedStatement.executeQuery();
			ArrayList<String[]> strings = new ArrayList<String[]>();
			while(r.next())
			{
				String[] string = { r.getString(1), r.getString(2), r.getString(3), Integer.toString(r.getInt(4))};
				strings.add(string);
			}
			return toTable(strings);
		} catch (SQLException e) {
			System.out.println("Prepared Statement error");
			e.printStackTrace();
			return null;
		}
	}
	// transfom an ArrayList to table
	private String[][] toTable(ArrayList<String[]> strings)
	{
		String[][] table = new String[strings.size()][4];
		for(int i = 0; i < table.length ;  i++)
		{
			table[i] = strings.get(i);
		}
		return table;
	}
	/*******************************************************************************************************/

	@Override
	protected Object newObject(ResultSet result) 
	{
		try 
		{
			ExamQCM exam = new ExamQCM(result.getInt(1), result.getInt(2), result.getString(3), result.getInt(4));
			exam.setResponses();
			return exam;
		} catch (SQLException e) 
		{
			System.out.println("Error in creating object from ResultSet");
			e.printStackTrace();
			return null;
		}
	}

}
