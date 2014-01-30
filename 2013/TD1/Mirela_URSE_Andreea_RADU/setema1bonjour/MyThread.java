package setema1bonjour;

class MyThread extends Thread // on definit une classe qui extends la classe "thread"
{
    String[] args = new String[5]; // le parametre args qui va contenir la liste de noms
    public MyThread(String[] args) // le constructeur de la classe qui a comme parametre un vecteur de type String[]
    {
       this.args = args;
    }
	@Override
	public void run ()   // la methode herite de la methode thread
	{
            for (int i=0; i<args.length; i++) // on parcoure la liste
            {
                System.out.println("Bonjour, " + args[i] + "!"); // on affiche pour chaque element ce texte
            }              
	}
}