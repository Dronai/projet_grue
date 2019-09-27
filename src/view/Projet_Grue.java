package view;

import javax.swing.JFrame;

import controler.Wkf_cpte;
import controler.Wkf_decrypt;

public class Projet_Grue {

	public static void main(String[] args) {
		
		JFrame frame = new Frm_decrypt();
		//JFrame frame = new Frm_auth();

		Boolean bool;
		Wkf_cpte cpte = new Wkf_cpte();
		
		bool =cpte.pcs_authentifier("Boutin", "Pierre");
		
		if(bool == true) {
			System.out.println("Successfull login");
		}else {
			System.out.println("Error in our login or password");
		}
		
		//--------------------------------------------------------------------------------------------------------------
		System.out.println("Hello");
		String code;
		
		Wkf_decrypt decrypt = new Wkf_decrypt("awqazertyui");
		
		Boolean decry = decrypt.pcs_decrypter("D:/Application/tex.txt", "D:/Application/pom.txt");
		
		if(decry == true) {
			System.out.println("Message decrypté !");
			System.out.println("Message :" + decrypt.getTexte());
			System.out.println("Clé :" + decrypt.getKey());
		}else {
			System.out.println("Message non décrypté");
		}
	}
}