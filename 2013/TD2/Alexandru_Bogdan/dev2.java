/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devoir2;
import java.io.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author Alexandru Bogdan Georges 1221F
 */
public class Devoir2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
  	try {
			File file = new File("ListePersonnes.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
                       StringBuffer stringBuffer = new StringBuffer();
			String line;
                        
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append(" \n");
                             
                                
                               
			}
			fileReader.close();
			System.out.println( "Bonjour "+  stringBuffer.toString());
		 } catch (IOException e) {
			e.printStackTrace();
		}
	}
}
