
package tema1;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TD1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        ArrayList<String> a=new ArrayList<>();
        InputStreamReader isr = new InputStreamReader(System.in);//cidirea de la tastatura a unei lista de nume separate cu spatiu ip string
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        StringTokenizer st=new StringTokenizer(s," ");//se imparte stringul dupa spatiu
        while (st.hasMoreTokens()) {
            a.add(st.nextToken());//se pune fiecare nume in lista
        }
            Thread th = new Thread(a);//se creaza un obiect de tipul clasei Thread1
		th.start();//se porneste thread-ul

            }   
        
}