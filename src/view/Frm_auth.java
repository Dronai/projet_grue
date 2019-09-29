package view;

import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;

import controler.Wkf_cpte;

public class Frm_auth extends JFrame implements ActionListener{

	private String login;
	private char[] password;
	private boolean verify;
	
	Wkf_cpte cpte = new Wkf_cpte();
	
	JPanel MainPanneau = new JPanel();//main panel
	JPanel SubPanneauErr = new JPanel();//panel for the error msg
	JPanel SubPanneau1 = new JPanel();//panel for the login text
	JPanel SubPanneau11 = new JPanel();//panel for the login textField
	JPanel SubPanneau2 = new JPanel();//panel for the password text
	JPanel SubPanneau22 = new JPanel();//panel for the password textField
	JPanel SubPanneau3 = new JPanel();//pannel for the button
	JPanel EmptyPanel = new JPanel();//empty panel for compensing
	JLabel labelLogin = new JLabel("Login");
	JLabel labelPassword = new JLabel("Password");
	JLabel labelError = new JLabel("");
	JTextField loginField = new JTextField();
	JPasswordField passwordField = new JPasswordField();
	JButton button = new JButton("Connexion");

	public Frm_auth() {
		super("Application de décryptage");
		
		WindowListener l = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		addWindowListener(l);
		
		
		loginField.setPreferredSize(new Dimension(100, 20));
		passwordField.setPreferredSize(new Dimension(100, 20));
		button.addActionListener(this);
		
		BoxLayout layout = new BoxLayout(MainPanneau, BoxLayout.Y_AXIS);
		
		MainPanneau.setLayout(layout);
		SubPanneau1.add(labelLogin);
		SubPanneau11.add(loginField);
		SubPanneau2.add(labelPassword);
		SubPanneau22.add(passwordField);
		SubPanneau3.add(button);
		SubPanneauErr.add(labelError);
		
		SubPanneau1.setPreferredSize(new Dimension(110, 30));
		SubPanneau11.setPreferredSize(new Dimension(110, 30));
		SubPanneau2.setPreferredSize(new Dimension(110, 30));
		SubPanneau22.setPreferredSize(new Dimension(110, 30));
		
		MainPanneau.add(SubPanneauErr);
		MainPanneau.add(SubPanneau1);
		MainPanneau.add(SubPanneau11);
		MainPanneau.add(SubPanneau2);
		MainPanneau.add(SubPanneau22);
		MainPanneau.add(SubPanneau3);
		
		
		setResizable(false);
		setContentPane(MainPanneau);
		setSize(300, 250);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.login = this.loginField.getText();
		this.password = this.passwordField.getPassword();
		
		this.verify = cpte.pcs_authentifier(this.login, this.password);
		
		if(this.verify == true) {
			this.labelError.setText("Succefull login");
			this.setVisible(false);
			Frm_decrypt frm_decrypt = new Frm_decrypt();
			System.out.println("Successful login");
		}else {
			this.labelError.setText("Le login ou password n'est pas correct !");
			System.out.println("Connection failed");
		}		
	}
}
