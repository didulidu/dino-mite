package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.SingleSelectionModel;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import model.Aplikacija;
import model.Grad;
import model.Izvodjenje;
import model.Korisnik;
import model.Lokacija;
import model.Obilazak;

public class UserWindow extends JFrame implements ItemListener{
	private JLabel ime;
	private JLabel prezime;
	private JLabel email;
	private JLabel ulica;
	private JLabel jmbg;
	private JLabel datumRodj;
	private JLabel stanje;
	private JLabel slika;
	private JLabel korisnickoIme;
	private JTable tabela;
	private JScrollPane skrol;
	private JComboBox<String> kombo;
	private JButton dodajObilazak;
	private JButton izbrisiProfil;
	private Object[][] podaci;
	private DefaultTableModel model;
	
	private void ucitavanjePodataka(Korisnik k){
		ime = new JLabel("Ime: 	" + k.getOsoba().getIme());
		this.setTitle("User profile: " + k.getKorisnickoIme());
		prezime = new JLabel("Prezime:	" + k.getOsoba().getPrezime());
		email = new JLabel("Email:	" + k.getOsoba().getEmail());
		jmbg = new JLabel("Jmbg: " + k.getOsoba().getJmbg());
		korisnickoIme = new JLabel(k.getKorisnickoIme());
		ulica = new JLabel("Ulica: "+k.getOsoba().getUlica());
		SimpleDateFormat datRodj = new SimpleDateFormat("dd.MM.yyyy.");
		datumRodj = new JLabel("Datum rodjenja:	 " + datRodj.format(k.getOsoba().getDatumRodjenja()));
		stanje = new JLabel("Stanje racuna: 	" + k.getOsoba().getStanjeNaRacunu());
		ImageIcon ii= new ImageIcon("./src/slike/dinamit.png");
		this.setIconImage(ii.getImage());
		ImageIcon avatar = new ImageIcon("./src/slike/korisnik.png");
		slika = new JLabel(avatar);
		
	}
	
	public UserWindow(Korisnik k){
		ucitavanjePodataka(k);
		initUI();
	}
	
	private void initUI(){
		setLayout(null);
		setSize(800, 800);
		setResizable(false);
		getContentPane().setBackground(new Color(0, 128, 255));
		
		Font f = new Font("f", Font.BOLD, 18);
		ime.setBounds(50, 10, 300, 30);
		ime.setFont(f);
		prezime.setBounds(50,  30, 300, 30);
		prezime.setFont(f);
		jmbg.setBounds(50, 50,  300, 30);
		jmbg.setFont(f);
		email.setBounds(50,  70, 300, 30);
		email.setFont(f);
		ulica.setBounds(50,  90,  300, 30);
		ulica.setFont(f);
		datumRodj.setBounds(50, 110, 300,  30);
		datumRodj.setFont(f);
		stanje.setBounds(50,  130, 300, 30);
		stanje.setFont(f);
		slika.setBounds(550, 10, 200, 200);
		korisnickoIme.setBounds(550, 180, 200, 30);
		korisnickoIme.setFont(f);
		
		dodajObilazak = new JButton("Add tour");
		dodajObilazak.setBounds(50, 430, 90, 25);
		
	
		
		dodajObilazak.addActionListener((ActionEvent event)->{			
			InputWindow iw = new InputWindow();
			iw.setVisible(true);
		});
		
		add(dodajObilazak);
		add(ime);
		add(prezime);
		add(email);
		add(datumRodj);
		add(stanje);
		add(slika);
		add(jmbg);
		add(ulica);
		add(korisnickoIme);
		
		postaviKomboBoks();
		kombo.addItemListener(this);
		
		podaci = new Object[][]{};
		String[] naziv = { "<html><font color='black'>Tours: </font></html>"};
		model = new DefaultTableModel(podaci, naziv){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tabela = new JTable(model);
		tabela.setFont(new Font("f", Font.BOLD, 15));
		tabela.getTableHeader().setFont(new Font("f", Font.BOLD, 15));
		tabela.setRowHeight(50);
		tabela.getSelectionBackground();
		tabela.setBackground(new Color(153,204,255));
		
		skrol = new JScrollPane(tabela);
		skrol.setBounds(40, 160, 450, 250);
		add(skrol);
		tabela.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
			    if (e.getClickCount() == 2) {
			      JTable target = (JTable)e.getSource();
			      int row = target.getSelectedRow();
			      int column = target.getSelectedColumn();
			      String[] lista = ((String)tabela.getModel().getValueAt(row, column)).split("\\|");
			      System.out.println(lista.length);
			      if(lista.length!=2){
			    	  for(Obilazak o : Aplikacija.sviObilasci.values()){
			    		  if(o.getNaziv().compareTo(lista[0])==0){
						      TourWindow tw = new TourWindow(o.getIdOb());
						      tw.setVisible(true);
			    		  }
			    	  }
			      }
			    }
			  }
		});
		skrol.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		skrol.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	}
	
	private void postaviKomboBoks(){
		kombo = new JComboBox<>();
		kombo.addItem("");
		kombo.addItem("Tours you have visited as a tourist");
		kombo.addItem("Tours you have created");
		kombo.addItem("All your tours");
		kombo.setBounds(550, 260, 200, 20);
		add(kombo);
	}
	
	
	private void popuniTabelu(String str){
		if(str.compareTo("Tours you have visited as a tourist")==0){
			model.setRowCount(0);
			int longest = 0;
			for(Obilazak o : Aplikacija.trenutni.getTurista()){
				model.addRow(new Object[] {o.getNaziv()});
				if(o.getNaziv().length()>longest){
					longest = o.getNaziv().length();
				}
			}
			tabela.setPreferredSize(new Dimension(longest*10,model.getRowCount()*50));
		}else if (str.compareTo("Tours you have created")==0){
			model.setRowCount(0);
			int longest = 0;
			for(Obilazak o : Aplikacija.trenutni.getVodic()){
				model.addRow(new Object[] {o.getNaziv()});
				if(o.getNaziv().length()>longest){
					longest = o.getNaziv().length();
				}
			}
			tabela.setPreferredSize(new Dimension(longest*10,model.getRowCount()*50));
		}else if (str.compareTo("All your tours")==0){
			model.setRowCount(0);
			int longest = 0;
			SimpleDateFormat termin = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
			for(Obilazak o : Aplikacija.trenutni.getVodic()){
				for(Izvodjenje iz : o.getIzvodjenja().values()){
					if(o.getNaziv().length()+iz.getTermin().toString().length()>longest){
						longest = o.getNaziv().length()+iz.getTermin().toString().length();
					}
					model.addRow(new Object[] {iz.getObilazak().getNaziv()+"|"+termin.format(iz.getTermin())});
				}
			}
			tabela.setPreferredSize(new Dimension(longest*6,model.getRowCount()*50));
		}else{
			model.setRowCount(0);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			popuniTabelu(e.getItem().toString());
		}	
	}	
}