package view;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class StartWindow extends JFrame {
// konstruktor poziva prikaz prozora
    public StartWindow() {
        initUI();
    }
// instanciranje prozora za logovanje (jako je ruzan trenutno) - prikaz prozora
    private void logIn(){
    	LogInWindow log = new LogInWindow();
    }
// instanciranje prozora za logovanje (jako je ruzan trenutno) - prikaz prozora
    private void register(){
    	RegistrationWindow reg = new RegistrationWindow();
    }
// pocetni prozor    
    private void initUI() {
    	
    	setLayout(null);
    	ImageIcon ikonica = new ImageIcon("ikonice/mite.png");
		setIconImage(ikonica.getImage());
    	
        JButton logB = new JButton("Log In");
        logB.setBounds(150-40, 250-50, 80, 25);

        JButton regB = new JButton("Register");
        regB.setBounds(250-40, 250-50 , 80, 25);

        JButton galB = new JButton("Search");
        
        galB.setBounds(350-40, 250-50, 80, 25);
        setBackground(new Color(24));
        add(logB);
        add(regB);
        add(galB);

        setTitle("DinoMite");
        setSize(500,500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        logB.addActionListener((ActionEvent event) -> {
            logIn();
        });
        
        regB.addActionListener((ActionEvent event)->{
        	register();
        });
        
    }

}