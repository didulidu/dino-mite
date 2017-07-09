package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Aplikacija;
import model.Izvodjenje;
import model.Korisnik;

public class IzvWindow extends JFrame{
	private Izvodjenje izvodjenje;
	private JButton cancelGuidance;
	private JButton beginGuidance;
	private JButton endGuidance;
	private JTable tabela;
	private JScrollPane skrol;
	private Object[][] podaci;
	private DefaultTableModel model;
	

	public IzvWindow(Izvodjenje izv){
		izvodjenje = izv;
		this.setSize(550,550);
		this.setLayout(null);
		String[] naziv = { "<html><font color='black'>Tourists: </font></html>"};
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
		
		popuniTabelu();
		
		skrol = new JScrollPane(tabela);
		skrol.setBounds(40, 160, 450, 250);
		add(skrol);
		
		cancelGuidance = new JButton("Cancel");
		cancelGuidance.setBounds(400, 20, 100, 30);
		cancelGuidance.addActionListener((ActionEvent e)->{
			JOptionPane.showMessageDialog(null, "This tour has been canceled!");
			izvodjenje.otkazivanjeIzvodjenja();
			beginGuidance.setVisible(false);
			endGuidance.setVisible(false);
			cancelGuidance.setVisible(false);
		});
		
		
		beginGuidance = new JButton("Begin");
		beginGuidance.setBounds(400, 70, 100, 30);
		endGuidance = new JButton("End");
		endGuidance.setBounds(400,70,100,30);
		
		
		beginGuidance.addActionListener((ActionEvent e)->{
			izv.zapocniIzvodjenje();
			beginGuidance.setVisible(false);
			endGuidance.setVisible(true);
		});
		
		endGuidance.addActionListener((ActionEvent ev)->{
			JFrame dolazak = new JFrame();
			dolazak.setLayout(null);
			dolazak.setVisible(true);
			int size = izv.getObilazak().getBrMjesta()-izv.getBrMjesta();
			dolazak.setSize(300, size*150);
			int pomjeraj = 0;
			HashMap<Korisnik, JCheckBox> mapa = new HashMap<Korisnik, JCheckBox>();
			for(Korisnik k : izvodjenje.getTuristi().keySet()){
				JLabel lab = new JLabel(k.getKorisnickoIme());
				JCheckBox cek = new JCheckBox();
				cek.setBounds(150, 50+pomjeraj, 100,20);
				lab.setBounds(30, 50+pomjeraj, 100,20);
				pomjeraj = pomjeraj + 30;
				dolazak.add(lab);
				dolazak.add(cek);
				mapa.put(k, cek);
			}
			
			JButton kraj = new JButton("Done");
			kraj.setBounds(100, 10, 100,20);
			kraj.addActionListener((ActionEvent ee)->{
				for(Korisnik k: mapa.keySet()){
					if(mapa.get(k).isSelected()){
						izv.getTuristi().replace(Aplikacija.korisnici.get(k.getKorisnickoIme()), "bio");
					}else{
						izv.getTuristi().replace(Aplikacija.korisnici.get(k.getKorisnickoIme()), "nije bio");
					}
				}
				dolazak.dispose();
				popuniTabelu();
				endGuidance.setVisible(false);
				cancelGuidance.setVisible(false);
				izv.zavrsiIzvodjenje();
			});
			dolazak.add(kraj);
		});
		
		if(izv.getStanje().getUpis().compareTo("kreiran")==0 || izv.getStanje().getUpis().compareTo("popunjen")==0){
			add(cancelGuidance);
			add(beginGuidance);
			add(endGuidance);
		}
		
		if(izv.getStanje().getUpis().compareTo("utoku")==0){
			add(endGuidance);
		}
	}
	
	private void popuniTabelu(){		
		model.setRowCount(0);
		model.setColumnCount(1);
		Vector<String> vekt = new Vector<String>();
		for(Korisnik k : izvodjenje.getTuristi().keySet()){
			vekt.add(izvodjenje.getTuristi().get(k));
			model.addRow(new Object[] {k.getKorisnickoIme()});
		}
		model.addColumn("Arrived?", vekt);
	}
}
