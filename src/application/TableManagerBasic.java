package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;


// Table manager Basic  object can manipulate data of a specific table in a general way
/* 
 * ->Columns : a table with the columns to be selected or modified .... : [ "id", "intitule", ...]
 *
 */
public abstract class TableManagerBasic implements IResultSetHandler
{
	protected String table;
	protected Connection connection;
	
	public TableManagerBasic(Connection connection) 
	{
		this.connection = connection;
	}
	
	/******** USEFUL FUNCTIONS *************************************************/
	// return => "id, nom, ...."
	protected String getParameters(String[] columns)
	{
		String parameters = "";
		
		if(columns.length > 0)
		{
			for(int i = 0 ; i < columns.length - 1 ; i++)
			{
				parameters += columns[i] + ", ";
			}
			parameters += columns[columns.length - 1];
		}
		return parameters;
	}
	
	// return => "?, ?, ?,....."
	protected String getBinders(Object[] values)
	{
		String binders = "";
		
		if(values.length > 0)
		{
			for(int i = 0 ; i < values.length - 1 ; i++)
			{
				binders +=  "?, ";
			}
			binders += "? ";
		}
		return binders;
	}
	
	// binding every parameter in the query with its value
	protected void bindParameters(Object[] values, PreparedStatement preSt)
	{
		int legth = values.length;
		for(int i = 0 ; i < legth ; i++)
		{
			try {
				preSt.setObject( i + 1, values[i]);
			} catch (SQLException e) {
				System.out.println("Error in binding parameters");
				e.printStackTrace();
			}
		}
	}
	
	// getting columns equal parameters => "id = ?, name = ?, ....."
	protected String getColumnEqualParameter(String[] columns)
	{
		String couples = "";
		
		if(columns.length > 0)
		{
			for(int i = 0 ; i < columns.length - 1 ; i++)
			{
				couples += columns[i] + " = ?, ";
			}
			couples += columns[columns.length - 1] + " = ? ";
		}
		return couples;
	}
	
	/*** CRUD **********************************************************/
	
	//// reading ///////////
	
