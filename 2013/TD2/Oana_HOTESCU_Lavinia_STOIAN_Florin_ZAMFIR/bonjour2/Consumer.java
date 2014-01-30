/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bonjour2;


import java.io.*;

/**
 *
 * @author Oana User
 */
public class Consumer extends Thread {
  	private int n;
  	private Buffer consBuf;

  	public Consumer (int m, Buffer buf) {
  		n = m;
  		consBuf = buf;

    }
        {
        try {
            BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Lavy\\Desktop\\Facultate\\An II\\Sem II\\SE\\Bonjour.txt"));
        } catch (IOException e1)


{
System.out.println(e1);// tratarea excep?iei
}
    }

    public void run() {
    	int value;
    for (int i = 0; i < n; i++) {
    		try {

    			value = consBuf.get();

    		}  catch (InterruptedException e) {return;}
    		try {
    			Thread.sleep( (int) Math.random() * 100); // ia pauza pentru un interval de timp ales aleator
    		} catch (InterruptedException e) {return;}

    	}
    }
  }