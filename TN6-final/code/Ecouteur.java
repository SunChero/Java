

import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


/**
 *
 * @author Christophe modifié par colombiano Kedowide
 * @version
 */
public class Ecouteur{
Interface map;
	int x;
	int y;
	int minorant=21;


	Carre carre;
	Carre temporaire;
	public Ecouteur(Interface mapper) {
		super();
		this.map=mapper;
		map.getSupanel().addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {

				x=arg0.getX();
					y=arg0.getY();
					if(map.getSupanel().getComponentAt(x,y)instanceof Carre){
					temporaire=(Carre)map.getSupanel().getComponentAt(x,y);
					if(!temporaire.nondispo){
						if(carre!=null){
							carre.setCoche(false);
							carre.repaint();
						}

						temporaire.setCoche(true);
								carre=temporaire;
								carre.repaint();
						}}}});
}

public void chiffre(int i){
if(carre!=null){
	if(i!=0){
		carre.setBg(map.getNbdispo(i-1));
		carre.repaint();
		//INSCRIRE CHIFFRE DANS MATRICE
		map.getSudoku().setGrille(carre.getI(), carre.getE(),i);
		map.getSudoku().afficher(map.getSudoku().getGrille());
	}
	else{
		carre.setBg(new ImageIcon("images/nonselec.png").getImage());
		carre.repaint();
		//INSCRIRE CHIFFRE DANS MATRICE
		map.getSudoku().setGrille(carre.getI(), carre.getE(),i);
		map.getSudoku().afficher(map.getSudoku().getGrille());
	}
	}
//TEST SI LA GRILLE EST REMPLIE
//si oui, on essaye de resoudre la grille et si elle est resoluble on affiche un message
if(map.getSudoku().grilleremplie()){
	System.out.println("REMPLIE");
	if(map.sudoku.testervalidite()){
		JOptionPane d = new JOptionPane();
		d.showMessageDialog( map,
		                    "Bon travail !",
		                    "La grille est correct",
		                     JOptionPane.INFORMATION_MESSAGE);
	}
}
}

}