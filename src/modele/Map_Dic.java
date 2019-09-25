package modele;

import java.util.ArrayList;

public class Map_Dic {

	ArrayList<String> wordtab = new ArrayList<String>();
	
	//Make map => call request. With result -> create array.
	public Map_Dic() {
		wordtab.add("j'aimes");
		wordtab.add("poire et chocolat");
		wordtab.add("j'en mangé des pömmes");
		wordtab.add("banane");
		wordtab.add("elephant");
		wordtab.add("ours");
		wordtab.add("J'aime manger les pommes");
		wordtab.add("manger");
	}
	
	public boolean compare(String word) {
				
		for(int i=0; i<wordtab.size(); i++) {
			if(wordtab.get(i).equals(word)) {
				return true;
			}
		}
		return false;
	}
}
