package application;

import java.sql.ResultSet;
import java.util.ArrayList;

// IResultSetHandler a l'abilite a manipuler un ResultSet
// IResultSetHandler has the ability to manipuliate ResultSet
public interface IResultSetHandler 
{
	public ArrayList<Object> toObjects(ResultSet result);
	public Object[] toObjectsTable(ResultSet result);
	public Object toObject(ResultSet result);
}
