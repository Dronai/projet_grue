package view;

import java.awt.Dimension;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import controler.Wkf_decrypt;

public class Frm_decrypt extends JFrame implements ActionListener {

	String sourcePath;
	String destinationPath;
	String key;
	
	JPanel Mainpanneau = new JPanel();
	JPanel SubPanel1 = new JPanel();
	JPanel SubPanel2 = new JPanel();
	JPanel SubPanel3 = new JPanel();
	JPanel SubPanel4 = new JPanel();
	JPanel SubPanel5 = new JPanel();
	JPanel SubPanel6 = new JPanel();
	JPanel SubPanel7 = new JPanel();
	JPanel SubPanel8 = new JPanel();
	JPanel SubPanel9 = new JPanel();
	JPanel SubPanel10 = new JPanel();
	JPanel SubPanel11 = new JPanel();
	JPanel SubPanel12 = new JPanel();
	JPanel SubPanel13 = new JPanel();
	JPanel SubPanel14 = new JPanel();
	JPanel SubPanel15 = new JPanel();
	
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
		
		BoxLayout layout = new BoxLayout(Mainpanneau, BoxLayout.X_AXIS);
		BoxLayout layoutSub1 = new BoxLayout(SubPanel4, BoxLayout.Y_AXIS);
		BoxLayout layoutSub2 = new BoxLayout(SubPanel13, BoxLayout.Y_AXIS);
		BoxLayout layoutSub3 = new BoxLayout(SubPanel8, BoxLayout.Y_AXIS);
		
		Mainpanneau.setLayout(layout);
		SubPanel4.setLayout(layoutSub1);
		SubPanel13.setLayout(layoutSub2);
		SubPanel8.setLayout(layoutSub3);
		
		
		SubPanel1.add(labelSource);
		SubPanel2.add(buttonSource);
		SubPanel3.add(pathSource);
		SubPanel4.add(SubPanel1);
		SubPanel4.add(SubPanel2);
		SubPanel4.add(SubPanel3);
		
		SubPanel5.add(labeldestination);
		SubPanel6.add(buttonDestination);
		SubPanel7.add(pathdestination);
		SubPanel8.add(SubPanel5);
		SubPanel8.add(SubPanel6);
		SubPanel8.add(SubPanel7);
		
		SubPanel9.add(keyLabel);
		SubPanel10.add(keyField);
		SubPanel11.add(buttonDecrypt);
		SubPanel12.add(labelDecryptMessage);
		SubPanel13.add(SubPanel9);
		SubPanel13.add(SubPanel10);
		SubPanel13.add(SubPanel11);
		SubPanel13.add(SubPanel12);
		
		Mainpanneau.add(SubPanel4);
		Mainpanneau.add(SubPanel13);
		Mainpanneau.add(SubPanel8);

		
		setContentPane(Mainpanneau);
		setResizable(false);
		setSize(900, 225);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == buttonSource) {
			 String filename = File.separator+"tmp";
		        JFileChooser fileChooser = new JFileChooser(new File(filename));
		        // pop up an "Open File" file chooser dialog
		        fileChooser.showOpenDialog(Mainpanneau);
		        this.sourcePath = fileChooser.getSelectedFile().getAbsolutePath();
		        this.pathSource.setText(this.sourcePath);
		        System.out.println("File to open: " + fileChooser.getSelectedFile());

		}else if(e.getSource() == buttonDestination) {
			String filename = File.separator+"tmp";
	        JFileChooser fileChooser = new JFileChooser(new File(filename));
	        // pop up an "Save File" file chooser dialog
	        fileChooser.showSaveDialog(Mainpanneau);
	        
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
				this.labelDecryptMessage.setText("Le message a était décodé avec succès ! La clé est : " + wkf_decrypt.getKey() + " Le message est : " + wkf_decrypt.getTexte());
				System.out.println("Message decoded "+  "Key : " + wkf_decrypt.getKey() + "\n Message : " + wkf_decrypt.getTexte());
			}else {
				this.labelDecryptMessage.setText("Le message n'a pas était décodé ! :(");
				System.out.println("Message not decoded");
			}
		}
	}
}
