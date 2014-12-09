import java.util.ArrayList;

/*
 * modifié le 10 déc. 2009
 *
 * TODO Pour changer le modèle de ce fichier généré, allez à :
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */

public class Sudoku extends Thread{

	int grille[][];
	int n;
	int placements=0;
	int complexite=0;
	int[][] tableaufinal;
        ArrayList log;


	public Sudoku(int n){
		this.n=n;
		grille=new int[n][n];
                log = new ArrayList();
		init();
	}


	public void init(){
			for(int i=0;i<grille.length;i++){
				for(int e=0;e<grille[i].length;e++){
					grille[i][e]=0;
				}
			}
			placements=0;
			complexite=0;
		}

	public String ecrire(){
		String temp="";
		for(int i=0;i<grille.length;i++){
			for(int e=0;e<grille[i].length;e++){
				temp+=Integer.toString(grille[i][e]);
			}
		}
		temp+=" // "+Integer.toString(complexite);

		return temp;
	}


	public void generateur(){
			//ON SAUT DE QUADRAN EN QUADRAN
		for(int i=0;i<n;i+=3){
			for(int e=0;e<n;e+=3){

				int[] quadran=quadran(i,e);
				int lignedebut=quadran[0];
				int colonnedebut=quadran[1];


			int compte=0;
				while(compte!=5){
				int chiffre=random(1,9);
				int ligne=random(lignedebut,3);
				int colonne=random(colonnedebut,3);

				//MAUVAIS CHOIX
			while( !cherchequadran(ligne,colonne,grille, chiffre) || !cherchehb(ligne,colonne,grille,chiffre) || !cherchedg(ligne,colonne,grille,chiffre)){
				chiffre=random(1,9);
				ligne=random(lignedebut,3);
				colonne=random(colonnedebut,3);
			}
			grille[ligne][colonne]=chiffre;
			compte++;


				}
		}}
		resolution();
	}




	public int random(int inf, int sup){
		return (int)(inf+((Math.random()*sup)));
	}





	public void resolution(){
		//afficher(grille);


//on Commence la routine par 1
		int chiffre=1;
		int colonne=prochaincolonne(chiffre,grille);
		while(colonne==-1){
			chiffre++;
		}
		placement(colonne, grille,chiffre);
		}

	public void placement(int colonne, int[][] tableau, int chiffre){

		//if(complexite<5000000){
		//BOUCLAGE SUR LES LIGNES
		for(int i=0;i<n;i++){


			//ICI ON TEST AVEC LE CHIFFRE
		if(libre(i,colonne,tableau,chiffre)){//chiffre
complexite++;

			if(tableau[i][colonne]==0){
				int[][] tab=clonage(tableau);
				tab[i][colonne]=chiffre;//chiffre

				int nextcolonne=prochaincolonne(chiffre,tab);//chiffre
				if(nextcolonne!=-1){
				placement(nextcolonne, tab, chiffre);

				}

			else{
				//ON EST SUR LA DERNIERE COLONNE POSSIBLE
				if(chiffre+1<=n){
				int prochainchiffre=chiffre+1;
				nextcolonne=prochaincolonne(prochainchiffre,tab);//chiffre
				//Il faut sauter le chiffre ou la grille est completement placée
				while(prochainchiffre+1<=n && nextcolonne==-1){
						prochainchiffre++;
						nextcolonne=prochaincolonne(prochainchiffre,tab);
				}
				if(nextcolonne!=-1){
				placement(nextcolonne, tab, prochainchiffre);
				}

				}
				else{
					placements++;
					tableaufinal=tab;
					}
			}


		}


		}
		}
		//}


	}

	private int nextchiffre(){
		int temp=-1;

		return temp;
	}

	private int prochaincolonne(int chiffre, int[][] tab) {
		int temp=-1;

		for(int i=0;i<n;i++){
			if(cherchehb(0, i, tab,chiffre)){
				temp=i;
				break;
			}
		}

		return temp;
	}


	public int[][] clonage(int[][] tab){
		int[][] temp=new int[tab.length][tab.length];
		for(int i=0;i<tab.length;i++){
			for(int e=0;e<tab[i].length;e++){
				temp[i][e]=tab[i][e];
			}
			}
		return temp;
	}


	public boolean libre(int ligne, int colonne, int[][] tab, int chiffre){
		boolean libre=false;

				if(
						cherchedg(ligne,colonne,tab, chiffre) && cherchequadran(ligne,colonne,tab, chiffre)){
					libre=true;

				}

		return libre;


	}

	private boolean cherchequadran(int ligne, int colonne, int[][] tab, int chiffre) {
		int[] quadran=quadran(ligne,colonne);

		if(chercherchiffrequadran(chiffre, quadran, tab)){
			return true;
		}
		else{
			return false;
		}
	}



