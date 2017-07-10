package view;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import model.Aplikacija;
import model.Izvodjenje;
import model.Korisnik;
import model.Lokacija;
import model.Obilazak;

public class TourWindow extends JFrame{
	private JLabel labelaTermini;
	private JComboBox<String> box = new JComboBox<String>();
	private String[] termini;
	private Object[][] podaci = new Object[][]{};
	private JTable tabela;
	private String[] ime = { "<html><font color='red'>Comments </font></html>"};
	private DefaultTableModel model;
	private String idObilaska;
	private JButton dugmeRezervisi;
	private JButton dugmeZakazi;
	private JButton izbrisiObilazak;
	private Obilazak o= null;
	private String user="";
	///
	private JTable tabelaIzvodjenja;
	private DefaultTableModel modelIzvodjenja;
	private String[] ime2 = {"<html><font color='red'>Locations </font></html>"};
	private Object[][] podaci2 = new Object[][]{};
	private void fillComboBox() {
		SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy. HH:mm");
		
		box.removeAllItems();
		box.addItem("");
		for (Izvodjenje i : Aplikacija.sviObilasci.get(idObilaska).getIzvodjenja().values()){
			if(i.getStanje().getUpis().compareTo("kreiran")==0){
				box.addItem(sdf.format(i.getTermin()));
			}
		}
	}
	
	private void fillTable() {
		model.setRowCount(0);
		Obilazak o = Aplikacija.sviObilasci.get(idObilaska);
		for (String user:o.getKomentari().keySet()){
			for (String kom:o.getKomentari().get(user)){
				model.addRow(new Object[]{ user+": "+kom});
			}
		}
		
	}
	private void fillLocations(){
		modelIzvodjenja.setRowCount(0);
		Obilazak o = Aplikacija.sviObilasci.get(idObilaska);
		for(Lokacija l : o.getLokacije()){
			modelIzvodjenja.addRow(new Object[]{l.getNaziv()});
		}
	}
	public TourWindow(String idObilaska){
		this.idObilaska=idObilaska;
		fillComboBox();
		initIU();
	}

