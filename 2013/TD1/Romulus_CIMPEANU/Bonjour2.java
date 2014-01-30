public class Bonjour2 
{
/**
* @param args contient le nom de la personne
*/
public static void main(String[] args) {
args = new String[]{"Ioan","Victor"}	;
if (args.length == 0) {
System.err.println("Utilisation: java HelloWorld <prenom>");
System.exit(0);
}
for(int i=0;i<args.length;i++)
System.out.println("Bonjour " + args[i] + " !");
}
}