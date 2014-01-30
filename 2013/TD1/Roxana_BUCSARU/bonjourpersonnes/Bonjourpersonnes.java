
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bonjourpersonnes;



/**
 * Devoir 1
 * Mon 1er Labo
 * @author Roxana Bucsaru 1221 F 
 * 25.03.2013
 */

public class Bonjourpersonnes {
    String name;
    
    public void sayHello() {
        System.out.println("Bonjour "+getName()+"!");
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public static void main(String[] args)
    
    {
    
        Bonjourpersonnes hello = new Bonjourpersonnes();
        hello.setName("Bucsaru");
        hello.sayHello();
        hello.setName("Roxana");
        hello.sayHello();
        hello.setName("Violeta");
        hello.sayHello();
    }
}