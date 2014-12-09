/*
 * modifié le 20 novembre. 2009
 *
 * TODO Pour changer le modèle de ce fichier généré, allez à :
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JComponent;


public class Carre extends JComponent{
	boolean nondispo=false;
int positionx=0;
int positiony=0;
int longeur=0;
int largeur=0;
Image bg;
int i;
int e;
boolean coche=false;


	public Carre(int positionx, int positiony, int longueur, int largeur, Image bg, int i, int e){
this.positionx=positionx;
this.positiony=positiony;
this.longeur=longueur;
this.largeur=largeur;
this.bg=bg;
this.i=i;
this.e=e;
		super.setBounds(positionx,positiony,longueur,largeur);

		//Here is a code for setting a tooltip to the square
		//ONE THING TO DO THE NEXT RELEASE
		//setToolTipText("Click this button to enable the middle button.");
			}

    public void paintComponent(Graphics g) {
        	if(coche){
        		BufferedImage imagetemp=toBufferedImage(this.createImage(longeur-1,largeur-1));
        		Graphics2D g2d = imagetemp.createGraphics(); // on récupère le contexte graphique de la BufferedImage
        		g2d.drawImage(bg,0,0,null);

        		g2d.setColor( Color.red ); // on met l'état de couleur rouge à la BufferedImage
    			g2d.fill3DRect(0,0,longeur,1,false);
    			g2d.fill3DRect(0,0,1,longeur,false);
    			g2d.fill3DRect(0,longeur-2,longeur,1,false);
    			g2d.fill3DRect(longeur-2,0,1,longeur,false);
            	g.drawImage(imagetemp,0,0,null);
        	}
        	else{
            	g.drawImage(bg,0,0,null);
        	}


}

    /**
	 * @return Renvoie bg.
	 */
	public Image getBg() {
		return bg;
	}

	/**
	 * @param bg bg à définir.
	 */
	public void setBg(Image bg) {
		this.bg = bg;
	}

	/**
	 * @return Renvoie e.
	 */
	public int getE() {
		return e;
	}

	/**
	 * @param e e à définir.
	 */
	public void setE(int e) {
		this.e = e;
	}

	/**
	 * @return Renvoie i.
	 */
	public int getI() {
		return i;
	}

	/**
	 * @param i i à définir.
	 */
	public void setI(int i) {
		this.i = i;
	}
BufferedImage toBufferedImage(Image image) {
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

/**
 * @return Renvoie coche.
 */
public boolean isCoche() {
	return coche;
}

/**
 * @param coche coche à définir.
 */
public void setCoche(boolean coche) {
	this.coche = coche;
}
}
