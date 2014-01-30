/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bonjour;

/**TP 1 - Premier programme en Java<br/>
* Le programme s’utilise : <b>java HelloWorld <Betty>;</b><br/>
* Le programme affiche : <b>"Bonjour Betty !"</b>
*                        <b>"Bonjour Gabi!"</b>
*                        <b>"Bonjour Ana!"</b>
* @author Beatrice GRIGORE, Gabriela SPATARELU
* @version 0.1, 04.03.2013
 
 */

public class Bonjour
{
/**
* 
*/
public static void main(String[] args) {

    String [] lista = {"Betty","Gabi","Ana"};
    
    for (int i=0;i<=2;i++){
      new SimpleThread(lista[i]).start();
}
}
}