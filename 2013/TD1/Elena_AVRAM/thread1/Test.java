/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thread1;

/**
 *
 * @author Acer
 */
public class Test {
public static void main(String[] args) {
    //On Introduit les noms des etudiants qu'on veut saluter
Student r1 = new Student("Claude");
Student r2 = new Student("Sandra");
Student r3 = new Student("Marie");
Student r4 = new Student("Henry");

    //On va creer les threads 
Thread t1 = new Thread(r1);
Thread t2 = new Thread(r2);
Thread t3 = new Thread(r3);
Thread t4 = new Thread(r4);

    //On commence a activer les threads 
t1.start();
t2.start();
t3.start();
t4.start();

}
}