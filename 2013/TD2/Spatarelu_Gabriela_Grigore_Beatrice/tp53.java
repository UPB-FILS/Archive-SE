/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp53;

/**
 *
 * @author ROX
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
public class TP53 {

   public static void main(String[] args) throws Exception {
    InputStream in = System.in;
    BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));

    int letter;

    while ((letter = in.read()) != -1) {
      bw.write((char) letter);
      bw.flush();
    }
  }
