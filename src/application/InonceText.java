package application;

public class InonceText implements IInonce
{
	private String inonce;
	
	public InonceText(String inonce) 
	{
		this.inonce = inonce;
	}
	/*****************************************/

	@Override
	public Object getInonce() {
		
		return this.inonce;
	}

	

	
}
