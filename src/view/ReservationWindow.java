package view;

import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Aplikacija;
import model.Izvodjenje;

public class ReservationWindow extends JFrame{
	Izvodjenje i;
	JFrame uspesnaRezervacija = new JFrame();
	JLabel uspesno = new JLabel("You've successfuly joined this tour!");
	JLabel sigurno = new JLabel("Are you sure you want to book this tour?");
	JLabel nemaPara = new JLabel("Not enough money on your credit card!");
	JButton uspesnoOK = new JButton("OK");
	JButton nemaParaOK = new JButton("OK");
	JButton YesBook = new JButton("Yes");
	JButton CancelBook = new JButton("Cancel");
	
	public ReservationWindow(Izvodjenje i){
		this.i=i;
		initUI();
	}
	
	public void initUI(){
		setTitle("Reservation");
		ImageIcon ikonica = new ImageIcon("./src/slike/dinami.png");
		setIconImage(ikonica.getImage());
		this.setSize(200, 100);
		nemaPara.setBounds(5, 5, 25, 10);
		sigurno.setBounds(5, 5, 25, 10);
		nemaParaOK.setBounds(50,70, 25, 15);
		CancelBook.setBounds(50,70, 25, 15);
		YesBook.setBounds(10, 70, 25, 15);
		this.add(nemaPara);
		this.add(sigurno);
		this.add(nemaParaOK);
		this.add(YesBook);
		
		uspesnaRezervacija.add(uspesno);
		uspesnaRezervacija.add(uspesnoOK);
		
		if (i.getObilazak().getCena()>Aplikacija.trenutni.getOsoba().getStanjeNaRacunu()){
			//nemaPara.setVisible(true);
			sigurno.setVisible(false);
			//nemaParaOK.setVisible(true);
		}else{
			//sigurno.setVisible(true);
			nemaPara.setVisible(false);
			//nemaParaOK.setVisible(true);
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		uspesnoOK.addActionListener((ActionEvent event) -> {
			uspesnaRezervacija.dispose();
			
		});
		
		nemaParaOK.addActionListener((ActionEvent event) -> {
			this.dispose();
			
		});
		
		YesBook.addActionListener((ActionEvent event) -> {
			//metoda za rezervaciju
			
		});
		
		
		CancelBook.addActionListener((ActionEvent event) -> {
			this.dispose();
			
		});
		
		
	}
}
