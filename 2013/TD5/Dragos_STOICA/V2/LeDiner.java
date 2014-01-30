import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/** classe principale lancant les processus philosophes*/
public class LeDiner extends JFrame implements ActionListener {

	/** nb de philosophes*/
	int nbPhilo;
	/** panneau contenant les objets graphiques (labels, ...)*/
	JPanel panneau;
	/** tableau de labels representant les philosophes */
	JLabel[] lblPhilos;
	/** tableau de cases a cocher representant les fourchettes*/
	JCheckBox[] cbFourchettes;
	/** tableau de couleurs a associer aux philosophes */
	final static Color[] tabCouleurs = {Color.BLACK, Color.BLUE, Color.RED, Color.GRAY, Color.GREEN};
	/** lien vers la gestion des fourchettes*/
	Fourchettes ustensiles;
	/** tableau de Philosophes*/
	Philosophe[] convives;
	
	/** initialisation du diner avec 5 philosophes par defaut*/
	LeDiner() 
	{
		nbPhilo = 5;
		lblPhilos = new JLabel[nbPhilo];
		cbFourchettes = new JCheckBox[nbPhilo] ;
		ustensiles = new Fourchettes(nbPhilo);
		convives = new Philosophe[nbPhilo];
		setBounds(10, 10, 500, 500);
		panneau = new JPanel();
		initPanneau();
		getContentPane().add(panneau);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/** initialisation du diner avec nb philosophes en parametre 
	 * @param _nom Nom de la fenetre
	 * @param _nbPhilo nb de philosophes */
	LeDiner(String _nom, int _nbPhilo) 
	{
		super(_nom);
		nbPhilo = _nbPhilo;
		lblPhilos = new JLabel[nbPhilo];
		cbFourchettes = new JCheckBox[nbPhilo] ;
		ustensiles = new Fourchettes(nbPhilo);
		convives = new Philosophe[nbPhilo];
		setBounds(10, 10, 500, 500);
		panneau = new JPanel();
		initPanneau();
		getContentPane().add(panneau);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/** initialisaion du panneau (construction et positionnement des labels, des cases a cocher...)*/
	void initPanneau()
	{
		panneau.setLayout(new GridLayout(0, 2));
		for (int i=0; i<nbPhilo; i++)
		{
			lblPhilos[i] = new JLabel("philosophe "+i+" pense en attendant les fourchettes");
			lblPhilos[i].setForeground(tabCouleurs[i%5]);
			panneau.add(lblPhilos[i]);
			cbFourchettes[i] = new JCheckBox("fourchette no "+i);
			cbFourchettes[i].setSelected(true);
			panneau.add(cbFourchettes[i]);
		}
		JButton jbGo = new JButton("go");
		jbGo.addActionListener(this);
		panneau.add(jbGo);
	}

	/** fonction lancee suite a l'activation du bouton Go...<br>
	 * cree les philosophes et les "lance"*/
	void go()
	{
		for(int i=0; i<nbPhilo; i++)
			convives[i] = new Philosophe(i, 5, ustensiles, this);
		for(int i=0; i<nbPhilo; i++)
			convives[i].start();
	}

	/** fonction lancee par un philo pour indiquer qu'il mange
	 * @param no no du philosophe lancant la fonction
	 * @param nbBouchees nb de bouchees restant a manger pour le philosope lancant la fonction*/
	public void manger(int no, int nbBouchees)
	{
		Color couleur = tabCouleurs[no];
		lblPhilos[no].setForeground(couleur );
		lblPhilos[no].setText("philosophe "+no+" mange, il me reste " + nbBouchees + " bouchees.");
		cbFourchettes[no].setForeground(couleur);
		cbFourchettes[(no+1)%nbPhilo].setForeground(couleur);		
		cbFourchettes[no].setSelected(false);
		cbFourchettes[(no+1)%nbPhilo].setSelected(false);
		this.repaint();
	}

	/** fonction lancee par un philo pour indiquer qu'il pense
	 * @param no no du philosophe lancant la fonction */
	public void penser(int no)
	{
		Color couleur = tabCouleurs[no];
		lblPhilos[no].setForeground(couleur );		lblPhilos[no].setText("philosophe "+no+" pense");
		cbFourchettes[no].setForeground(Color.BLACK);
		cbFourchettes[(no+1)%nbPhilo].setForeground(Color.BLACK);
		cbFourchettes[no].setSelected(true);
		cbFourchettes[(no+1)%nbPhilo].setSelected(true);
		this.repaint();
	}

	/** fonction lancee par un philo pour indiquer qu'il attend
	 * @param no no du philosophe lancant la fonction */
	public void penserEtAttendre(int no)
	{
		Color couleur = tabCouleurs[no];
		lblPhilos[no].setForeground(couleur );		lblPhilos[no].setText("philosophe "+no+" pense en attendant");
		cbFourchettes[no].setForeground(Color.BLACK);
		cbFourchettes[(no+1)%nbPhilo].setForeground(Color.BLACK);
		cbFourchettes[no].setSelected(true);
		cbFourchettes[(no+1)%nbPhilo].setSelected(true);
		this.repaint();
	}

	/** fonction lancee par un philo pour indiquer qu'il a fini
	 * @param no no du philosophe lancant la fonction */
	public void finir(int no)
	{
		Color couleur = tabCouleurs[no];
		lblPhilos[no].setForeground(couleur );lblPhilos[no].setText("philosophe "+no+" : j'ai fini !!!");
		cbFourchettes[no].setForeground(Color.BLACK);
		cbFourchettes[(no+1)%nbPhilo].setForeground(Color.BLACK);
		cbFourchettes[no].setSelected(true);
		cbFourchettes[(no+1)%nbPhilo].setSelected(true);
		this.repaint();
	}

	/** evenement declenche par l'action sur le bouton go<br>
	 * lance la fonction go */
	public void actionPerformed(ActionEvent event) {
		String nomEvent = event.getActionCommand();
		// verification du texte sur l'objet declenchant l'evenement
		if(nomEvent.equalsIgnoreCase("go")) 
			go();		
	}

	/** fonction principale, cree un diner et l'affiche*/
	public static void main(String[] args)
	{
		LeDiner ld = new LeDiner("coucou", 5);
		ld.setVisible(true);
	}

}
