package modele;

import java.io.FileReader;
import java.io.FileWriter;

public class Files {

	public Files() {
		
	}
	
	/*
	 * read a file
	 */
	public String getData(String path) throws Exception {
		
		FileReader fr = new FileReader(path);
		String texte = "";
		
		int i;
		while((i=fr.read())!=-1) {
			texte += (char)i;
		}
		fr.close();
		return texte;
	}
	
	/*
	 * Write in a file
	 */
	public void setData(String path, String texte) {
		try {
			FileWriter fw = new FileWriter(path);
			fw.write(texte);
			fw.close();
		}catch (Exception e) {
			System.out.println(e);
		}
		
		System.out.println("Success");
	}
}