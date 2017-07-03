package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Aplikacija;
import model.Izvodjenje;
import model.Obilazak;

public class TourWindow extends JFrame{
	private JLabel labelaTermini;
	private JComboBox<String> box;
	private String[] termini;
	private Object[][] podaci = new Object[][]{};
	private JTable tabela;
	private String[] ime = { "<html><font color='red'>Comments </font></html>"};
	private DefaultTableModel model;
	private String idObilaska;
	private JButton dugmeRezervisi;
	
	
	private void fillComboBox() {
		SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy. HH:mm");
		box = new JComboBox<>();
		box.addItem("");
		for (Izvodjenje i : Aplikacija.sviObilasci.get(idObilaska).getIzvodjenja()){
			if (i.getObilazak().getIdOb().compareTo(idObilaska)==0){
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
	
	public TourWindow(String idObilaska){
		this.idObilaska=idObilaska;
		fillComboBox();
		initIU();
	}

	
	public void initIU(){
		setLayout(null);
		labelaTermini = new JLabel("Dates:");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(dim.width*3/5, dim.height*4/5);
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		model = new DefaultTableModel(podaci, ime) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		
		tabela = new JTable(model);
		tabela.setFont(new Font("f", Font.BOLD, 10));
		tabela.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 30));
		
		tabela.setRowHeight(30);
		JScrollPane galerija = new JScrollPane(tabela);
		galerija.setBounds(40, 230, 450, 300);
		galerija.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		galerija.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(galerija);
		
	
		Obilazak o= null;
		for (String id:Aplikacija.sviObilasci.keySet()){
			if (id.compareTo(idObilaska)==0){
				o=Aplikacija.sviObilasci.get(idObilaska);
				naslov.setText(Aplikacija.sviObilasci.get(idObilaska).getNaziv());
			}
		}

		fillTable();
		
		dugmeRezervisi = new JButton("Purchase" );
		dugmeRezervisi.setBounds(320, 150, 90, 25);
		add(dugmeRezervisi);
		
		
		dugmeRezervisi.addActionListener((ActionEvent event) -> {
			//ReservationWindow();//prosledjuju se termin, username korisnika
		});
		
	}

}
