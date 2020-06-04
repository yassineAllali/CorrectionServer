package application;


public class InonceCreatore 
{
	private InonceType type;
	
	public InonceCreatore(InonceType type) 
	{
		this.type = type;
	}
	
	public IInonce creerInoncer(String inonceText)
	{
		switch (type) {
		case TEXT:
			return new InonceText(inonceText);
		/*case AUDIO:
			return new InonceAudio(inonceText);
		case IMAGE:
			return new InonceImage(inonceText);
		case VIDEO:
			return new InonceVideo(inonceText);*/
		default:
			return new InonceText(inonceText);
		}
	}
}
