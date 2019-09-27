package view;

import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;

import controler.Wkf_cpte;

public class Frm_auth extends JFrame implements ActionListener{

	private String login;
	private String password;
	private boolean verify;
	
	Wkf_cpte cpte = new Wkf_cpte();
	
	JPanel panneau = new JPanel();
	JLabel labelLogin = new JLabel("Login");
	JLabel labelPassword = new JLabel("Password");
	JLabel labelError = new JLabel("");
	JTextField loginField = new JTextField();
	JTextField passwordField = new JTextField();
	JButton button = new JButton("C'est ok");

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
		
		panneau.add(labelLogin);
		panneau.add(loginField);
		panneau.add(labelPassword);
		panneau.add(passwordField);
		panneau.add(button);
		panneau.add(labelError);
		setContentPane(panneau);
		setSize(700, 500);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.login = this.loginField.getText();
		this.password = this.passwordField.getText();
		
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
