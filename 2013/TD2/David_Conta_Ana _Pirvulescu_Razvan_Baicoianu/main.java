import java.io.IOException;

import java.util.InputMismatchException;

/** 
 
    @author [David Conta][Ana Pirvulescu][Razvan Baicoianu]
    @version 1.0
    @param [String[] args][classic main garb ].
    @return [void] - 
**/
public class main {
 
	public static void main( String[] args)  {
		// TODO Auto-generated nmethod stub
		
				
         
		try {
			new reader().read() ;
			(new Thread(new printer(new sorter(new reader().read()).sort()))).start();
		} catch (InputMismatchException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
