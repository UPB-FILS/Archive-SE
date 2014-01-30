/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thread1;

import java.util.Random;

/**
 *
 * @author Acer
 */
public class Student implements Runnable {
 
private String name;
private int nrS;

public Student(String name)// le constructeur
{
this.name=name;
 }


public void run(){
try {

System.out.println("Bonjour "+name+"!");//le message qu'on vut afficher 
Thread.sleep(2000);//apres 2 secondes,le thread va etre arrete et le suivant va commencer
}

catch (InterruptedException exception){}
}
}

