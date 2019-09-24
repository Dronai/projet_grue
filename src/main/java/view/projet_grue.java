package view;

import controler.Wkf_decrypt;

public class projet_grue {

	public static void main(String[] args) {
		
		System.out.println("Hello");
		Wkf_decrypt wkf_decrypt = new Wkf_decrypt();
		wkf_decrypt.writeFiles("D:/Application/tex.txt", "J'aime regarder les filles qui marchent sur la plage");
		
		String texte;
		texte = wkf_decrypt.readFiles("D:/Application/tex.txt");
		System.out.println(texte);
	}

}
