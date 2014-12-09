/*
 * Modifié par Colombiano Kedowide
 *
 * TODO Pour changer le modèle de ce fichier généré, allez à :
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.StringTokenizer;

import javax.swing.JFrame;

/**
 * @author Administrateur
 *
 * TODO Pour changer le modèle de ce commentaire de type généré, allez à :
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */
public class FileLogger {
	//Declaration des objets lecture/écriture
	JFrame frame;
	File rep;
	File copy;
	String nomfichier;
	boolean erreur=false;
	FileWriter out=null;
	BufferedWriter f;
	static String temp="";
	RandomAccessFile random;





	public BufferedWriter getF() {
		return f;
	}



	/**
	 * @return Renvoie actif.
	 */
	public FileLogger(JFrame frame, String nomfichier){
		this.frame=frame;
		this.nomfichier=nomfichier;
		repertoire();
	}

	public void repertoire(){
		try{
		//ICI CREATION DES REPERTOIRES
		rep=new File(System.getProperty("user.dir")+"//"+nomfichier);
		copy=new File(System.getProperty("user.dir")+"//"+"copy");
	        out=new FileWriter(rep,true);
			f = new BufferedWriter(out);

		}
		catch(IOException e){}
		}


	public void delete(String lineasupprimer){
			try {


			BufferedReader reader = new BufferedReader(new FileReader(rep));
					String line = "", oldtext = "";
					while((line = reader.readLine()) != null) {
						if(!line.equals(lineasupprimer)){
					   oldtext += line + "\r\n";
						}
					}
					reader.close();

					FileWriter writer = new FileWriter(rep);
					writer.write(oldtext);
					writer.close();
					reader.close();
					writer.close();



			} catch (IOException e) {
				// TODO Bloc catch auto-généré
				e.printStackTrace();
			}
	}

	public int nbdelignes() throws IOException{
		int compte=0;
		BufferedReader reader = new BufferedReader(new FileReader(rep));
		String line = reader.readLine();
		while (line != null) {
			compte++;
			line = reader.readLine();
	}
		reader.close();
		return compte;
	}


	public String[] reader() throws IOException{
		String[] temp=new String[nbdelignes()];

		BufferedReader reader = new BufferedReader(new FileReader(rep));
		String line = reader.readLine();
		int index=0;
		while (line != null) {

			temp[index]=line;
			index++;
			line = reader.readLine();
	}
		reader.close();
	return temp;
	}


	public void log(String message) {
		try{
				String text =message;
		    	f.write(text);
		    	f.newLine();
	    	f.flush();
		}
		catch(IOException e){}
	}



	public void fermer(){
		try{
		f.close();
		out.close();
		}
		catch(IOException e){
			erreur=true;
		}
	}

	/**
	 * @return Renvoie erreur.
	 */
	public boolean isErreur() {
		return erreur;
	}
	/**
	 * @param erreur erreur à définir.
	 */
	public void setErreur(boolean erreur) {
		this.erreur = erreur;
	}
}
