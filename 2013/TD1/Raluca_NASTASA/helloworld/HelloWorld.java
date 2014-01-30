/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helloworld;

/**
 *
 * @author IBM
 */
public class HelloWorld {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     System.out.println("Bonjour monde");
      if (args.length > 0)
 /*sans cette test on a l'erreur <<Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 0>>*/
      {
        System.out.println(args[0]);
      }
        // TODO code application logic here
    }
}
