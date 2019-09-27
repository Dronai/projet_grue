package view;

import java.awt.Dimension;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import controler.Wkf_decrypt;

public class Frm_decrypt extends JFrame implements ActionListener {

	JPanel panneau = new JPanel();
	JLabel labelSource = new JLabel("Selectionner le fichier texte source");
	JLabel labeldestination = new JLabel("Selectionner le fichier texte de destination");	
	JLabel pathSource = new JLabel();
	JLabel pathdestination = new JLabel();
	JButton buttonSource = new JButton("Choose");
	JButton buttonDestination = new JButton("Create");
	JLabel keyLabel = new JLabel("Clé (Ou partie de clé connue)");
	JTextField keyField = new JTextField();
	JButton buttonDecrypt = new JButton("Decrypter");
	JLabel labelDecryptMessage = new JLabel();
	
	Wkf_decrypt wkf_decrypt = new Wkf_decrypt("awqazertyui");
	String sourcePath;
	String destinationPath;
	String key;
	
	public Frm_decrypt() {
		super("Application de décryptage");
		
		WindowListener l = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		addWindowListener(l);
		
		buttonSource.addActionListener(this);
		buttonDestination.addActionListener(this);
		buttonDecrypt.addActionListener(this);
		keyField.setPreferredSize(new Dimension(100, 20));
		panneau.add(labelSource);
		panneau.add(buttonSource);
		panneau.add(pathSource);
		panneau.add(labeldestination);
		panneau.add(buttonDestination);
		panneau.add(pathdestination);
		panneau.add(keyLabel);
		panneau.add(keyField);
		panneau.add(buttonDecrypt);
		panneau.add(labelDecryptMessage);
		setContentPane(panneau);
		setSize(700, 500);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == buttonSource) {
			 String filename = File.separator+"tmp";
		        JFileChooser fileChooser = new JFileChooser(new File(filename));
		        // pop up an "Open File" file chooser dialog
		        fileChooser.showOpenDialog(panneau);
		        this.sourcePath = fileChooser.getSelectedFile().getAbsolutePath();
		        this.pathSource.setText(this.sourcePath);
		        System.out.println("File to open: " + fileChooser.getSelectedFile());

		}else if(e.getSource() == buttonDestination) {
			String filename = File.separator+"tmp";
	        JFileChooser fileChooser = new JFileChooser(new File(filename));
	        // pop up an "Save File" file chooser dialog
	        fileChooser.showSaveDialog(panneau);
	        
	        File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");
	        try {
				if(file.createNewFile()) {
			        this.destinationPath = fileChooser.getSelectedFile().getAbsolutePath() + ".txt";
			        this.pathdestination.setText(this.destinationPath);
					System.out.println("File create : " + fileChooser.getSelectedFile().getAbsolutePath() + ".txt");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
				System.out.println("Fail to create new file");
			}
		}else if(e.getSource() == buttonDecrypt) {
			this.key = this.keyField.getText();
			Wkf_decrypt wkf_decrypt = new Wkf_decrypt(this.key);

			if(wkf_decrypt.pcs_decrypter(this.sourcePath, this.destinationPath)) {
				this.labelDecryptMessage.setText("Le message a était décodé avec succès ! \n La clé est : " + wkf_decrypt.getKey() + "\n Le message est : " + wkf_decrypt.getTexte());
			}else {
				this.labelDecryptMessage.setText("Le message n'a pas était décodé ! :(");
			}
		}
	}
}
