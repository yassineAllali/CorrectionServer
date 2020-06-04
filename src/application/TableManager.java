package application;

import java.sql.Connection;
import java.sql.ResultSet;

/*
 * the TableManager extebds the TableManagerBasic to make some methods easier
 * by storing the frequently used columns and indexes 
 * to use it in the basics methods of TableManagerBasic that are more general
 */

public abstract class TableManager extends TableManagerBasic{

	protected String[] generalColumns;// usualy used columns
	protected String generalIndex;// usualy used index
	public TableManager(Connection connection) 
	{
		super(connection);	
	}
	
	/***** CRUD ******************************************/
	// Reading //
	
	// lire des colennes specifiques de toutes les lignes
	// reading specific columns of all records
	protected ResultSet readColumnsOfAll() 
	{
		return super.readColumnsOfAllBasic(generalColumns);
	}
	
	// lire une ligne specific on se basant sur un index specific
	// reading a specific record besed on a soecific index(column name)
	protected ResultSet readRecord(Object valeur)
	{
		return readRecordBasic(generalIndex, valeur);
	}
	
	// lire des colonnes specific d'une ligne specific on se basant sur un index specific
	// reading specific columns of a specific record besed on a soecific index(column name)
	protected ResultSet readColumnsOfRecord(Object valeur)
	{
		return readColumnsOfRecordBasic(generalIndex, valeur, generalColumns);
	}
	
	//// Creating ///////////
	
	//inserer des colonnes specifiques d'un nouveau record
	// inserting specifics columns of a new record 
	// make sure that the order of parameters is the same of values
	protected int createColumnsRecord( Object[] values)
	{
		return createColumnsRecordBasic(generalColumns, values);
	}
	
	//// Updating ///////////
	
	//modifier des colonnes specifiques d'une ligne
	// updating specifics columns of a record 
	// make sure that the order of parameters is the same of values
	protected int updateRecord( Object[] values, Object indexValue)
	{
		return updateRecordBasic(generalColumns, values, generalIndex, indexValue);
	}
	
	//// Deleting ///////////
	
	protected int deleteRecord(Object value)
	{
		return deleteRecordBasic(generalIndex, value);
	}
	
	////// Counting ////////////////
	protected int countRecords(Object value)
	{
		return countRecordsBasic(generalIndex, value);
	}
	
}
