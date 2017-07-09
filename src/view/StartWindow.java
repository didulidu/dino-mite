package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import model.Aplikacija;
import model.Izbrisan;
import model.Korisnik;
import model.Obilazak;

public class StartWindow extends JFrame implements ItemListener {
	// Atributi
	private JLabel display;
	private JComboBox<String> box;
	private String[] cities;
	private Object[][] podaci = new Object[][] {};
	private JTable tabela;
	private String[] ime = { "<html><font color='red'>Tours: </font></html>" };
	private DefaultTableModel model;
	private String username;
	private String password;
	private RegistrationWindow regWin;
	private UserWindow uw;
	private JButton obrisiNalog = new JButton("Delete acc");

	// Proverava ispravnost unetih podataka pri logovanju
	private Korisnik checkLogIn() {
		for (Korisnik k : Aplikacija.korisnici.values()) {
			if (k.getKorisnickoIme().compareTo(username) == 0 && k.getLozinka().compareTo(password) == 0 && k.isRegistrovan())
				return k;
		}
		return null;
	}

	// Popunjavanje Combo Boxa Gradovima koji postoje
	private void fillComboBox() {
		box = new JComboBox<>();
		box.addItem("");
		for (String g : Aplikacija.gradovi.keySet())
			box.addItem(g);
	}

	// Popunjavanje Tabele Obilascima selektovanog grada
	private void fillTable(String city) {
		model.setRowCount(0);

		for (Obilazak o : Aplikacija.sviObilasci.values()) {
			if (o.getGrad().getNaziv().compareTo(city) == 0) {
				model.addRow(new Object[] { o.getNaziv() });
			}
		}
	}

	// Konstruktor
	public StartWindow() {
		fillComboBox();
		initUI();
	}

	// Prozorche
	private void initUI() {
		setLayout(null);

		display = new JLabel("City: ");
		JLabel naslov = new JLabel(new ImageIcon("./src/slike/naslov.png"));
		naslov.setBounds(140, 10, 677, 98);
		add(naslov);
		getContentPane().setBackground(new Color(0, 153, 76));
		setSize(1000, 800);
		display.setBounds(40, 200, 150, 25);
		display.setFont(new Font("fontic", Font.BOLD, 20));
		box.setBounds(100, 200, 100, 25);
		add(display);
		add(box);
		box.addItemListener(this);
		setTitle("DinoMite");
		ImageIcon image = new ImageIcon("./src/slike/dinamit.png");
		setIconImage(image.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addWindowListener(new WindowEventHandler());
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		setLocationRelativeTo(null);
		setResizable(false);

		model = new DefaultTableModel(podaci, ime) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tabela = new JTable(model);
		tabela.setFont(new Font("f", Font.BOLD, 30));
		tabela.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 40));

