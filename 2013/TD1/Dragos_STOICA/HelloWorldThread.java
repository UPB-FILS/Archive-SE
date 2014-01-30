/**
 * TP 1 - Test fils d'execution
 * 
 * 
 */


import java.io.*;

public class HelloWorldThread
{
	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("Utilisation: java HelloWorldThread <liste prenom,nom>");
			System.exit(0);
		}

		for (int i = 0; i<args.length; i++){
			FilsExecution f = new FilsExecution(args[i]);
		}
	}
}

class FilsExecution extends Thread{
	String arg;
	
	public FilsExecution (String str){
		super(str);
		this.arg = str;
		start();
	}
	
	
	public void run(){
		System.out.println("Bonjour " + this.arg  + " !");
	}

}
