public class Hello2{

   
    public static void main(String[] args) {
	System.out.print("Bonjour ");
	for(int i=0;i<=3;i++)
{
        System.out.print(args[i]+",");
}
	System.out.print("!");
    }

}

/*
Ce programme a comme but l'affichage du meme message pour plusieurs arguments dans le meme temps.
On entre dans la Ligne de commande et on fait les suivantes:
-on se deplace dans le dossier contenant le programme: cd desktop\SE-devoir1-Gheorghe Maria-Daniela 1221F+Enter
-on va creer la classe Hello2.class: javac Hello2.java+Enter
-On introduit les arguments apres l'appel au programme: java Hello2 Marcel Cornel Ionel Gigel+Enter
Resultat:
Bonjour Marcel, Cornel, Ionel, Gigel!

*/

