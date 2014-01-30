/*TD1-Devoir1<br/>
 * Le programme s'utilise:<b> java Bonjour[liste_personnes];</b><br/>
 * Le programme affiche:<b>"Bonjour &lt;nom_de_la_personne&gt; !"</b>
 * @author Oana HOTESCU, LAvinia STOIAN, Florin ZAMFIR
 * @version 7.2, 04.Mar.2013
 */
package bonjour;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Bonjour {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        ArrayList<String> a=new ArrayList<>();
        InputStreamReader isr = new InputStreamReader(System.in);//se citeste de la tastatura o lista de nume separate de spatiu sub forma unei variabile de tip string
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        StringTokenizer st=new StringTokenizer(s," ");//se imparte stringul dupa spatiu
        while (st.hasMoreTokens()) {
            a.add(st.nextToken());//se pune fiecare nume in lista
        }
            Thread1 th = new Thread1(a);//se creaza un obiect de tipul clasei Thread1
		th.start();//se porneste thread-ul
		
            }   
        
}

