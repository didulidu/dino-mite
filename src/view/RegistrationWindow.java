package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.Aplikacija;
import model.Korisnik;
import model.Osoba;

public class RegistrationWindow extends JFrame {
	private int genID = 0;
	private String _username;
	private String _password;
	private String _jmbg;
	private String _street;
	private String _name;
	private String _lName;
	private String _email;
	private java.util.Date _birth = new java.util.Date();

	private SimpleDateFormat sdf;

	public RegistrationWindow() {
		sdf = new SimpleDateFormat("dd.MM.yyyy.");
		for (Korisnik k: Aplikacija.korisnici.values()){
			if (genID < Integer.parseInt(k.getOsoba().getBrojRacuna())){
				genID = Integer.parseInt(k.getOsoba().getBrojRacuna());
			}
		}
		initUI();
	}

	private Korisnik checkRegistration() {
		if (_username.isEmpty() || _password.isEmpty() || _name.isEmpty() || _lName.isEmpty() || _street.isEmpty()
				|| _jmbg.isEmpty() || _email.isEmpty()) {
			return null;
		}

		for (Korisnik k : Aplikacija.korisnici.values()) {
			if (k.getKorisnickoIme().compareTo(_username) == 0) {
				return null;
			}
		}
		Korisnik kori = new Korisnik(_username, _password, new Osoba(_name, _lName, _jmbg, _street, _birth, genID+1+"", 0, _email), true);
		genID+=1;
		Aplikacija.korisnici.put(_username, kori);
		return kori;
	}

	private void initUI() {
		setLayout(null);
		JButton ok = new JButton("<html><font color='green'>OK </font></html>");
		ok.setBounds(50, 460, 150, 30);
		JButton cancel = new JButton("<html><font color='red'>Cancel</font></html>");
		cancel.setBounds(250, 460, 150, 30);

		JLabel name = new JLabel("Name: ");
		JLabel lName = new JLabel("Last name: ");
		JLabel jmbg = new JLabel("JMBG: ");
		JLabel street = new JLabel("Adress: ");
		JLabel email = new JLabel("E-mail: ");
		JLabel birth = new JLabel("Birth: ");

		name.setFont(new Font("ss", Font.LAYOUT_LEFT_TO_RIGHT, 20));
		lName.setFont(name.getFont());
		jmbg.setFont(name.getFont());
		street.setFont(name.getFont());
		email.setFont(name.getFont());
		birth.setFont(name.getFont());
		name.setBounds(50, 20, 150, 25);
		lName.setBounds(50, 70, 150, 25);
		jmbg.setBounds(50, 120, 150, 25);
		street.setBounds(50, 170, 150, 25);
		email.setBounds(50, 220, 150, 25);
		birth.setBounds(50, 270, 150, 25);
		JTextField nameT = new JTextField("");
		JTextField lNameT = new JTextField("");
		JTextField jmbgT = new JTextField("");
		JTextField streetT = new JTextField("");
		JTextField emailT = new JTextField("");
		JTextField birthT = new JTextField("");
		nameT.setBounds(150, 20, 200, 30);
		lNameT.setBounds(150, 70, 200, 30);
		jmbgT.setBounds(150, 120, 200, 30);
		streetT.setBounds(150, 170, 200, 30);
		emailT.setBounds(150, 220, 200, 30);
		birthT.setBounds(150, 270, 200, 30);

		JLabel user = new JLabel("Username: ");
		JLabel pass = new JLabel("Password: ");
		user.setBounds(50, 320, 150, 30);
		pass.setBounds(50, 370, 150, 30);
		user.setFont(name.getFont());
		pass.setFont(name.getFont());

		JTextField userT = new JTextField();
		userT.setBounds(150, 320, 200, 30);
		JPasswordField passT = new JPasswordField();
		passT.setBounds(150, 370, 200, 30);
		JLabel dateErr = new JLabel("<html><font color='red'>Oops! Wrong date format</font></html>");
		dateErr.setBounds(150, 420, 250, 60);
		dateErr.setVisible(false);
		JLabel errT = new JLabel("<html><font color='red'>*Missing data or username taken </font></html>");
		errT.setBounds(150, 390, 250, 60);
		errT.setVisible(false);
		setSize(new Dimension(600, 600));
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
		add(email);
		add(emailT);
		add(birth);
		add(birthT);
		add(dateErr);
		getContentPane().setBackground(new Color(40, 200, 76));

		setVisible(true);
		setTitle("Log in to DinoMite!");
		ImageIcon ikonica = new ImageIcon("ikonice/mite.png");
		setIconImage(ikonica.getImage());
		setLocationRelativeTo(null);
		// na klik cancel dugmeta :
		cancel.addActionListener((ActionEvent event) -> {
			dispose();
		});

		// na klik ok dugmeta:
		ok.addActionListener((ActionEvent event) -> {
			_username = userT.getText();
			_password = passT.getText();
			_name = nameT.getText();
			_lName = lNameT.getText();
			_street = streetT.getText();
			_jmbg = jmbgT.getText();
			_email = emailT.getText();
			boolean okk = false;
			try {
				_birth = sdf.parse(birthT.getText());
				okk= true;
				System.out.println(_birth);

				System.out.println(_username + _password + _name + _lName + _street + _jmbg + _email);

				Korisnik ko = checkRegistration();
				if (ko == null) {
					System.out.println("invalid username ili nedostaje nesto");
					errT.setVisible(true);
					userT.setText("");
				} else {
					if (!okk) {
						dateErr.setVisible(true);
						birthT.setText("");
					} else {
						dateErr.setVisible(false);
						errT.setVisible(false);
						System.out.println("Uspela registracija");
						dispose();
					}
				}
			} catch (Exception e) {
				okk = false;
				System.out.println("ovde je ipak...");
				dateErr.setVisible(true);
			}
		});

	}

}
 