import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.concurrent.Callable;

//javadoc
/** 

@author [David Conta][Ana Pirvulescu][Razvan Baicoianu]
@version 1.0
@param [ArrayList<String> input ][The input unsorted list to be sorted ].
@return [ArrayList of Strings] 
@exception [Throws a InputMismatchException] [what is says in case of input fubar]

**/

public class sorter   {
	//Fields and such
	ArrayList<String> input;
	
	//Constructor
	public sorter(ArrayList<String> input){
		this.input = input ;
	}
	
	//method
public ArrayList<String> sort()throws  InputMismatchException{
	try{
		System.out.println("Begining Sort");
	
	Collections.sort(input);
	System.out.println("Done");
	return input ; 
	}
	finally{} 
	
}





}