package application;

import java.io.Serializable;


public abstract class Question  implements ICorrectable, IAnswerable, Serializable
{
	protected int id, idFiliere;
	protected Reponse reponse;
	protected Object reponseCorrecte;
	protected IInonce inonce;
	protected InonceType type;
	

	public Question(int id, int idFiliere, String inonceText, InonceType type ) 
	{
		super();
		this.id = id;
		this.idFiliere = idFiliere;
		// accepting different types of inonce (text, audio, ....)
		this.type = type;
		InonceCreatore creatore = new InonceCreatore(type);
		this.inonce = creatore.creerInoncer(inonceText);
	}

/**************************************************************************/
	//setters and getters 

	public int getId() 
	{
		return id;
	}
	
	public int getIdFiliere() 
	{
		return idFiliere;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Object getInonce() {
		return this.inonce.getInonce();
	}
	
	protected void setReponseCorrecte(Object reponseCorrecte)
	{
		this.reponseCorrecte = reponseCorrecte;
	}
	public void setInonce(IInonce inonce) {
		this.inonce = inonce;
	}
	
	public InonceType getType() {
		return type;
	}


	public void setType(InonceType type) {
		this.type = type;
	}

/**************************************************************************/
	@Override
	public Reponse getReponse() {
		return this.reponse;
	}

	@Override
	public Object getReponseCorrecte() {
		return this.reponseCorrecte;
	}

	@Override
	public void setReponse(Reponse reponse) 
	{
		this.reponse = reponse;
	}

}
