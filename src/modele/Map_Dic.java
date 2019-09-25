package modele;

import java.util.ArrayList;

public class Map_Dic {

	ArrayList<String> wordtab = new ArrayList<String>();
	CAD cad = new CAD();
	
	//Make map => call request. With result -> create array.
	public Map_Dic() {
		wordtab.add("Pomme");
		wordtab.add("Poire");
		wordtab.add("Fruit");
		wordtab.add("Banane");
		wordtab.add("Elephant");
		wordtab.add("Ours");
		wordtab.add("Singe");
	}
	
	public boolean compare(String word) {
		
		for(int i=0; i<wordtab.size(); i++) {
			if(wordtab.get(i) == word) {
				return true;
			}
		}
		
		return false;
	}
}
