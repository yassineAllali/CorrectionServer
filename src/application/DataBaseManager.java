package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseManager 
{
	private String user = "examUser";
	private String password = "exam2020";
	private String url = "jdbc:mysql://localhost:3308/exam_2.0";
	private Connection connection;
	
	public DataBaseManager() 
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			System.out.println("Probleme de Driver de la base de donnees");
		}
		
		try 
		{
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Probleme de connection avec base de donnees");
		}
	}
	
	public Connection getConnection()
	{
		return this.connection;
	}
	
	public void closeConnection()
	{
		try {
			connection.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println("Probleme de fermeture de connection!");
		}
	}
	
	
}
