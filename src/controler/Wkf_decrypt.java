package controler;

import java.util.ArrayList;

import modele.Decrypt;
import modele.Files;
import modele.Map_Dic;

public class Wkf_decrypt {
	
	Files files = new Files();
	Decrypt decrypt = new Decrypt();
	Map_Dic map_dic = new Map_Dic();
	String code;
	String texte;
	String knowKey;
	int knowNumberCharacter;
	String key;
	Boolean check;
	ArrayList<Character> keytab = new ArrayList<Character>();

	//Constructor
	public Wkf_decrypt(String key) {
		
		//Set the key and the array of unknown key's character
		this.knowKey = key;
		this.knowNumberCharacter = 11;
		this.key = this.knowKey;
		for(int i=0; i<(12-this.knowNumberCharacter); i++) {
			this.keytab.add('a');
		}
		for(int y =0; y<this.keytab.size(); y++) {
			this.key += this.keytab.get(y);
		}
	}
	
	/*
	 * 
	 */
	public Boolean pcs_decrypter(String sourcepath, String destination_path) {
			
			//Get data
		//this.code = this.readFiles(sourcepath);
		
		int i= 0;
		do {
				//Decrypt message
			//this.texte = decrypt.decrypt(this.code, this.key);
			
				//Choose word to check if key is correct
			//String word = decrypt.pickWord();
			
				//Check if key is correct
			this.check = map_dic.compare("Poemme");
			
			if(this.check == false) {
				this.keytab = increment(this.keytab, this.keytab.size()-1, this.keytab.get(this.keytab.size()-1));
				this.key = this.knowKey;
				for(int y =0; y<this.keytab.size(); y++) {
					this.key += this.keytab.get(y);
				}				
			}
			i++;
		}while(i < (Math.pow(26.0, (12 - this.knowNumberCharacter))) && this.check == false);
			
		if(this.check == true) {
			//Write text in new file
			//this.writeFiles(destination_path, texte);
			return true;
		}
		
		return false;
		
	}
	
	public static ArrayList<Character> increment(ArrayList<Character> tab, int k, char n) {
		if(n == 'z') {
			if(k-1 != -1) {
				increment(tab, k-1, tab.get(k-1));
			}
			tab.set(k, 'a');
		}else {
			tab.set(k, (char) (tab.get(k)+1));
		}
		
		return tab;
	}
	
	private void writeFiles(String path, String texte) {
		files.setData(path, texte);
	}
	
	private String readFiles(String path) {
		String texte = null;
		try {
			texte = files.getData(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return texte;
	}
	

}