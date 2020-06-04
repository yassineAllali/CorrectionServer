package application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable
{
	private volatile boolean running = true;
	private Socket client;
	private ObjectOutputStream dataOut;
	private ObjectInputStream dataIn;
	private ExamQCM exam;
	private String etudiant;
	private CorrectionServer serveur;
	
	public ClientHandler(Socket client, CorrectionServer serveur ) 
	{
		this.client = client;
		this.serveur = serveur;
		//Creating streams of communication
		try {
			dataOut = new ObjectOutputStream(client.getOutputStream());
			dataIn = new ObjectInputStream(client.getInputStream());
			etudiant = dataIn.readUTF();
			serveur.changeConnectioInfos();
		} catch (IOException e) {
			System.out.println("Error in creating Communication Streams in Client Handler");
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() 
	{	
		while(running)
		{
			try 
			{
				Object temp = dataIn.readObject();
				if( temp instanceof ExamQCM)
				{
					exam = (ExamQCM)temp;
					couriger();
					terminer();
				}
				else
					System.out.println("not an exam!");
				
			} catch (ClassNotFoundException e) 
			{
				terminer();
				e.printStackTrace();
			} catch (IOException e) 
			{
				terminer();
				e.printStackTrace();
			}
		}
		shutDown();
	}
	
	//Correcting exam
	
	synchronized private void couriger()
	{
		if(exam instanceof ExamQCM)
		{
			CorrecteurQCM correcteur = new CorrecteurQCM(exam);
			int note = correcteur.courigerExam();
			DataBaseManager db = new DataBaseManager();
			ExamsManager manager = new ExamsManager(db.getConnection());
			manager.modifierExam(exam.getIdExam(), note);
			db.closeConnection();
		}
	}
	
	public String getEtudiant()
	{
		return this.etudiant;
	}
	
	// terminate the run method
	synchronized public void terminer()
	{
		running = false;
	}
	
	// Shutting diwn the client properly
	synchronized public void shutDown()
	{
		// Closing Streams
		try {
			dataOut.close();
			dataIn.close();
			//Closing connection
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		serveur.removeClient(this);
		System.out.println("Connection Released!");
		serveur.changeConnectioInfos();
	}

}
