/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devoir1;

/**
 *
 * @author daniela
 */
public class Student implements Runnable {
 
private String name;
private int nrS;

public Student(String name)// constructorul
{
this.name=name;
 }


    @Override
    public void run(){
try {

System.out.println("Bonjour "+name+"!");//le message qu'on veut afficher 
Thread.sleep(2000);//apres 2 secondes,le thread va etre arrete et le suivant va commencer
}

catch (InterruptedException exception){}
}
}
    

