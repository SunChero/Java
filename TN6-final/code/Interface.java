import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import java.util.ArrayList;

import javax.swing.*;

public class Interface extends JFrame
{
	// Variables declaration
	JPanel contentPanel;
	Sudoku sudoku;
	Fond supanel;

	//Ecouteur et Presse-Papier
	Ecouteur ecouteur;
    ModifPressPap mpp;

	FileLogger file;
	Menu menu;

	//Tableaux pour les chiffres
	Image[] nbplaces=new Image[9];
	Image[] nbdispo=new Image[9];


	//IMAGE JPANEL
	Image map=new ImageIcon("images/sudoku.png").getImage();
	Image digi=new ImageIcon("images/digits.gif").getImage();
	BufferedImage mapmodifiable= toBufferedImage(map);
	BufferedImage digits= toBufferedImage(digi);
	int carre=30;

	public Interface()
	{
		super();
		this.setVisible(false);
		file=new FileLogger(this, "sudoku.txt");
		file.repertoire();
		//LANCEMENT DE SUDOKU ET MISE EN DAEMON
		this.sudoku=new Sudoku(9);
		sudoku.setPriority(Thread.MAX_PRIORITY);
		sudoku.start();
		initializeComponent();
		creationdigits();
		this.setVisible(true);
	}

public void creationdigits(){

	int indice=0;
	for(int i=0;i<=digits.getHeight()-(carre-1);i+=(carre-1)){
		nbplaces[indice]=digits.getSubimage(29, i, carre-1, carre-1);
		nbdispo[indice]=digits.getSubimage(0, i, carre-1, carre-1);
		indice++;
	}

}

	private void initializeComponent()
	{
		//CONTENTPANEL
		contentPanel = (JPanel)this.getContentPane();
		contentPanel.setLayout(new GridBagLayout());

 		//SUDOKUPANEL
		supanel=new Fond(map, this);


		//MENU
		menu=new Menu(this);
		this.setJMenuBar(menu);

		//ECOUTEUR ET PRESSEPAPIER
		ecouteur=new Ecouteur(this);
		mpp = new ModifPressPap();


		GridBagConstraints c=new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 1;
	 	c.gridwidth=1;
	 	c.gridheight=1;
	 	c.weightx=100;
	 	c.weighty=100;
 	c.fill=GridBagConstraints.NONE;
 	c.ipadx=0;
 	c.ipady=0;
		contentPanel.add(supanel,c);


		this.setFocusable(true);
		this.addKeyListener(new KeyAdapter(){
			 public void keyPressed(KeyEvent e) {
                                ArrayList al = sudoku.getLog();
				  int caract = e.getKeyCode();
				  switch (caract) {
				    case KeyEvent.VK_1 :
				    	ecouteur.chiffre(1);
                                        al.add(ecouteur.carre);
                                        
				     break;
				    case KeyEvent.VK_2 :
				    	ecouteur.chiffre(2);
				      al.add(ecouteur.carre);
				     break;
				    case KeyEvent.VK_3 :
				    	ecouteur.chiffre(3);
				      al.add(ecouteur.carre);
				     break;
				    case KeyEvent.VK_4 :
				    	ecouteur.chiffre(4);
				      al.add(ecouteur.carre);
				     break;
				    case KeyEvent.VK_5 :
				    	ecouteur.chiffre(5);
				      al.add(ecouteur.carre);
                                      
				     break;
				    case KeyEvent.VK_6 :
				    	ecouteur.chiffre(6);
				      al.add(ecouteur.carre);
				     break;
				    case KeyEvent.VK_7 :
				    	ecouteur.chiffre(7);
				      al.add(ecouteur.carre);
				     break;
				    case KeyEvent.VK_8 :
				    	ecouteur.chiffre(8);
				      al.add(ecouteur.carre);
				     break;
				    case KeyEvent.VK_9 :
				    	ecouteur.chiffre(9);
				      al.add(ecouteur.carre);
				    	break;
				    case KeyEvent.VK_DELETE :
				    	ecouteur.chiffre(0);
				     break;
				  }
		   }
		});
		this.setContentPane(contentPanel);
		this.setLocation(new Point(350,350));
		this.setTitle("Sudoku");
		this.setResizable(false);
		this.setSize(new Dimension(540, 405));
	}


public BufferedImage toBufferedImage(Image image) {
        /** On test si l'image n'est pas déja une instance de BufferedImage */
        if( image instanceof BufferedImage ) {
                /** cool, rien à faire */
                return( (BufferedImage)image );
        } else {
                /** On s'assure que l'image est complètement chargée */
                image = new ImageIcon(image).getImage();
                /** On crée la nouvelle image */
                BufferedImage bufferedImage = new BufferedImage(
                                                      image.getWidth(null),
                                                      image.getHeight(null),
                                                      BufferedImage.TYPE_INT_RGB );
                Graphics g = bufferedImage.createGraphics();
                g.drawImage(image,0,0,null);
                g.dispose();
                return( bufferedImage );
        }
}


	public static void main(String[] args)
	{
		window fenetreintro = new window( 440, 400, 1 );
               
		//INTERFACE GRAPHIQUE
		Interface graphique=new Interface();
                window fenetreregles = new window( 440, 400, 0 );
		graphique.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				System.exit(0);
			}
		});
	}


public Fond getSupanel() {
	return supanel;
}

public Image getNbdispo(int i) {
	return nbdispo[i];
}

public Image getNbplaces(int i) {
	return nbplaces[i];
}

public Sudoku getSudoku() {
	return sudoku;
}

public void setSudoku(Sudoku sudoku) {
	this.sudoku = sudoku;
}



public ModifPressPap getMpp() {
	return mpp;
}

/**
 * @return Renvoie contentPanel.
 */
public JPanel getContentPanel() {
	return contentPanel;
}

/**
 * @param contentPanel contentPanel à définir.
 */
public void setContentPanel(JPanel contentPanel) {
	this.contentPanel = contentPanel;
}

}





