/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bonjour2;

/**
 *
 * @author Oana User
 */
class Buffer {
  		private int contents;
  		private boolean empty = true;
                private String s;
                String tip1="M.";
                String tip2="Mme.";
  		
  		public synchronized void put (String s1,String s2) throws InterruptedException { 
  			while ((empty == false)&&(s1.equals(tip2))) { 	//asteapta pana cand buffer-ul devine gol
  				try { wait(); }
  				catch (InterruptedException e) {throw e;}
  			}
  			empty = false;
  			System.out.println("Bonjour " + s1+" "+s2+"!");
  			notify();
  		} 
  		
  		public synchronized String get () throws InterruptedException {
  			while (empty == true)  {	//asteapta pana cand apare ceva in buffer
  				try { 	wait(); }
  				catch (InterruptedException e) {throw e;}
  			}
  			empty = true;
  			notify();
  			int val = contents;
  			System.out.println("Consumer: got..." + val);
                        String str=s;
  			return s;
  		}

    void put(int i) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}