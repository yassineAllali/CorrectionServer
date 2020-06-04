package application;

public class InonceTypeDeterminator 
{
	private String type;
	public InonceTypeDeterminator(String type) 
	{
		this.type = type;
	}
	
	public InonceType determinerType()
	{
		switch (type) {
		case "text":
			return InonceType.TEXT;
		case "audio":
			return InonceType.AUDIO;
		case "image":
			return InonceType.IMAGE;
		case "video":
			return InonceType.VIDEO;
		default:
			return InonceType.TEXT;
		}
	}
}
