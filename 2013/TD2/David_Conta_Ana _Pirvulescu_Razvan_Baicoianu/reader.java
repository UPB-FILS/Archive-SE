//Imports
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
//javadoc
/** 

@author [David Conta][Ana Pirvulescu][Razvan Baicoianu]
@version 1.0
@param [File f][The file to be read ].
@return [ArrayList of Strings] 
@exception [Throws a IOException] [checks for a nominal IOstream condition] 

**/

public class reader  {
	//Fields and such

	private Scanner s = null ;
	

	//MethodsOutput

	 
			// TODO Auto-generated method stub
		

	ArrayList<String>  read() throws IOException {
		try {
			Scanner s = new Scanner(new File("input.txt"));
			ArrayList<String> list = new ArrayList<String>();
			while (s.hasNext()){
			    list.add(s.nextLine());
			}
			return list ;


            

        } finally {
            if (s != null) {
                s.close();
               
            }
        }

		}

	}




	
	
