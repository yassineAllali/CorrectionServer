package application;

import java.io.Serializable;

// l'inonce de la question de n'importe quelles formats (text, audio , .....)
// IInonce is the statememnt of a question (text, audio, ...)
public interface IInonce extends Serializable 
{
	public Object getInonce();
}
