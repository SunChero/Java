/*
 * modifié 10 décembre 2009
 *
 * TODO Pour changer le modèle de ce fichier généré, allez à :
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */

/*
 * Créé le 20 sept. 2005
 *
 * TODO Pour changer le modèle de ce fichier généré, allez à :
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Fond extends JPanel{
	Image bg;
	Interface map;
	Carre[][] tableaudigits;
	int[][] grilletemporaire;

	public Fond( Image bg, Interface map){
		this.bg=bg;
		this.map=map;
		tableaudigits=new Carre[map.getSudoku().getN()][map.getSudoku().getN()];
		creagrille(map.getSudoku().getGrille());
		this.setLayout(null);
		this.setPreferredSize(new Dimension(295,295));

	}


	public void cherchernouvellesgrilles(){
			int compteur=0;
			while(true){
			while(map.sudoku.placements!=1){
				map.sudoku.init();
				map.sudoku.generateur();
			}
			compteur++;
			map.file.log(map.sudoku.ecrire());
			map.sudoku.init();
			map.sudoku.afficher(map.sudoku.tableaufinal);
			}}


	public void vidergrille(){
		//ON met la grille temporaire a null et le tableaufinal aussi

		grilletemporaire=null;
		map.sudoku.setTableaufinal(null);
			for(int i=0;i<map.sudoku.grille.length;i++){
				for(int e=0;e<map.sudoku.grille.length;e++){
					map.sudoku.setGrille(i,e,0);
						tableaudigits[i][e].setBg(new ImageIcon("images/nonselec.png").getImage());
						tableaudigits[i][e].nondispo=false;
						tableaudigits[i][e].setCoche(false);
				}
				}
			repaint();
		       map.menu.m331.setEnabled(true);
		       map.menu.m332.setEnabled(false);
			}

	public void testersinouvellegrille(){
			try {
				String[] temp=map.file.reader();
				int max=0;
				for(int i=0;i<temp.length;i++){
					for(int e=i+1;e<temp.length;e++){
					if(temp[i].equals(temp[e])){System.out.println("i : "+i+" e: "+e);}


					}
				}
				System.out.print(max);
			} catch (IOException e) {
				// TODO Bloc catch auto-généré
				e.printStackTrace();
			}
			}

	public void cachersolution(){
		//VIDE LE TABLEAU FINAl (utile pour le presse-papier)
		map.sudoku.tableaufinal=null;
		//PERMET D'Eviter UNE ECRITURE DE CHIFFRE DANS UNE CASE NON DISPONIBLE
		decochage();
					for(int i=0;i<tableaudigits.length;i++){
					for(int e=0;e<tableaudigits.length;e++){
					if(grilletemporaire[i][e]!=0){
						tableaudigits[i][e].setBg(map.nbplaces[grilletemporaire[i][e]-1]);
						tableaudigits[i][e].nondispo=true;}
					else{
						tableaudigits[i][e].setBg(new ImageIcon("images/nonselec.png").getImage());
						tableaudigits[i][e].nondispo=false;}}
					}

					repaint();
		}



	public boolean solutionnergrille(){
		boolean solution=false;
		//PERMET D'Eviter UNE ECRITURE DE CHIFFRE DANS UNE CASE NON DISPONIBLE
		decochage();

		//REMET PLACEMENT ET COMPLEXITE A ZERO
		map.sudoku.placements=0;
		map.sudoku.complexite=0;
		//SAUVEGARDE de L'etat Actuel de la Grille
		grilletemporaire=map.sudoku.clonage(map.sudoku.grille);

		//UNE TENTATIVE DE RESOLUTION
		if(map.sudoku.testervalidite()){
			map.sudoku.resolution();

				if(map.sudoku.getPlacements()==1){
					for(int i=0;i<tableaudigits.length;i++){
					for(int e=0;e<tableaudigits.length;e++){
					if(grilletemporaire[i][e]!=0){
						tableaudigits[i][e].setBg(map.nbplaces[grilletemporaire[i][e]-1]);
						tableaudigits[i][e].nondispo=true;
						}
					else{
						tableaudigits[i][e].setBg(map.nbdispo[map.sudoku.tableaufinal[i][e]-1]);
						tableaudigits[i][e].nondispo=true;}
					tableaudigits[i][e].repaint();}}
					solution=true;

		}
				else{
					pasbon();}}
				else{pasbon();}
				return solution;
	}

	public void selection(int niveaudifficulte){
		//RENITIALISATION DE LA GRILLE
		vidergrille();
		decochage();
		try {
			String[] temp=map.file.reader();
			int index=map.sudoku.random(0,temp.length);
switch(niveaudifficulte){
case 1:
	while(!(Integer.parseInt(temp[index].substring(85,temp[index].length()))<=5000)){
		index=map.sudoku.random(0,temp.length);
	}
break;
case 2:
	while(!(Integer.parseInt(temp[index].substring(85,temp[index].length()))>5000) && !(Integer.parseInt(temp[index].substring(85,temp[index].length()))<80000)){
		index=map.sudoku.random(0,temp.length);
	}
break;
case 3:
	while(!(Integer.parseInt(temp[index].substring(85,temp[index].length()))>=80000)){
		index=map.sudoku.random(0,temp.length);
	}
break;
}
System.out.println("index"+index);
int nombreaplace=0;
for(int i=0;i<tableaudigits.length;i++){
	for(int e=0;e<tableaudigits.length;e++){
		map.sudoku.setGrille(i,e,temp[index].charAt(nombreaplace)-48);


			if((temp[index].charAt(nombreaplace)-48)!=0){

				tableaudigits[i][e].nondispo=true;
				tableaudigits[i][e].setBg(map.nbplaces[(temp[index].charAt(nombreaplace)-48)-1]);
			}

			nombreaplace++;
	}
	}
repaint();
map.sudoku.afficher(map.sudoku.grille);
		} catch (IOException e) {
			// FAIRE Bloc catch auto-généré
			e.printStackTrace();
		}
		}

	public void decochage(){
		for(int i=0;i<tableaudigits.length;i++){
			for(int e=0;e<tableaudigits.length;e++){
				tableaudigits[i][e].coche=false;
				tableaudigits[i][e].nondispo=false;
			}
			}
		map.ecouteur.carre=null;
	}


	public void pasbon(){
		String temp="";
		if(map.sudoku.getPlacements()>1){
			temp="Plusieurs solutions !";
		}
		else{
			temp="Aucune solution !";
		}

			JOptionPane d = new JOptionPane();
			d.showMessageDialog( this,
			                    temp,
			                    "Erreur",
			                     JOptionPane.INFORMATION_MESSAGE);
	}






    public void paintComponent(Graphics g) {
        g.drawImage(bg,0,0,null);
}

	/**
	 * @return Renvoie bg.
	 */
	public Image getBg() {
		return bg;
	}
	private void creagrille(int[][] tableau) {
		int position=0;
		int positionx=0;
		int positiony=0;
		for(int i=0;i<tableau.length;i++){
			for(int e=0;e<tableau.length;e++){


				positionx=(i*map.carre);
			positiony=(e*map.carre);

			if(i==0){
				positionx=3;
				}
			if(e==0){
				positiony=3;
				}

			if(i%3==0 && i!=0 && i<3){
				positionx=(i*map.carre)+4;
			}
			if(i%3==0 && i!=0){
				positionx=(i*map.carre)+4;
			}
			if(i%3==0 && i!=0 && i>3){
				positionx=(i*map.carre)+5;
			}

			else if(i!=0 && i<3){
				positionx=(i*map.carre)+3;
			}
			else if(i!=0 && i>3 && i<6){
				positionx=(i*map.carre)+4;
			}
			else if(i!=0 && i>6){
				positionx=(i*map.carre)+5;
			}


			if(e%3==0 && e!=0 && e<3){
				positiony=(e*map.carre)+4;
			}
			if(e%3==0 && e!=0){
				positiony=(e*map.carre)+4;
			}
			if(e%3==0 && e!=0 && e>3){
				positiony=(e*map.carre)+5;
			}

			else if(e!=0 && e<3){
				positiony=(e*map.carre)+3;
			}
			else if(e!=0 && e>3 && e<6){
				positiony=(e*map.carre)+4;
			}
			else if(e!=0 && e>6){
				positiony=(e*map.carre)+5;
			}
				tableaudigits[i][e]=new Carre(positiony,positionx,map.carre,map.carre, new ImageIcon("images/nonselec.png").getImage(),i,e);
				this.add(tableaudigits[i][e],position);
				position++;

			}
		}}


	public void impressionapartirdetableau(int[][] tableau){
		for(int i=0;i<tableau.length;i++){
			for(int e=0;e<tableau.length;e++){
			if(tableau[i][e]!=0){
				tableaudigits[i][e].setBg(map.nbdispo[tableau[i][e]-1]);
				tableaudigits[i][e].nondispo=true;}
			else{
				tableaudigits[i][e].setBg(new ImageIcon("images/nonselec.png").getImage());
				tableaudigits[i][e].nondispo=true;}}}
			repaint();
	}
}

