package modele;

import java.util.ArrayList;

public class Map_Dic {

	ArrayList<String> wordtab = new ArrayList<String>();
	String rq_sql;
	
	//Make map => call request. With result -> create array.
	public Map_Dic() {
	}

	public String selectWord( ) {
		rq_sql = "SELECT * FROM tb_word";
		return rq_sql;
	}
	
	public boolean compare(String word) {
		System.out.println("Word tested : " + word);
		for(int i=0; i<wordtab.size(); i++) {
			if(wordtab.get(i).equals(word)) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<String> getWordtab() {
		return wordtab;
	}

	public void setWordtab(ArrayList<String> wordtab) {
		this.wordtab = wordtab;
	}

	public String getRq_sql() {
		return rq_sql;
	}

	public void setRq_sql(String rq_sql) {
		this.rq_sql = rq_sql;
	}
}
