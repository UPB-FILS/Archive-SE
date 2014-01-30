public class Bonjour {
   public static void main(String[] args) {
   
		if (args.length != 1) {
			System.err.println("Utilisation: java Bonjour <prenom>");
			System.exit(0);
		}
        System.out.print("Bonjour,"+args[0]+"!");// args[0] este primul parametru
    }
}
