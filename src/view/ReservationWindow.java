package view;

import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.Aplikacija;
import model.Izvodjenje;

public class ReservationWindow extends JFrame{
	Izvodjenje i;
	//JLabel uspesno = new JLabel("You've successfuly joined this tour!");
	JLabel sigurno = new JLabel("Are you sure you want to book this tour?");
	
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
		this.setSize(350, 200);
		
		
		CancelBook.setBounds(20,100, 80, 30);
		YesBook.setBounds(150, 100, 80, 30);
		sigurno.setBounds(1000,1000, 150, 100);
		
		this.add(YesBook);
		this.add(CancelBook);
		this.add(sigurno);
		
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		
		YesBook.addActionListener((ActionEvent event) -> {
			//metoda za rezervaciju
			JOptionPane.showMessageDialog(null, "You've successfuly joined this tour!");
			this.dispose();
			
		});
		
		
		CancelBook.addActionListener((ActionEvent event) -> {
			this.dispose();
			
		});
		
	}
}
