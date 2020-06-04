package application;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JLabel;

import Interface.ServeurInteface;


public class CorrectionServer extends Thread 
{
	private ServeurInteface frame;
	private ServerSocket serverSocket;
	private ArrayList<ClientHandler> clients;
	private ExecutorService pool;
	final int PORT = 1306;
	
	public CorrectionServer(ServeurInteface frame) 
	{
		this.frame = frame;
		try {
			serverSocket = new ServerSocket(PORT);
			clients = new ArrayList<ClientHandler>();
			//10 Clients can connect to the server at the same time
			pool = Executors.newFixedThreadPool(10);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		start();
	}
	
	@Override
	public void run() 
	{
		super.run();
		while(!interrupted())
		{
			try 
			{
				System.out.println("Waiting for new Client!");
				ClientHandler newClient = new ClientHandler(serverSocket.accept(), this);
				System.out.println("**************************************************");
				System.out.println("Connection Established with a new Client!");
				//Adding client to clients array list
				clients.add(newClient);
				//Start the task of the client
				pool.execute(newClient);
				changeConnectioInfos();
			} catch (IOException e) 
			{
				Thread.currentThread().interrupt();
				e.printStackTrace();
			}
		}
		pool.shutdownNow();
		shutDown();
	}

	
	// shutting down the server properly 
	synchronized public void shutDown()
	{
		System.out.println("****************************************************");
		try {
			serverSocket.close();
			System.out.println("Connection Closed");
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	/*****************************************************************************/
	//get the client currently connected
	public String getConnectes()
	{
		String connectes = "<html>";
		int length = clients.size();
		for (int i = 0 ; i < length ; i++)
		{
			connectes += ("<br>" + clients.get(i).getEtudiant());
		}
		connectes += "</html>";
		return connectes;
	}
	
	//removing a client from the clients list
	public void removeClient(ClientHandler client)
	{
		clients.remove(client);
	}
	// getting the number of clients currently connected
	public int getNumberClients()
	{
		return clients.size();
	}
	
	////////////////////////////////////////////////////////////
	//Frame functions
	private void setLabelText(String text, JLabel label)
	{
		label.setText(text);
	}
	
	public void setNombreLabel(JLabel label)
	{
		String text = "Etudiants Connectés: " + Integer.toString(getNumberClients());
		setLabelText(text, label);
		frame.repaint();
	}
	public void setEtudiantsConnectes(JLabel label)
	{
		setLabelText(getConnectes(), label);
		frame.repaint();
	}
	
	public void changeConnectioInfos()
	{
		setNombreLabel(frame.getNombreLabel());
		setEtudiantsConnectes(frame.getEtudiantsLabel());
	}
	
	/*public static void main(String[] args) 
	{
		CorrectionServer server = new CorrectionServer();
	}*/
}
