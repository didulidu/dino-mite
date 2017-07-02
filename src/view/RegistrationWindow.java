package view;

import java.awt.Color;
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
import model.Osoba;

public class RegistrationWindow extends JFrame{

	private String _username;
	private String _password;
	private String _jmbg;
	private String _street;
	private String _name;
	private String _lName;
	
	
	public RegistrationWindow() {
		initUI();
	}
	
	private Korisnik checkRegistration(){
		for (Korisnik k: Aplikacija.korisnici.values()){
			if  (k.getKorisnickoIme().compareTo(_username) == 0)
				return null;
		}
		Aplikacija.korisnici.put(_username, new Korisnik(_username,_password,new Osoba(_name, _lName, _jmbg, _street),true));
		
		return new Korisnik();
	}
	
	private void initUI(){
		setLayout(null);
		JButton ok = new JButton("Ok");
		ok.setBounds(200-40, 300-50 , 80, 25);
		JButton cancel = new JButton("Cancel");
		cancel.setBounds(250, 250, 80, 25);
		
		JLabel name = new JLabel("Name: ");
		JLabel lName = new JLabel("Last name: ");
		JLabel jmbg = new JLabel("JMBG: ");
		JLabel street = new JLabel("Adress: ");
		name.setBounds(100, 50 , 80, 25);
		lName.setBounds(100, 75 , 80, 25);
		jmbg.setBounds(100, 100 , 80, 25);
		street.setBounds(100, 125 , 80, 25);
		
		JTextField nameT = new JTextField("*name*");
		JTextField lNameT = new JTextField("lastname*");
		JTextField jmbgT= new JTextField("*jmbg*");
		JTextField streetT = new JTextField("*adress*");
		nameT.setBounds(200, 50 , 80, 25);
		lNameT.setBounds(200, 75 , 80, 25);
		jmbgT.setBounds(200, 100 , 80, 25);
		streetT.setBounds(200, 125 , 80, 25);
	
		JLabel user = new JLabel("Username: ");
		JLabel pass = new JLabel("Password: ");
		user.setBounds(100, 150 , 80, 25);
		pass.setBounds(100, 175 , 80, 25);
		JTextField userT= new JTextField();
		userT.setBounds(200,150, 100,25);
		JTextField passT= new JTextField();
		passT.setBounds(200,175, 100,25);
		
		JLabel errT= new JLabel("<html><font color='red'>Error: Username taken.Please try again! </font></html>");
		errT.setBounds(150,200, 150,60);
		errT.setVisible(false);
		setSize(new Dimension(400,400));
		setResizable(false);
		add(cancel);
		add(ok);
		add(user);
		add(pass);
		add(userT);
		add(passT);
		add(errT);
		add(name);
		add(lName);
		add(street);
		add(jmbg);
		add(nameT);
		add(lNameT);
		add(streetT);
		add(jmbgT);
		
		setVisible(true);
		setTitle("Log in to DinoMite!");
		ImageIcon ikonica = new ImageIcon("ikonice/mite.png");
		setIconImage(ikonica.getImage());
		
		// na klik cancel dugmeta :
		cancel.addActionListener( (ActionEvent event) -> {
            dispose();
        });
		
		//na klik ok dugmeta:
		ok.addActionListener((ActionEvent event) ->{
			_username = userT.getText();
			_password = passT.getText();
			_name = name.getText();
			_lName = lName.getText();
			_street = street.getText();
			_jmbg = jmbg.getText();
			
			Korisnik k = checkRegistration();
			if (k == null){
				errT.setVisible(true);
				userT.setText("");	
			}
			else{
				errT.setVisible(false);
				System.out.println("Uspela registracija");
				dispose();
			}
		});
		
		
	}
	
	
}
