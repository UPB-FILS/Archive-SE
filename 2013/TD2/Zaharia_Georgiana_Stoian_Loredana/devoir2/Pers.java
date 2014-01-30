/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devoir2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Georgi
 */
public class Pers {
    
   
    
  protected LinkedList<String> listMadames = new LinkedList();
   protected LinkedList<String> listMonsieur = new LinkedList();
  protected int MAX = 10;
  protected boolean done = false; // Also protected by lock on list.
  protected LinkedList<String> list1 = new LinkedList();
  protected LinkedList<String> list2 = new LinkedList();
  
  
  public Pers()
  {
 
  }
  public void Start()
  {
   new PersMadames().start();
  new PersMonsieur().start();
  new Individ().start();
  }
// protected BufferedReader reader;
public void ReadFile() throws IOException
{
   BufferedReader reader = new BufferedReader(new FileReader("D://ListePersonnes.txt"));
String line = null;


while ((line = reader.readLine()) != null) {
  if(line.contains("Mme.")){
      count1++;
  list1.add(line);}
  else{
      count2++;
      list2.add(line);}
}     

reader.close();

}

protected int count1;
protected int count2;
protected FileWriter fstream ;
protected BufferedWriter out;

public void OpenFileToWrite() throws IOException
{
 fstream = new FileWriter("D://Bonjour.txt");
   out = new BufferedWriter(fstream);
 
}

  /** Inner class representing the Producer side */
  class PersMadames extends Thread {
public void run() {
      while (true) {
        String justProduced=null;;
          try {
              justProduced = getElement();
          } catch (IOException ex) {
              Logger.getLogger(Pers.class.getName()).log(Level.SEVERE, null, ex);
          }
       
        synchronized(listMadames) {
            while (listMadames.size() == count1) 
            try {
              System.out.println("PersMadames WAITING");
              listMadames.wait();   
            } catch (InterruptedException ex) {
              System.out.println("PersMadames INTERRUPTED");
            }
          listMadames.addFirst(justProduced);
          listMadames.notifyAll();  // must own the lock
          System.out.println("PersMadames 1; List size now " + listMadames.size());
          if (done)
            break;
          
        }
      }
    } 
    String getElement() throws IOException {  
    String elem=list1.removeLast();
    
    return elem;
  }}
 class PersMonsieur extends Thread {
public void run() {
      while (true) {
        String justProduced=null;;
          try {
              justProduced = getElement();
          } catch (IOException ex) {
              Logger.getLogger(Pers.class.getName()).log(Level.SEVERE, null, ex);
          }
       
        synchronized(listMonsieur) {
            while (listMonsieur.size() == count2) // queue "full"
            try {
              System.out.println("PersMonsieur WAITING");
              listMonsieur.wait();   // Limit the size
            } catch (InterruptedException ex) {
              System.out.println("PersMonsieur INTERRUPTED");
            }
          listMonsieur.addFirst(justProduced);
          listMonsieur.notifyAll();  // must own the lock
          System.out.println("Pers1; List size now " + listMonsieur.size());
          if (done)
            break;
          
        }
      }
    }
   
    String getElement() throws IOException {  
      String elem=list2.removeLast();
      return elem;
    }
  }
  /** Inner class representing the Consumer side */
  class Individ  extends Thread {
    public void run() {
      while (true) {
        String obj = null;
        if(!done){
        synchronized(listMadames) {
            
            if(list1.size()==0)
            {
            done=true;
            break;
            }
          while (listMadames.size() == 0) {
            try {
              System.out.println("INDIVID WAITING ");
              listMadames.wait();  // must own the lock
            } catch (InterruptedException ex) {
              System.out.println("INDIVID INTERRUPTED");
            }
          }
          obj = listMadames.removeLast();
          listMadames.notifyAll();
          int len = listMadames.size();
          System.out.println("List size now " + len);
         
        }}
        if(done){
         synchronized(listMonsieur) {
             if(list2.size()==0)
                 break;
          while (listMonsieur.size() == 0) {
            try {
              System.out.println("INDIVID WAITING");
              listMonsieur.wait();  // must own the lock
            } catch (InterruptedException ex) {
              System.out.println("INDIVID INTERRUPTED");
            }
          }
          obj = listMonsieur.removeLast();
          listMonsieur.notifyAll();
          int len = listMonsieur.size();
          System.out.println("List size now " + len);
          
        }}
          try {
              process(obj);  
          } catch (IOException ex) {
              Logger.getLogger(Pers.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    }

    void process(String pers) throws IOException {
      
     out.write("Bonjour "+pers);
    }
  }

  
  
}

