/*********************************************************************/
/*************            window.java            *********************/
/*************   Affiche une fenetre d'intro ou  *********************/
/************* une fenetre expliquant les regles *********************/
/*************       Modifié par Colombiano k    *********************/
/*********************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class window extends JWindow
{
  ImageIcon imIntro = new ImageIcon("images/Intro.jpg");
  ImageIcon imRegles = new ImageIcon("images/lesregles.jpg");
  static Thread t = new Thread();
  static int thread = 0;
  static JButton bouton;
  static int X;
  static int Y;
  /* Parametres du constructeur :
     X, Y: taille de la fenetre :)
     type: 1-> Intro, 2->regles */
  public window( int X, int Y, int type ) {

    super();
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    setSize(X,Y);
    setLocation( (dim.width - X)/2 , (dim.height - Y)/2);
    setVisible(true);
    Container fen = getContentPane();

    if (type == 1 ) bouton = new JButton(imIntro);
                     
    else            bouton = new JButton(imRegles);
     /* bouton.setBorderPainted(false); 
     bouton.setContentAreaFilled(false); 
    bouton.setFocusPainted(false); 
     bouton.setOpaque(false); */
    bouton.setPreferredSize(new Dimension(X, Y) );
   
    fen.add( bouton);
    bouton.setVisible( true );

    show();
   /* Si c'est une fenetre d'intro,
      on ne l'affiche que 2 secondes */
    if( type == 1 ) {
        try {
            t.sleep(2000);
            thread = 1;
        }
        catch( java.lang.InterruptedException ex ) {
            JOptionPane.showMessageDialog(null, "erreur");
            }
        dispose();

    }
    /* Si c'est une fenetre de regles,
       on ne la ferme que quand l'utilisateur clique */
    else {
        bouton.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                     dispose();
                }
        });
    }

 }
}



