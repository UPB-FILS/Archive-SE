/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bonjour2;

/**
 *
 * @author Oana User
 */
public class Personnes {
    
    private String tip;
    private String nume;

    public Personnes() {
    }
    
    

    public Personnes(String tip, String nume) {
        this.tip = tip;
        this.nume = nume;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    @Override
    public String toString() {
        return "Personnes{" + "tip=" + tip + ", nume=" + nume + '}';
    }
    
}
