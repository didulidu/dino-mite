package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class StartWindow extends JFrame {

    public StartWindow() {

        initUI();
    }

    private void logIn(){
    	
    }
    
    private void initUI() {
    	
    	setLayout(null);
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
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
        	StartWindow ex = new StartWindow();
            ex.setVisible(true);
        });
    }
}