	// lire tous les lignes du tableau
	// reading all records
	protected ResultSet readAll()
	{
		String requete = "Select * from " + table;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(requete);
			ResultSet result = preparedStatement.executeQuery();
			return result;
		} catch (SQLException e) {
			System.out.println("Prepared Statement error");
			e.printStackTrace();
			return null;
		}
	}
	
	// lire des colennes specifiques de toutes les lignes
	// reading specific columns of all records
	protected ResultSet readColumnsOfAllBasic(String[] columns)
	{
		String param = getParameters(columns);
		String requete = "Select " + param + " FROM " + table;
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(requete);
			ResultSet result = preparedStatement.executeQuery();
			return result;
		} catch (SQLException e) {
			System.out.println("Prepared Statement error");
			e.printStackTrace();
			return null;
		}
	}
	
	// lire une ligne specific on se basant sur un index specific
	// reading a specific record besed on a soecific index(column name)
	protected ResultSet readRecordBasic(String index, Object valeur)
	{
		String requete = "SELECT * FROM " + table;
		requete += " WHERE " + index + " = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(requete);
			preparedStatement.setObject( 1 , valeur);
			ResultSet result = preparedStatement.executeQuery();
			return result;
		} catch (SQLException e) {
			System.out.println("Prepared Statement error");
			e.printStackTrace();
			return null;
		}
	}
	
	// lire des colonnes specific d'une ligne specific on se basant sur un index specific
	// reading specific columns of a specific record besed on a soecific index(column name)
	protected ResultSet readColumnsOfRecordBasic(String index, Object valeur, String[] columns )
	{
		String param = getParameters(columns);
		String requete = "Select " + param + " FROM " + table;
		requete += " WHERE " + index + " = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(requete);
			preparedStatement.setObject( 1 , valeur);
			ResultSet result = preparedStatement.executeQuery();
			return result;
		} catch (SQLException e) {
			System.out.println("Prepared Statement error");
			e.printStackTrace();
			return null;
		}
	}
	
	//// creating ///////////
	
	//creer un record
	// creating record
	protected int createRecord(Object[] values)
	{
		String requete = "INSERT INTO " + table;
		requete += " VALUES( " + getBinders(values) + ")";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);

			bindParameters(values, preparedStatement);
			int key = preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next())
            {
               key = rs.getInt(1);
            }
			return key;
		} catch (SQLException e) {
			System.out.println("Prepared Statement error");
			e.printStackTrace();
			return 0;
		}
		
	}
	
	//inserer des colonnes specifiques d'un nouveau record
	// inserting specifics columns of a new record 
	// make sure that the order of parameters is the same of values
	protected int createColumnsRecordBasic(String[] columns, Object[] values)
	{
		String requete = "INSERT INTO " + table + "(" + getParameters(columns) + ")";
		requete += " VALUES( " + getBinders(values) + ")";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);

			bindParameters(values, preparedStatement);
			// Getting the id of the created record
			int key = preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next())
            {
               key = rs.getInt(1);
            }
			return key;
		} catch (SQLException e) {
			System.out.println("Prepared Statement error");
			e.printStackTrace();
			return 0;
		}
		
	}
	
	//// Updating ///////////
	
	//modifier des colonnes specifiques d'une ligne
	// updating specifics columns of a record 
	// make sure that the order of parameters is the same of values
	protected int updateRecordBasic(String[] columns, Object[] values, String index, Object indexValue)
	{
		int length = values.length;
		String requete = "UPDATE " + table;
		requete += " SET " + getColumnEqualParameter(columns);
		requete += " WHERE " + index + " = ? LIMIT 1;";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(requete);
			bindParameters(values, preparedStatement);
			// finaly binding the index with it's value
			preparedStatement.setObject(length + 1 , indexValue);
			int flag = preparedStatement.executeUpdate();
			return flag;
		} catch (SQLException e) {
			System.out.println("Prepared Statement error");
			e.printStackTrace();
			return 0;
		}
	}
	
	//// Deleting ///////////
	
	protected int deleteRecordBasic(String index, Object value)
	{
		String requete = "DELETE FROM " + table;
		requete += " WHERE " + index + " = ? LIMIT 1;"; 
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(requete);
			preparedStatement.setObject( 1 , value);
			int flag = preparedStatement.executeUpdate();
			return flag;
		} catch (SQLException e) {
			System.out.println("Prepared Statement error");
			e.printStackTrace();
			return 0;
		}
	}
	
	/*************************************************************************/
	// counting the number of records based on a specific columns
	protected int countRecordsBasic(String index, Object valeur)
	{
		String requete = "SELECT COUNT(" + index + ") FROM " + table;
		requete += " WHERE " + index + " = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(requete);
			preparedStatement.setObject(1 , valeur);
			ResultSet result = preparedStatement.executeQuery();
			if(result.next())
				return result.getInt(1);
			else
				return 0;
		} catch (SQLException e) {
			System.out.println("Prepared Statement error");
			e.printStackTrace();
			return 0;
		}
		
	}
	
	
	
	/***********************************************************************************/
	protected abstract Object newObject(ResultSet result);
	
	@Override
	public ArrayList<Object> toObjects(ResultSet result) 
	{
		ArrayList<Object> objects = new ArrayList<Object>();
		try 
		{
			while(result.next())
			{
				// creating an object depending on the table
				Object object = newObject(result);
				objects.add(object);
			}
			return objects;
		} catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public Object toObject(ResultSet result) 
	{
		try 
		{
			Object object = null;
			if(result.next())
			{
				// creating an object depending on the table
				object = newObject(result);
			}
			return object;
		} catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Object[] toObjectsTable(ResultSet result) 
	{
		ArrayList<Object> objects = toObjects(result);
		int length = objects.size();
		if(length > 0)
		{
			Object[] table = new Object[length];
			for(int i = 0 ; i < length ; i++)
			{
				table[i] = objects.get(i);
			}
			return table;
		}
		return null;
	}
	
}

