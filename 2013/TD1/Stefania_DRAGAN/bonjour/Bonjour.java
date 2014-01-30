/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bonjour;

/**
 *
 * @author user
 */
public class Bonjour {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
                   
        String str = "Stefania Andreea Maria Ioana Florina";
        String[] temp;
        
        String delimiter = " ";
       
        
        temp = str.split(delimiter);
       
              
        for(int i =0; i < temp.length ; i++)        
            System.out.println("Bonjour " +temp[i]);
       
        
       
        
       
  }
    }
 
