package setema1bonjour;

/*
* TD 1 - Premier devoir a SE<br/>
* Le programme s’utilise : <b>java HelloWorld &lt;nom_de_la_personne&gt;</b><br/>
* Le programme affiche : <b>"Bonjour &lt;nom_de_la_personne&gt; !"</b>
* @authors Mirela URSE, Andreea RADU 
* @version 7.2, 04.Mar.2013
* 
 */

public class Bonjour {

    /**
     * @param args est une liste qui contient les noms des personnes
     */
    public static void main(String[] args) 
    {
        args = new String[5]; // on defini la liste
        args[0]="Stefan";  // on atribue des valeurs a chaque element de la liste
        args[1]="Robert";
        args[2]="Andrei";
        args[3]="Cristi";
        args[4]="Mircea";
        MyThread thr = new MyThread(args); // on declare un object de type mythread
	thr.start(); // on start le thread
	
    }
}

