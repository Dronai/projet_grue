package controler;

import java.util.ArrayList;

import modele.CAD;
import modele.Decrypt;
import modele.Files;
import modele.Map_Dic;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Wkf_decrypt {

	Files files = new Files();
	Decrypt decrypt = new Decrypt();
	Map_Dic map_dic = new Map_Dic();
	CAD cad = new CAD("jdbc:mysql://localhost:3306/projet_grue?useSSL=false", "root", "");
	String code;
	String[] word;
	String texte;
	String knowKey;
	int knowNumberCharacter;
	String key;
	Boolean check;
	ArrayList<Character> keytab = new ArrayList<Character>();

	// Constructor
	public Wkf_decrypt(String key) {

		// Set the key and the array of unknown key's character
		this.knowKey = key;
		this.knowNumberCharacter = this.knowKey.length();
		this.code = "";
		this.texte = "";
		this.key = this.knowKey;
		for (int i = 0; i < (12 - this.knowNumberCharacter); i++) {
			this.keytab.add('a');
		}
		for (int y = 0; y < this.keytab.size(); y++) {
			this.key += this.keytab.get(y);
		}
		
		System.out.println("Initial key : " + this.key);
	}

	/*
	 * Function for test the key and decrypt the code
	 */
	public Boolean pcs_decrypter(String sourcepath, String destination_path) {

		// Get data
		this.code = this.readFiles(sourcepath);
		//set the dictionary of word
		map_dic.setWordtab(this.tabWord());

		int i = 0;
		do {
			// Decrypt message
			this.texte = decrypt.xor_encodeToString(this.code, this.key);
			this.texte = this.texte.toLowerCase();
			
			// Choose word to check if key is correct
			word = decrypt.getListOfUniqueWord(this.texte);

			// Check if key is correct. Skip the word with accent
			this.check = map_dic.compare(this.word[0]);
			
			//If word doesn't matches, increment key
			if (this.check == false) {
				this.keytab = increment(this.keytab, this.keytab.size() - 1, this.keytab.get(this.keytab.size() - 1));
				this.key = this.knowKey;
				for (int y = 0; y < this.keytab.size(); y++) {
					this.key += this.keytab.get(y);
				}
			}
			i++;
			System.out.println("Iteration: " + i + ", key tested : " + this.key + ", text found : " + this.texte);
		} while (i < (Math.pow(26.0, (12 - this.knowNumberCharacter))) && this.check == false);

		//If matches : write the message in file.
		if (this.check == true) {
			System.out.println("Message found !");
			// Write text in new file
			this.writeFiles(destination_path, this.texte);
			System.out.println("Texte write in file : " + destination_path);
			return true;
		}

		System.out.println("Message not found");
		return false;

	}

	/*
	 * Function to increment the key
	 */
	public static ArrayList<Character> increment(ArrayList<Character> tab, int k, char n) {
		if (n == 'z') {
			if (k - 1 != -1) {
				increment(tab, k - 1, tab.get(k - 1));
			}
			tab.set(k, 'a');
		} else {
			tab.set(k, (char) (tab.get(k) + 1));
		}

		return tab;
	}

	/*
	 * Call function to write a text in a file
	 */
	private void writeFiles(String path, String texte) {
		files.setData(path, texte);
	}

	/*
	 * Call function to read a file
	 */
	private String readFiles(String path) {
		String texte = null;
		try {
			texte = files.getData(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return texte;
	}
	
	public ArrayList<String> tabWord() {
		ArrayList<String> tab = new ArrayList<String>();
		String rq_sql;
		ResultSet result;
		
		rq_sql = map_dic.selectWord();
		result = cad.GetRows(rq_sql);
		
		try {
			while (result.next()) {
				tab.add(result.getString("word"));
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tab;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}