/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package firstgui;

import java.awt.event.*;
import javax.swing.*;


/**
 *
 * @author user
 */
public class FirstGUI implements ActionListener {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        FirstGUI gui= new FirstGUI();
        gui.go();
        
    }
  
    public void go(){
        
        JFrame frame= new JFrame();
        JButton buton=new JButton("click me");
        buton.addActionListener(this);
        frame.add(buton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        buton.setText("I`ve been clicked");
    }
    
}