		tabela.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();
					// do some action if appropriate column
					String x = (String) tabela.getModel().getValueAt(row, column);
					System.out.println(x);
					for (Obilazak k : Aplikacija.sviObilasci.values()) {
						if (x.compareTo(k.getNaziv()) == 0) {
							TourWindow t = new TourWindow(k.getIdOb());
							t.setVisible(true);
						}
					}
				}
			}
		});

		tabela.setRowHeight(50);
		JScrollPane galerija = new JScrollPane(tabela);
		galerija.setBounds(40, 300, 500, 400);
		add(galerija);
		// logIn deo
		JButton ok = new JButton("<html><font color='green'>OK </font></html>");
		ok.setBounds(730, 410, 100, 30);
		JButton cancel = new JButton("<html><font color='red'>Cancel </font></html>");
		cancel.setBounds(850, 410, 100, 30);
		JLabel user = new JLabel("*Username: ");
		JLabel pass = new JLabel("*Password: ");
		user.setFont(new Font("f", Font.BOLD, 24));
		pass.setFont(user.getFont());
		user.setBounds(600, 300, 200, 40);
		pass.setBounds(600, 350, 200, 40);
		JTextField userT = new JTextField();
		userT.setBounds(750, 310, 200, 25);
		JPasswordField passT = new JPasswordField();
		passT.setBounds(750, 360, 200, 25);
		JLabel errT = new JLabel(
				"<html><font color='red'>*Incorrect username and/or passwort.Please try again! </font></html>");
		errT.setFont(new Font("error", Font.CENTER_BASELINE, 20));
		errT.setBounds(600, 250, 350, 50);
		errT.setVisible(false);
		JButton regB = new JButton("<html><font color='blue'>Register </font></html>");
		JLabel regL = new JLabel("<html><font color='white'>Don't have an account? Register for FREE </font></html>");
		JButton logOut = new JButton("<html><font color='blue'>Log out</font></html>");
		logOut.setVisible(false);
		logOut.setBounds(850, 480, 100, 30);
		obrisiNalog.setVisible(false);
		obrisiNalog.setBounds(850, 520, 100, 30);
		
		JButton seeAcc = new JButton("<html><font color='green'>Account</font></html>");
		seeAcc.setBounds(730, 480, 100, 30);
		seeAcc.setVisible(false);
		seeAcc.addActionListener((ActionEvent event) ->{
			uw.setVisible(true);
		});
		
		logOut.addActionListener((ActionEvent event) -> {
			Aplikacija.trenutni = null;
			uw.dispose();
			userT.setVisible(true);
			passT.setVisible(true);
			user.setVisible(true);
			pass.setVisible(true);
			regB.setVisible(true);
			regL.setVisible(true);
			ok.setVisible(true);
			cancel.setVisible(true);
			logOut.setVisible(false);
			seeAcc.setVisible(false);
			passT.setText("");
			userT.setText("");
		});
		add(seeAcc);
		add(logOut);
		add(cancel);
		add(ok);
		add(user);
		add(pass);
		add(userT);
		add(passT);
		add(errT);
		add(obrisiNalog);
		// na klik cancel dugmeta :
		cancel.addActionListener((ActionEvent event) -> {
			dispose();
		});

		// na klik ok dugmeta:
		ok.addActionListener((ActionEvent event) -> {
			username = userT.getText();
			password = passT.getText();
			System.out.println(password);
			Aplikacija.trenutni = checkLogIn();
			if (Aplikacija.trenutni == null) {
				errT.setVisible(true);
				userT.setText("");
				passT.setText("");
			} else {
				obrisiNalog.setVisible(true);
				seeAcc.setVisible(true);
				errT.setVisible(false);
				userT.setVisible(false);
				passT.setVisible(false);
				user.setVisible(false);
				pass.setVisible(false);
				regB.setVisible(false);
				regL.setVisible(false);
				logOut.setVisible(true);
				uw = new UserWindow(Aplikacija.trenutni);
				uw.setVisible(false);
				ok.setVisible(false);
				cancel.setVisible(false);
				// dispose();
			}
		});
		
		
		//obrisiNalog = new JButton("Delete profile");
		//obrisiNalog.setBounds(550, 220, 200, 30);
		obrisiNalog.addActionListener((ActionEvent)->{
			//dodati ovo dugme u StartWindow?
			JFrame sure = new JFrame();
			sure.setLayout(null);
			sure.setLocationRelativeTo(null);
			sure.setSize(350, 120);
			JLabel lab = new JLabel("Are you sure you want to delete your profile?");
			lab.setBounds(10,10,300,20);
			JButton yes = new JButton("Yes");
			JButton canc = new JButton("Cancel");
			yes.setBounds(40,40,100,20);
			canc.setBounds(150,40,100,20);
			
			sure.add(yes);
			sure.add(canc);
			sure.add(lab);
			sure.setVisible(true);
			
			yes.addActionListener((ActionEvent e)->{
				//promena stanja korisnika
				Aplikacija.trenutni.getStanje().izbrisanKorisnik();
				Aplikacija.trenutni.setRegistrovan(false);
				//
				Aplikacija.trenutni = null;
				uw.dispose();
				userT.setVisible(true);
				passT.setVisible(true);
				user.setVisible(true);
				pass.setVisible(true);
				regB.setVisible(true);
				regL.setVisible(true);
				ok.setVisible(true);
				cancel.setVisible(true);
				logOut.setVisible(false);
				seeAcc.setVisible(false);
				passT.setText("");
				userT.setText("");
				System.out.println("pumparica");
				sure.dispose();
				
			});
			
			canc.addActionListener((ActionEvent e)->{
				sure.dispose();
			});
		});
		

		// registracija

		regL.setBounds(600, 440, 300, 50);
		regB.setBounds(700, 480, 100, 30);
		add(regL);
		add(regB);
		regB.addActionListener((ActionEvent event) -> {
			regWin = new RegistrationWindow();
		});
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// Za svaki grad moramo prikazati ture
		if (e.getStateChange() == ItemEvent.SELECTED) {
			fillTable(e.getItem().toString());
		}
	}

}
