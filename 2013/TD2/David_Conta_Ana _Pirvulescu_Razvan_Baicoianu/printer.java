
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;


//javadoc
/** 

@author [David Conta][Ana Pirvulescu][Razvan Baicoianu]
@version 1.0
@param [ArrayList<String> input ,File f][The input to be printed along with the file to which we print].
@return [void] 
@exception [Throws a IOException] [what is says in case of IO fubar]

**/

public class printer implements Runnable {
	//Fields and such
	private ArrayList<String> input;
	private FileWriter out ;
	private static final String f = "output.txt" ;
	
	
	
	//Constructor
	public printer(ArrayList<String> input  ){
		this.input =input;
		
	}
	
	//method
public void print(ArrayList<String> input )throws  IOException{
	try{
		out = new FileWriter(f);
		System.out.println("Begining print");
		
		for(int i = 0; i < input.size(); i++)
		      System.out.println("Element at index " + i + " = " + input.get(i));
		 
		      out.write("\n Bonjour : ");
		
		      
		      ListIterator<String> li = input.listIterator();
		 
		      while(li.hasNext())
		       out.write(li.next()+ " , ");	
	
		
	System.out.println("Done");
	 
	}
	finally{
		out.flush();
		out.close() ;
	} 
	



	
}

@Override
public void run()  {
try {
		
		print(input);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	// TODO Auto-generated method stub
	
}
}