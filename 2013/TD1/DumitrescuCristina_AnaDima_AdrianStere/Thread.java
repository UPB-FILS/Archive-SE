package tema1;

import java.util.ArrayList;

/**
 *
 * @author Tudor TURCU
 */
public class Thread extends TD1{//se construieste o clasa care extinde interfata Thread
    private final ArrayList<String> list;
    
public Thread(ArrayList<String> list){//se face constructorul clasei care primeste ca parametru o variabila de tip ArrayList
this.list=list;
}

		public void run (){

		for (int i=0;i<this.list.size(); i++) {//se parcurge ArrayList
                System.out.println("Bonjour "+list.get(i));//pentru fiecare element din ArrrayList se afiseaza Bonjour "element"
                }          
        
        }
}
