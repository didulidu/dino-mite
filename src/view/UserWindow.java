package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.SingleSelectionModel;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import model.Aplikacija;
import model.Izvodjenje;
import model.Korisnik;
import model.Obilazak;

public class UserWindow extends JFrame{
	private JLabel ime;
	private JLabel prezime;
	private JLabel email;
	private JLabel ulica;
	private JLabel jmbg;
	private JLabel datumRodj;
	private JLabel stanje;
	private JLabel slika;
	private JLabel korisnickoIme;
	private JScrollPane obilasciKaoTurista;
	private JScrollPane obilasciKaoVodic;
	private JScrollPane izvodjenja;
	private Object[][] podaciTurista;
	private Object[][] podaciVodic;
	private Object[][] podaciIzvodjenje;
	private String[] nazivTurista = { "<html><font color='black'>As tourist, you have visited: </font></html>"};
	private String[] nazivVodic = { "<html><font color='black'>You have created these tours: </font></html>"};
	private String[] nazivIzvodjenje = { "<html><font color='black'>Your scheduled tours:  </font></html>"};
	
	private void ucitavanjePodataka(Korisnik k){
		ime = new JLabel("Ime: 	" + k.getOsoba().getIme());
		this.setTitle("User profile: " + k.getKorisnickoIme());
		prezime = new JLabel("Prezime:	" + k.getOsoba().getPrezime());
		email = new JLabel("Email:	" + k.getOsoba().getEmail());
		jmbg = new JLabel("Jmbg: " + k.getOsoba().getJmbg());
		korisnickoIme = new JLabel(k.getKorisnickoIme());
		ulica = new JLabel("Ulica: "+k.getOsoba().getUlica());
		SimpleDateFormat datRodj = new SimpleDateFormat("dd.MM.YYYY.");
		datumRodj = new JLabel("Datum rodjenja:	 " + datRodj.format(k.getOsoba().getDatumRodjenja()));
		stanje = new JLabel("Stanje racuna: 	" + k.getOsoba().getStanjeNaRacunu());
		ImageIcon ii= new ImageIcon("./src/slike/naslov.png");
		ii.getImage().getScaledInstance(100, 100, Image.SCALE_REPLICATE);
		this.setIconImage(ii.getImage());
		slika = new JLabel(ii);
	}
	
