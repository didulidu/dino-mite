package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Aplikacija;
import model.Main;
import model.Obilazak;

public class StartWindow extends JFrame
        implements ItemListener {

    private JLabel display;
    private JComboBox<String> box;
    private String[] cities;
    private Object[][] podaci;
    private JTable tabela;
    private String[] ime={"Obilasci: "};
    private DefaultTableModel model;
    

    private void fillComboBox(){
        box = new JComboBox<>();
        box.addItem("");
    	for (String  g : Aplikacija.gradovi.keySet())
    		box.addItem(g);
    }
    
    private void fillTable(String city){
        model.setRowCount(0);
    	for (Obilazak o : Aplikacija.sviObilasci.values()){
    		if (o.getGrad().getNaziv().compareTo(city)==0){
    	        model.addRow(new Object[]{o.getNaziv()});
    	        System.out.println(o.getIdOb());
    			System.out.println("Dodao  u tabelu");
    		}
    	}
    	
    	
    	}
    
    public StartWindow() {

    	fillComboBox();
        initUI();

    }

    private void initUI() {
    	setLayout(null);

        display = new JLabel("Grad: ");
        JLabel naslov = new JLabel(new ImageIcon("./src/slike/naslov.png"));
        naslov.setBounds(140, 10, 677,98);
        add(naslov);
        getContentPane().setBackground(new Color(0,153,76));
    	setSize(1000,800);
    	display.setBounds(40, 200, 150,25);
    	display.setFont(new Font("fontic",Font.BOLD, 20 ));
    	box.setBounds(100,200,100,25);
    	add(display);
    	add(box);
        box.addItemListener(this);        
        setTitle("DinoMite");
        ImageIcon image = new ImageIcon("./src/slike/naslov.png");
        setIconImage(image.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);     

        setResizable(false);
        tabela = new JTable(new DefaultTableModel(new Object[][]{}, ime));
    	tabela.setRowHeight(30);

        model = (DefaultTableModel) tabela.getModel();
        JScrollPane galerija = new JScrollPane(tabela);
        galerija.setBounds(40,300,500,400);
        add(galerija);
        

        
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
// Za svaki grad moramo prikazati ture
        if (e.getStateChange() == ItemEvent.SELECTED) {
            fillTable(e.getItem().toString());
        }
    }

//    public static void main(String[] args) {
//
//        EventQueue.invokeLater(() -> {
//            StartWindow ex = new StartWindow();
//            ex.setVisible(true);
//        });
//    }
//    
}