	public void initIU(){
		setLayout(null);
		labelaTermini = new JLabel("Dates:");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(dim.width*3/5, 900);
		ImageIcon ikonica = new ImageIcon("./src/slike/dinamit.png");
		this.setIconImage(ikonica.getImage());
		this.setTitle("Tour info");
		JLabel naslov = new JLabel();
		naslov.setBounds(20, 10, 700, 100);
		naslov.setFont(new Font("font1",Font.BOLD,40));
		add(naslov);
		getContentPane().setBackground(new Color(0, 153, 76));
		
		
		labelaTermini.setBounds(40, 150, 150, 25);
		labelaTermini.setFont(new Font("fontic", Font.BOLD, 25));
		box.setBounds(140, 150, 150, 25);  
		add(labelaTermini);
		add(box);
		
		
		//box.addItemListener(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		model = new DefaultTableModel(podaci, ime) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		
		tabela = new JTable(model);
		tabela.setFont(new Font("f", Font.BOLD, 20));
		tabela.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 30));
		
		tabela.setRowHeight(30);
		JScrollPane galerija = new JScrollPane(tabela);
		galerija.setBounds(40, 230, 450, 300);
		galerija.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		galerija.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(galerija);
		
		
	
		////
		modelIzvodjenja = new DefaultTableModel(podaci2, ime2) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tabelaIzvodjenja = new JTable(modelIzvodjenja);
		tabelaIzvodjenja.setFont(new Font("f", Font.BOLD, 20));
		tabelaIzvodjenja.getTableHeader().setFont(new Font("fontizvodjenja", Font.BOLD, 20));
		
		tabelaIzvodjenja.setRowHeight(20);
		JScrollPane galerijaIzvodjenja = new JScrollPane(tabelaIzvodjenja);
		galerijaIzvodjenja.setBounds(40, 550, 200, 200);
		galerijaIzvodjenja.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		galerijaIzvodjenja.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(galerijaIzvodjenja);
		fillLocations();
		////
		for (String id:Aplikacija.sviObilasci.keySet()){
			if (id.compareTo(idObilaska)==0){
				o=Aplikacija.sviObilasci.get(idObilaska);
				naslov.setText(Aplikacija.sviObilasci.get(idObilaska).getNaziv());
			//?	break;
			}
		}

		fillTable();
		
		JLabel imence = new JLabel("Ime: 	" + o.getVodic().getOsoba().getIme());
		imence.setBounds(530, 315, 150,25);
		JLabel prezime = new JLabel("Prezime:	" + o.getVodic().getOsoba().getPrezime());
		prezime.setBounds(530,340,150,25);
		JLabel email = new JLabel("Email:	" + o.getVodic().getOsoba().getEmail());
		email.setBounds(530,370,150,25);
		add(imence);
		add(prezime);
		add(email);
		JTextPane komentar = new JTextPane();
		komentar.setBounds(530, 400, 200, 100);
		add(komentar);
		JButton dodajKomentar = new JButton("Add comment");
		dodajKomentar.setBounds(610,505,120,25);
		add(dodajKomentar);
		
		if (Aplikacija.trenutni!=null){
			user=Aplikacija.trenutni.getKorisnickoIme();
		}
		boolean bio = false;
		for (Korisnik kor:o.getTuristiPrisutni()){
			if (kor.getKorisnickoIme().compareTo(user)==0){
				bio=true;
			}
		}
		
		if (!bio){
			System.out.println("nije bioo");
			komentar.setVisible(false);
			dodajKomentar.setVisible(false);
		}if (user.compareTo(o.getVodic().getKorisnickoIme())==0){
			komentar.setVisible(true);
			dodajKomentar.setVisible(true);
		}
		dodajKomentar.addActionListener((ActionEvent event) ->{
			int broj=0;
			if (!o.getKomentari().containsKey(user)){
				o.getKomentari().put(user, new ArrayList<String>());
			}
			o.getKomentari().get(user).add(komentar.getText());
			fillTable();
			komentar.setText("");
		});
		
		JLabel slika = new JLabel(new ImageIcon("./src/slike/korisnik.png"));
		slika.setBounds(530, 100, 200, 200);
		add(slika);
		
		izbrisiObilazak = new JButton("Delete this tour");
		if(Aplikacija.trenutni!=null && Aplikacija.trenutni.getVodic().contains(Aplikacija.sviObilasci.get(this.idObilaska))){
			izbrisiObilazak.setVisible(true);
		}else{
			izbrisiObilazak.setVisible(false);
		}
		izbrisiObilazak.setBounds(500, 150, 100, 25);
		add(izbrisiObilazak);
		izbrisiObilazak.addActionListener((ActionEvent e)->{
			boolean postoji = false;
			for(Izvodjenje izv : Aplikacija.sviObilasci.get(this.idObilaska).getIzvodjenja().values()){
				String stanje = izv.getStanje().getUpis();
				if(stanje.compareTo("kreiran")==0 || stanje.compareTo("utoku")==0 || stanje.compareTo("popunjen")==0){
					postoji = true;
					break;
				}
			}
			if(postoji){
				JOptionPane.showMessageDialog(null, "You can't delete tour that has active dates!");
			}else{
				JOptionPane.showMessageDialog(null, "Tour deleted!");
				izbrisiObilazak.setVisible(false);
				Aplikacija.sviObilasci.get(this.idObilaska).getStanjeObilaska().izbrisanObilazak();
				this.dispose();
			}
		});
		
		dugmeRezervisi = new JButton("Purchase" );
		dugmeRezervisi.setBounds(320, 150, 90, 25);
		add(dugmeRezervisi);
		dugmeZakazi = new JButton("Add a date");
		dugmeZakazi.setBounds(320,150,120,25);
		add(dugmeZakazi);
		if (o.getVodic().getKorisnickoIme().compareTo(user)==0){
			dugmeRezervisi.setVisible(false);
		}else{
			dugmeZakazi.setVisible(false);
		}
		if (Aplikacija.trenutni==null){
			dugmeRezervisi.setVisible(false);
			dugmeZakazi.setVisible(false);
		}
		if(Aplikacija.sviObilasci.get(this.idObilaska).getStanjeObilaska().getUpis().compareTo("neaktuelan")==0){
			dugmeZakazi.setVisible(false);
			dugmeRezervisi.setVisible(false);
			izbrisiObilazak.setVisible(false);
		}
		
		dugmeZakazi.addActionListener((ActionEvent event)->{
			JFrame dejt = new JFrame();
			dejt.setLocationRelativeTo(null);
			dejt.setLayout(null);
			dejt.setSize(350, 120);
			ImageIcon ikona=new ImageIcon("./src/slike/dinamit.png");
			dejt.setIconImage(ikona.getImage());
			JLabel lab = new JLabel("Input date(dd.MM.yyyy. HH:mm): ");
			JTextField tekst = new JTextField();
			lab.setBounds(30, 10, 200, 20);
			tekst.setBounds(220, 10, 100, 20);
			JButton dugme = new JButton("Done");
			dugme.setBounds(130,60,100,20);
			dugme.addActionListener((ActionEvent e)->{
				SimpleDateFormat termin = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
				Date d = null;
				boolean uspio = true;
				try{
					d = termin.parse(tekst.getText());
				}catch(Exception e1){
					uspio = false;
					tekst.setText("");
					JOptionPane.showMessageDialog(null, "Invalid input! Input in format dd.MM.yyyy. HH:mm");
					tekst.setText("");
				}
				if (d.before(new Date())){
					uspio=false;
					JOptionPane.showMessageDialog(null, "Invalid input! Cannot pick a date in past!"); //DUBOKOUMNO
					tekst.setText("");
				}
				if(uspio){
					
					Aplikacija.trenutni.dodavanjeTermina(o, d);
					dejt.dispatchEvent(new WindowEvent(dejt, WindowEvent.WINDOW_CLOSING));
				}
				fillComboBox();
			});
			dejt.add(dugme);
			
			dejt.add(lab);
			dejt.add(tekst);
			dejt.setVisible(true);
		});
		//////==================================================================
		tabela.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();
					JOptionPane.showMessageDialog(null, (String) tabela.getModel().getValueAt(row, column));

				}
			}
		});
		////===========================================================================
		dugmeRezervisi.addActionListener((ActionEvent event) -> {
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
			String termin = box.getSelectedItem().toString();
			if (termin.compareTo("")==0){
				JOptionPane.showMessageDialog(null, "You didn't choose the date");
			}else{
				for (Izvodjenje i:o.getIzvodjenja().values()){
					try {
						if (sdf.format(i.getTermin()).compareTo(termin)==0){
							if (o.getCena()>Aplikacija.trenutni.getOsoba().getStanjeNaRacunu()){
								JOptionPane.showMessageDialog(null, "Not enough money on your card!");
							}
							else if (i.getTuristi().containsKey(Aplikacija.trenutni)){
								JOptionPane.showMessageDialog(null, "You've already booked this tour!");
								
							}else if (i.getBrMjesta()==o.getBrMjesta()){
								JOptionPane.showMessageDialog(null, "No more free space for chosen tour on this date!");
							}
							else{
								ReservationWindow rezProzor = new ReservationWindow(i);//prosledjuje se izvodjenje
								
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			
		});
		
	}
	
	

}