	public UserWindow(Korisnik k){
		podaciTurista = new Object[][]{};
		podaciVodic = new Object[][]{};
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
		korisnickoIme.setBounds(550, 200, 200, 30);
		korisnickoIme.setFont(f);
		
		add(ime);
		add(prezime);
		add(email);
		add(datumRodj);
		add(stanje);
		add(slika);
		add(jmbg);
		add(ulica);
		add(korisnickoIme);
		
		//dodavanje obilazaka koje je korisnik posjetio kao turista
		DefaultTableModel modelTurista = new DefaultTableModel(podaciTurista, nazivTurista) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		popuniTabeluObilazaka(modelTurista, Aplikacija.trenutni.getTurista());
		JTable tabela = new JTable(modelTurista);
		tabela.setFont(new Font("f", Font.BOLD, 15));
		tabela.getTableHeader().setFont(new Font("f", Font.BOLD, 15));
		tabela.setRowHeight(50);
		tabela.setBackground(new Color(255,102,102));
		obilasciKaoTurista = new JScrollPane(tabela);
		obilasciKaoTurista.setBounds(40, 160, 250, 250);
		obilasciKaoTurista.getViewport().setBackground(new Color(153,204,255));
		add(obilasciKaoTurista);
		tabela.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
			    if (e.getClickCount() == 2) {
			      JTable target = (JTable)e.getSource();
			      int row = target.getSelectedRow();
			      int column = target.getSelectedColumn();
			      String[] lista = ((String)tabela.getModel().getValueAt(row, column)).split("\\|");
			      TourWindow tw = new TourWindow(Aplikacija.sviObilasci.get(lista[0]).getIdOb());
			      tw.setVisible(true);
			    }
			  }
		});
		
		//dodavanje obilazaka koje je korisnik kreirao kao vodic
		
		DefaultTableModel modelVodic = new DefaultTableModel(podaciVodic, nazivVodic){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		popuniTabeluObilazaka(modelVodic, Aplikacija.trenutni.getVodic());
		JTable tabelaVodic = new JTable(modelVodic);
		tabelaVodic.setFont(new Font("f", Font.BOLD, 15));
		tabelaVodic.getTableHeader().setFont(new Font("f", Font.BOLD, 15));
		tabelaVodic.setRowHeight(50);
		tabelaVodic.setBackground(new Color(255,102,102));
		tabelaVodic.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
			    if (e.getClickCount() == 2) {
			      JTable target = (JTable)e.getSource();
			      int row = target.getSelectedRow();
			      int column = target.getSelectedColumn();
			      String[] lista = ((String)tabela.getModel().getValueAt(row, column)).split("\\|");
			      TourWindow tw = new TourWindow(Aplikacija.sviObilasci.get(lista[0]).getIdOb());
			      tw.setVisible(true);
			    }
			  }
		});
		obilasciKaoVodic = new JScrollPane(tabelaVodic);
		obilasciKaoVodic.setBounds(40, 420, 250, 250);
		obilasciKaoVodic.getViewport().setBackground(new Color(153,204,255));
		add(obilasciKaoVodic);
		
		//dodavanje zakazanih izvodjenja
		
		DefaultTableModel modelIzvodjenja = new DefaultTableModel(podaciIzvodjenje, nazivIzvodjenje){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		popuniTabeluIzvodjenja(modelIzvodjenja, Aplikacija.trenutni.getVodic());
		JTable tabelaIzvodjenja = new JTable(modelIzvodjenja);
		tabelaIzvodjenja.setFont(new Font("f", Font.BOLD, 15));
		tabelaIzvodjenja.getTableHeader().setFont(new Font("f", Font.BOLD, 15));
		tabelaIzvodjenja.setRowHeight(50);
		tabelaIzvodjenja.setBackground(new Color(255,102,102));
		tabelaIzvodjenja.addMouseListener(new MouseAdapter() {
			  public void mouseClicked(MouseEvent e) {
			    if (e.getClickCount() == 2) {
			      JTable target = (JTable)e.getSource();
			      int row = target.getSelectedRow();
			      int column = target.getSelectedColumn();
			      String[] lista = ((String)tabela.getModel().getValueAt(row, column)).split("\\|");
			      String[] idovi = lista[0].split("\\.");
			      IzvWindow iw = new IzvWindow(Aplikacija.sviObilasci.get(idovi[0]).getIzvodjenja().get(lista[0]).getIdIzv());
			      iw.setVisible(true);
			    }
			  }
		});
		izvodjenja = new JScrollPane(tabelaIzvodjenja);
		izvodjenja.setBounds(450, 250, 250, 450);
		izvodjenja.getViewport().setBackground(new Color(153,204,255));
		//izvodjenja.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		//izvodjenja.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(izvodjenja);
		tabelaIzvodjenja.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

	}
	
	private void popuniTabeluObilazaka(DefaultTableModel model, ArrayList<Obilazak> lista){
		model.setRowCount(0);
		for(Obilazak o : lista){
			model.addRow(new Object[] {o.getIdOb()+"|"+o.getNaziv()});
		}
	}
	
	private void popuniTabeluIzvodjenja(DefaultTableModel model, ArrayList<Obilazak> lista){
		model.setRowCount(0);
		SimpleDateFormat termin = new SimpleDateFormat("dd.MM.YYYY. HH:mm");
		for(Obilazak o : lista){
			for(Izvodjenje iz : o.getIzvodjenja().values()){
				model.addRow(new Object[] {iz.getIdIzv()+"|"+iz.getObilazak().getNaziv()+"|"+termin.format(iz.getTermin())});
			}
		}
	}
	
	
	
}
