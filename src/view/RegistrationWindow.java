package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
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
		if (_username.isEmpty() || _password.isEmpty() || _name.isEmpty() || _lName.isEmpty() || _street.isEmpty() || _jmbg.isEmpty()  ) return null;
		for (Korisnik k: Aplikacija.korisnici.values()){
			if  (k.getKorisnickoIme().compareTo(_username) == 0)
				return null;
		}
		Aplikacija.korisnici.put(_username, new Korisnik(_username,_password,new Osoba(_name, _lName, _jmbg, _street),true));
		
		return new Korisnik();
	}
	
	private void initUI(){
		setLayout(null);
		JButton ok = new JButton("<html><font color='green'>OK </font></html>");
		ok.setBounds(50, 350 , 150, 30);
		JButton cancel = new JButton("<html><font color='red'>Cancel</font></html>");
		cancel.setBounds(250, 350, 150, 30);
		
		JLabel name = new JLabel("Name: ");
		JLabel lName = new JLabel("Last name: ");
		JLabel jmbg = new JLabel("JMBG: ");
		JLabel street = new JLabel("Adress: ");
		name.setFont(new Font("ss", Font.LAYOUT_LEFT_TO_RIGHT, 20));
		lName.setFont(name.getFont());
		jmbg.setFont(name.getFont());
		street.setFont(name.getFont());
		
		name.setBounds(50, 20 , 150, 25);
		lName.setBounds(50, 70 , 150, 25);
		jmbg.setBounds(50, 120 , 150, 25);
		street.setBounds(50, 180 , 150, 25);
		
		JTextField nameT = new JTextField("");
		JTextField lNameT = new JTextField("");
		JTextField jmbgT= new JTextField("");
		JTextField streetT = new JTextField("");
		nameT.setBounds(150, 20 , 200, 30);
		lNameT.setBounds(150, 70 , 200, 30);
		jmbgT.setBounds(150, 120 , 200, 30);
		streetT.setBounds(150, 180 , 200, 30);
	
		JLabel user = new JLabel("Username: ");
		JLabel pass = new JLabel("Password: ");
		user.setBounds(50, 230 , 150, 30);
		pass.setBounds(50, 280 , 150, 30);
		user.setFont(name.getFont());
		pass.setFont(name.getFont());
		
		JTextField userT= new JTextField();
		userT.setBounds(150,230, 200,30);
		JPasswordField passT= new JPasswordField();
		passT.setBounds(150,280, 200,30);
		
		JLabel errT= new JLabel("<html><font color='red'>*Missing data or username taken </font></html>");
		errT.setBounds(150,290, 250,60);
		errT.setVisible(false);
		setSize(new Dimension(450,450));
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
		getContentPane().setBackground(new Color(40, 200, 76));

		setVisible(true);
		setTitle("Log in to DinoMite!");
		ImageIcon ikonica = new ImageIcon("ikonice/mite.png");
		setIconImage(ikonica.getImage());
		setLocationRelativeTo(null);
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