	private int[] quadran(int ligne, int colonne) {
		int[] quadran=new int[4];
		//[0]lignedebut [1]colonnedebut  [2]lignefin [3]colonnefin
		switch(ligne){
		case 0: case 1: case 2:
			switch(colonne){
			case 0: case 1: case 2:
				quadran[0]=0;
				quadran[1]=0;
				quadran[2]=2;
				quadran[3]=2;
				break;
			case 3: case 4: case 5:
				quadran[0]=0;
				quadran[1]=3;
				quadran[2]=2;
				quadran[3]=5;
				break;
			case 6: case 7: case 8:
				quadran[0]=0;
				quadran[1]=6;
				quadran[2]=2;
				quadran[3]=8;
				break;
			}
			break;
		case 3: case 4: case 5:
			switch(colonne){
			case 0: case 1: case 2:
				quadran[0]=3;
				quadran[1]=0;
				quadran[2]=5;
				quadran[3]=2;
				break;
			case 3: case 4: case 5:
				quadran[0]=3;
				quadran[1]=3;
				quadran[2]=5;
				quadran[3]=5;
				break;
			case 6: case 7: case 8:
				quadran[0]=3;
				quadran[1]=6;
				quadran[2]=5;
				quadran[3]=8;
				break;
			}
			break;
		case 6: case 7: case 8:
			switch(colonne){
			case 0: case 1: case 2:
				quadran[0]=6;
				quadran[1]=0;
				quadran[2]=8;
				quadran[3]=2;
				break;
			case 3: case 4: case 5:
				quadran[0]=6;
				quadran[1]=3;
				quadran[2]=8;
				quadran[3]=5;
				break;
			case 6: case 7: case 8:
				quadran[0]=6;
				quadran[1]=6;
				quadran[2]=8;
				quadran[3]=8;
				break;
			}
			break;

		}



		return quadran;
	}


	public boolean chercherchiffrequadran(int chiffre, int[] quadran, int[][] tab){
		boolean test=true;

		int lignedebut=quadran[0];
		int colonnedebut=quadran[1];
		int lignefin=quadran[2];
		int colonnefin=quadran[3];
		//ATTENTION IL Y A EGALITE DANS LES BOUCLES
		for(int ligne=lignedebut;ligne<=lignefin;ligne++){
			for(int colonne=colonnedebut;colonne<=colonnefin;colonne++){
				//MEME CHIFFRE DANS LE QUADRAN DONC FAUx
				if(tab[ligne][colonne]==chiffre){
					test=false;
					break;
				}
			}
		}


		return test;
	}


	private boolean cherchedg(int ligne, int colonne, int[][] tab, int chiffre) {
		boolean test=true;

		for(int index=0;index<n;index++){
			if((colonne+index)<n){

				if (tab[ligne][colonne+index]==chiffre){
					test=false;
					break;}}


			if((colonne-index)>=0){
				if(tab[ligne][colonne-index]==chiffre){
					test=false;
					break;}}
		}
		return test;
	}

	private boolean cherchehb(int ligne, int colonne, int[][] tab, int chiffre) {
		boolean test=true;


		for(int index=0;index<n;index++){
			if((ligne-index)>=0){
				if(tab[ligne-index][colonne]==chiffre){
					test=false;
					break;}}


			if((ligne+index)<n){
				if(tab[ligne+index][colonne]==chiffre){
					test=false;
					break;}}
		}
		return test;
	}



	public void afficher(int[][] tab){
		for(int i=0;i<tab.length;i++){
			for(int e=0;e<tab[i].length;e++){

					System.out.print(tab[i][e]+" ");
				}
			System.out.println();
		}
	System.out.println();
	}


	/**
	 * @return Renvoie complexite.
	 */
	public int getComplexite() {
		return complexite;
	}

	/**
	 * @return Renvoie grille.
	 */
	public int[][] getGrille() {
		return grille;
	}


	/**
	 * @param grille grille à définir.
	 */
	public void setGrille(int i, int e, int donnee) {
                 //keep a trace of placements
                //log.add(new int[]{i,e});
		grille[i][e]=donnee;
	}

    public void setLog(ArrayList log) {
        this.log = log;
    }

    public ArrayList getLog() {
        return log;
    }


    /**
     * @return Renvoie n.
     */
	public int getN() {
		return n;
	}


	/**
	 * @param n n à définir.
	 */
	public void setN(int n) {
		this.n = n;
	}


	/**
	 * @return Renvoie placements.
	 */
	public int getPlacements() {
		return placements;
	}


	/**
	 * @param placements placements à définir.
	 */
	public void setPlacements(int placements) {
		this.placements = placements;
	}




	/**
	 * @param tableaufinal tableaufinal à définir.
	 */
	public void setTableaufinal(int[][] tableaufinal) {
		this.tableaufinal = tableaufinal;
	}


	public boolean grilleremplie() {
		boolean solution=true;
		if(grille!=null){
		for(int i=0;i<grille.length;i++){
			for(int e=0;e<grille[i].length;e++){
				if(grille[i][e]==0){
					solution=false;
					break;
				}
			}
		}
		}
		else{
			solution=false;
		}
		return solution;
	}

	public boolean testervalidite(){
		boolean solution=true;

		int nbelements=0;
		for(int i=0;i<grille.length;i++){
			//ON PREND LE PREMIER ELEMENT DE CHAQUE LIGNE POUR ETRE TESTER
			//AVEC LES AUTRES ELEMENTS DE LA MEME LIGNE
			int max=grille[i][0];
			//ON COMMENCE A 1 DONC
			for(int e=1;e<grille[i].length;e++){

				if(grille[i][e]==max && max!=0){
					solution=false;
					break;
				}
				if(grille[i][e]!=0){
				nbelements++;
				}

				max=grille[i][e];
		}
		}
		//LE NB ELEMENTS NON NULS DOIT ETRE PLUS GRAND QUE 15
		if(solution && nbelements<16){
			solution=false;
		}
		return solution;
	}


	/**
	 * @param grille grille à définir.
	 */
	public void setGrille(int[][] grille) {
		this.grille = grille;
	}


	/**
	 * @return Renvoie tableaufinal.
	 */
	public int[][] getTableaufinal() {
		return tableaufinal;
	}

}
