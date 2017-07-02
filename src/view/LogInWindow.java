package view;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Aplikacija;
import model.Korisnik;
import model.Main;

public class LogInWindow extends JFrame{

	private String username;
	private String password;
	
	public LogInWindow() {
		initUI();
	}
	
	private Korisnik checkLogIn(){
		for (Korisnik k: Aplikacija.korisnici.values()){
			if  (k.getKorisnickoIme().compareTo(username) == 0 && k.getLozinka().compareTo(password) == 0)
				return k;
		}
		return null;
	}
	
	private void initUI(){
		setLayout(null);
		
		JButton ok = new JButton("Ok");
		ok.setBounds(200-40, 300-50 , 80, 25);
		JButton cancel = new JButton("Cancel");
		cancel.setBounds(250, 250, 80, 25);
		
		JLabel user = new JLabel("Username: ");
		JLabel pass = new JLabel("Password: ");
		
		user.setBounds(100, 200-50 , 80, 25);
		pass.setBounds(100, 200 , 80, 25);
		JTextField userT= new JTextField();
		userT.setBounds(200,150, 100,25);
		JTextField passT= new JTextField();
		passT.setBounds(200,200, 100,25);
		
		JLabel errT= new JLabel("<html><font color='red'>Error: Incorrect username and/or passwort.Please try again! </font></html>");
		errT.setBounds(150,300, 150,60);
		errT.setVisible(false);
		setSize(new Dimension(400,300));
		setResizable(false);
		add(cancel);
		add(ok);
		add(user);
		add(pass);
		add(userT);
		add(passT);
		add(errT);
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("Log in to DinoMite!");
		ImageIcon ikonica = new ImageIcon("./src/slike/mite.png");
		setIconImage(ikonica.getImage());
		
		// na klik cancel dugmeta :
		cancel.addActionListener( (ActionEvent event) -> {
            dispose();
        });
		
		//na klik ok dugmeta:
		ok.addActionListener((ActionEvent event) ->{
			username = userT.getText();
			password = passT.getText();
			Korisnik k = checkLogIn();
			if (k == null){
				errT.setVisible(true);
				userT.setText("");
				passT.setText("");
			}
			else{
				errT.setVisible(false);
				dispose();
			}
		});
	
		
	}
	
	
}
