package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Aplikacija;
import model.Obilazak;

public class TourWindow extends JFrame {
	public TourWindow(String nazivObilaska){
		initIU(nazivObilaska);
	}
	
	public void initIU(String nazivObilaska){
		setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(dim.width*4/5, dim.height*4/5);
		ImageIcon ikonica = new ImageIcon("./src/slike/dinamit.png");
		this.setIconImage(ikonica.getImage());
		this.setTitle("Tour info");
		this.getContentPane().setBackground(new Color(0,153,76));
		
		JLabel naslov = new JLabel(nazivObilaska);
		naslov.setBounds(this.getSize().width/45,this.getSize().height/80 , 400, 80);
		naslov.setFont(new Font("font1",Font.BOLD,60));
		add(naslov);
		
		Obilazak o;
		for (String naziv:Aplikacija.sviObilasci.keySet()){
			if (naziv.compareTo(nazivObilaska)==0){
				o=Aplikacija.sviObilasci.get(nazivObilaska);
			}
		}
		
		JPanel panel = new JPanel();
		JScrollPane panelKomentara = new JScrollPane(panel);
		
		panelKomentara.setBounds(this.getSize().width/15,this.getSize().height/4 , 700, 400);
		panelKomentara.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		this.add(panelKomentara);
		

        //this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        for (Korisnik k:)
		
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		
	}
}
