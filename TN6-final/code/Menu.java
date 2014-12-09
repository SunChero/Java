/*
 * Modifiée par Colombiano kedowide
 *
 * TODO Pour changer le modèle de ce fichier généré, allez à :
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Administrateur
 *
 * TODO Pour changer le modèle de ce commentaire de type généré, allez à :
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */
public class Menu extends JMenuBar implements ActionListener{
    Interface map;

	JMenu m1;
	JMenu m2;
	JMenu m3;
	JMenu m4;
        JMenu m5;
        JMenu m6;
    JMenuItem m12;
    JMenuItem m121;
    JMenuItem m122;
    JMenuItem m123;
    JMenuItem m51;
    JMenuItem m61;
    
    JMenuItem m222;

    JMenuItem m331;
    JMenuItem m332;

    JMenuItem m441;
    JMenuItem m442;

	public Menu(Interface map){
        super();
        this.map=map;
		m1 = new JMenu("Nouvelle grille");
		m2 = new JMenu("Solutionner Grille");
                m5 = new JMenu("Edition");
		m3 = new JMenu("Générer une grille");
		m4 = new JMenu("Fonctions supplémentaires");
                m6 = new JMenu("Aide");
        m121 = new JMenuItem("Niveau fort");
        m122 = new JMenuItem("Niveau moyen");
        m123 = new JMenuItem("Niveau faible");
        m12 = new JMenuItem("Sélectionner dans une liste");
	m51 = new JMenuItem("Annuler Placement");
	m61 = new JMenuItem("Afficher Aide");
	m222 = new JMenuItem("Vider la Grille");    
        m121.addActionListener(this);
        m122.addActionListener(this);
        m123.addActionListener(this);
        m12.addActionListener(this);
	m51.addActionListener(this);
	m61.addActionListener(this);
        
        m222.addActionListener(this);
        m1.add(m222);
        
        m331 = new JMenuItem("Afficher la solution");
        m331.addActionListener(this);
        m2.add(m331);
        m332 = new JMenuItem("Cacher la solution");
        m332.addActionListener(this);
        m2.add(m332);
        m332.setEnabled(false);
    

        m441 = new JMenuItem("Copier grille dans le presse-papier");
        m441.addActionListener(this);
        m4.add(m441);
        m442 = new JMenuItem("Imprimer Grille+Solution");
        m442.addActionListener(this);
        m4.add(m442);
        m5.add(m51);
	m6.add(m61);
        m3.add(m121);
        m3.add(m122);
        m3.add(m123);
        m3.addSeparator();
        m3.add(m12);


        this.add(m1);
        this.add(m2);
        this.add(m3);
        this.add(m4);
        this.add(m5);
        this.add(m6);
        //PERMET DE DESACTIVER CERTAINS ELEMENTS PAR DEFAUT

	}

	  public void actionPerformed(ActionEvent evt){
			//PERMET D'eviter une ecriture de chiffre par erreur
			map.ecouteur.carre=null;
		  if(evt.getSource()==m121){
			  //map.cherchernouvellesgrilles();
			  map.supanel.selection(3);
		  }
	          else if(evt.getSource()==m51){
                                    
	               	            //map.testersinouvellegrille();
                                    ArrayList al = map.sudoku.getLog();
                                    if(al != null && al.size() > 0){
	               	            Carre temp = (Carre)al.get(al.size() -1);
                                    int i = temp.getI();
                                    int e = temp.getE();
                                    map.getSudoku().setGrille(i, e,0);
                                    map.getSudoku().afficher(map.getSudoku().getGrille());
                                    al.remove(al.size() - 1);
                                    map.sudoku.setLog(al);
                                    map.supanel.tableaudigits[i][e].setBg(new ImageIcon("images/nonselec.png").getImage());
                                    map.supanel.tableaudigits[i][e].nondispo=false;
                                    map.supanel.tableaudigits[i][e].setCoche(false);
                                    repaint();
                                   map.repaint(); 
                                    }
	             
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
	           }
		  else if(evt.getSource()==m122){
			  //map.testersinouvellegrille();
			  map.supanel.selection(2);
		  }
		  else if(evt.getSource()==m123){
			  map.supanel.selection(1);
		  }


		  else if(evt.getSource()==m222){
			 map.supanel.vidergrille();
			 // map.supanel.cherchernouvellesgrilles();
		  }


		  else if(evt.getSource()==m331){
			  if(map.supanel.solutionnergrille()){
		        m331.setEnabled(false);
		        m332.setEnabled(true);
			  }
		  }
		  else if(evt.getSource()==m332){
			  map.supanel.cachersolution();
		        m331.setEnabled(true);
		        m332.setEnabled(false);
		  }


		  //ECRITURE DANS PRESSE PAPIER
		  else if(evt.getSource()==m441){
if(map.getSudoku().getTableaufinal()!=null){
	ecriturepressepapier(map.getSudoku().getTableaufinal());
}
else{
	ecriturepressepapier(map.getSudoku().getGrille());
}
		  }
          else if(evt.getSource()==m61){
                          JOptionPane d = new JOptionPane();
                          d.showMessageDialog( map,
                                               "chaque ligne, colonne et région ne doit contenir qu’une seule fois tous les chiffres de un à neuf. \nFormulé autrement, chacun de ces ensembles doit contenir tous les chiffres de un à neuf.\n\n"+
                                              "1. les chiffes doivent etre en 1-9"+
                                              "\n2. un chiffre doit etre present une seule fois par ligne/colonne/carre 3x3","Les regles du jeu sudoku !",
                                               JOptionPane.INFORMATION_MESSAGE);
                      }
		  //IMPRESSION
		  else if(evt.getSource()==m442){
			 JFrame frame=new JFrame("Aperçu avant Impression");
			 frame.setSize(new Dimension(305,640));
			 Fond supanelreponse1=new Fond(map.map,map);
			 Fond supanelreponse2=new Fond(map.map,map);
			 supanelreponse1.impressionapartirdetableau(map.sudoku.grille);
			 if(map.supanel.solutionnergrille()){
				 System.out.println("GRILLE");
				 map.sudoku.afficher(map.sudoku.grille);
				 System.out.println("REPONSE");
				 map.sudoku.afficher(map.sudoku.tableaufinal);
				 supanelreponse2.impressionapartirdetableau(map.sudoku.tableaufinal);
					//CONTENTPANEL
					JPanel contentPanel = (JPanel)frame.getContentPane();
					contentPanel.setLayout(null);

					supanelreponse1.setBounds(0,0,300,300);
			 	contentPanel.add(supanelreponse1);

					supanelreponse2.setBounds(0,301,300,300);
			 	contentPanel.add(supanelreponse2);

			 	frame.setContentPane(contentPanel);
				 frame.setVisible(true);



				  MPanelPrinter mp=new MPanelPrinter(contentPanel);
				  mp.print();
				  map.supanel.cachersolution();
				  frame.dispose();
			 }



			  		  }



			map.requestFocus();
	               	}

public void ecriturepressepapier(int[][] tableau){
	String temp="";

	for(int i=0;i<tableau.length+2;i++){
		temp+="#";
	}
	temp+="\n";

	for(int i=0;i<tableau.length;i++){
		temp+="#";
		for(int e=0;e<tableau[i].length;e++){
			if(tableau[i][e]!=0){
			temp+=Integer.toString(tableau[i][e]);
			}
			else{
				temp+=" ";
			}
			}
		temp+="#\n";
	}

	for(int i=0;i<tableau.length+2;i++){
		temp+="#";
	}
	map.getMpp().setClipboardContents(temp);
}
}
