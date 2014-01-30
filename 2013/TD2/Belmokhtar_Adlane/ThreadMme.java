import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;


public class ThreadMme extends Thread 
{
public synchronized void run()
{
	
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader("ListeNoms.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	
	String strLine;
	
		try {
			while ((strLine = in.readLine()) != null)  
			 {  StringTokenizer st=new StringTokenizer(strLine," ");
			 String tip=st.nextToken();
			 String nume=st.nextToken();
			 if(tip.equalsIgnoreCase("Mme"))
			 {
			 System.out.println("Bonjour "+tip+" "+nume);
			 scrie("Bonjour"+" "+tip+" "+nume);
			 }
}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

public void scrie(String s)
{
	try{
		  // Create file 
		  FileWriter fstream = new FileWriter("out.txt",true);
		  BufferedWriter out = new BufferedWriter(fstream);
		  out.write(s +"\n");
		  //Close the output stream
		  out.close();
		  }catch (Exception e){//Catch exception if any
		  System.err.println("Error: " + e.getMessage());
		  }	
}
	
}

