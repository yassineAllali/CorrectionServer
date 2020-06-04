package application;

// IPassable est l'ablite a avoir plusieur ICorrecatable(questions) pour tester un IRepondeur
// IPassable has the ability to have many ICorrectable(Questions) to test an IRepondeur
public interface IPassable 
{
	// return les reponses pour les couriger
	// return responds to be corrected
	public ICorrectable[] getReponses();
}
