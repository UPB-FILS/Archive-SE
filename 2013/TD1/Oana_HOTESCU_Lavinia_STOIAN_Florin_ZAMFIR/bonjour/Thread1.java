/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bonjour;

import java.util.ArrayList;

/**
 *
 * @author Oana HOTESCU, Lavinia STOIAN, Florin ZAMFIR
 */
public class Thread1 extends Thread{//se construieste o clasa care extinde interfata Thread
    private final ArrayList<String> list;
    
public Thread1(ArrayList<String> list){//se face constructorul clasei care primeste ca parametru o variabila de tip ArrayList
this.list=list;
}

	@Override
	public void run (){
	
		for (int i=0;i<this.list.size(); i++) {//se parcurge ArrayList
                System.out.println("Bonjour "+list.get(i));//pentru fiecare element din ArrrayList se afiseaza Bonjour "element"
                }          
        
        }
}

