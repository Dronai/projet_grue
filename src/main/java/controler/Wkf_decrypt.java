package controler;

import modele.Files;

public class Wkf_decrypt {
	
	Files files = new Files();
	
	public void writeFiles(String path, String texte) {
		files.setData(path, texte);
	}
	
	public String readFiles(String path) {
		String texte = null;
		try {
			texte = files.getData(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return texte;
	}
}
