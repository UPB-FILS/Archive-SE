import java.io.*;
import java.util.*;
public class BonjourN {
   public static void main(String[] args){
   
		if (args.length == 0) {
			System.err.println("Utilisation: java Bonjour <prenom>");
			System.exit(0);
		}
		
	for(int i=0;i<=args.length;i++)
		System.out.print("Bonjour,"+args[i]+"!");
		
		
    }
}
