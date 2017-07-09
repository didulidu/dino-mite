package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Aplikacija;
import model.Grad;
import model.Lokacija;
import model.Obilazak;
import sun.invoke.empty.Empty;

public class InputWindow extends JFrame implements ItemListener {
	private JLabel labNaziv;
	private JLabel labMjesta;
	private JLabel labGrad;
	private JLabel labLoks;
	private JLabel labCijena;
	private JTextField tCijena;
	private JTextField tNaziv;
	private JTextField tMjesta;
	private JComboBox<String> cGrad;
	private JComboBox<String> cLoks;
	private JButton dodajLok;
	private JButton zavrsi;
	private ArrayList<Lokacija> lokacije;
	private String nazivGrada;
	private JLabel 	errL = new JLabel("<html><font color='red'>Location already exist!</font></html>");
	private JTextField crLocT;
	private JButton crLocB;
	private JLabel crLoc;

	public InputWindow() {
		lokacije = new ArrayList<Lokacija>();
		initUI();
	}

	private Lokacija createLocation() {
		String name = crLocT.getText();
		if (name.isEmpty())
			return null;
		Grad o = Aplikacija.gradovi.get(cGrad.getSelectedItem());
		System.out.println(o);
		for (Lokacija lo : o.getLokacije()) {
			if (name.compareTo(lo.getNaziv()) == 0) {
				errL.setVisible(true);
				crLocT.setText("");
				return null;
			}
		}
		if (crLocT.getText().compareTo("") != 0) {
			Lokacija l = new Lokacija(name);
			o.getLokacije().add(l);
			return l;
		}
		errL.setVisible(true);
		return null;

	}

	private void initUI() {
		setLayout(null);
		setSize(800, 250);
		setResizable(false);
		setLocationRelativeTo(null);
		labNaziv = new JLabel("Tour name:");
		labNaziv.setBounds(10, 10, 100, 20);
		tNaziv = new JTextField();
		tNaziv.setBounds(160, 10, 100, 20);

		labMjesta = new JLabel("Max number of tourists:");
		labMjesta.setBounds(10, 30, 150, 20);
		tMjesta = new JTextField();
		tMjesta.setBounds(160, 30, 100, 20);

		labGrad = new JLabel("City: ");
		labGrad.setBounds(10, 50, 100, 20);
		cGrad = new JComboBox<>();
		cGrad.setBounds(160, 50, 100, 20);
		cGrad.addItem("");
		cGrad.addItemListener(this);
		for (Grad g : Aplikacija.gradovi.values()) {
			cGrad.addItem(g.getNaziv());
		}

		labLoks = new JLabel("Locations:");
		labLoks.setBounds(10, 70, 100, 20);
		cLoks = new JComboBox<>();
		cLoks.setBounds(160, 70, 100, 20);
		cLoks.addItem("");
		// =========================================================================================
		crLoc = new JLabel("Other: ");
		crLocB = new JButton("<html><font color='green'>Create</font></html>");
		crLocT = new JTextField();
	
		errL.setVisible(false);
		crLoc.setVisible(false);
		crLocB.setVisible(false);
		crLocT.setVisible(false);
		errL.setBounds(300, 80, 200, 50);
		errL.setFont(new Font("f", Font.BOLD, 20));
		crLocT.setAutoscrolls(true);
		crLoc.setBounds(430, 70, 140, 20);
		crLocB.setBounds(590, 70, 100, 20);
		crLocT.setBounds(470, 70, 100, 20);
		add(crLoc);
		add(crLocB);
		add(crLocT);
		add(errL);
		crLocB.addActionListener((ActionEvent e) -> {
			Lokacija l = createLocation();
			System.out.println(l);
			if (l != null) {
				cLoks.addItem(l.getNaziv());
				errL.setVisible(false);
			} else
				errL.setVisible(true);
		});

		// =========================================================================================
		dodajLok = new JButton("Add location");
		dodajLok.setBounds(280, 70, 150, 20);
		dodajLok.addActionListener((ActionEvent e) -> {
			String lok = (String) cLoks.getSelectedItem();
			if (lok.length() == 0) {
				JOptionPane.showMessageDialog(null, "No location chosen!");
			} else {
				cGrad.setEnabled(false);
				for (Lokacija l : Aplikacija.gradovi.get(nazivGrada).getLokacije()) {
					if (l.getNaziv().compareTo(lok) == 0) {
						if (lokacije.contains(l)) {
							JOptionPane.showMessageDialog(null, "Location already added!");
							break;
						}
						lokacije.add(l);
						break;
					}
				}
			}
		});
		dodajLok.setVisible(false);

		labCijena = new JLabel("Price: ");
		labCijena.setBounds(10, 90, 100, 20);
		tCijena = new JTextField();
		tCijena.setBounds(160, 90, 100, 20);

		zavrsi = new JButton("Done");
		zavrsi.setBounds(150, 150, 100, 20);
		zavrsi.addActionListener((ActionEvent e) -> {
			if (tNaziv.getText().length() == 0 || tMjesta.getText().length() == 0 || lokacije.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Input all fields!");
			} else {
				Integer br = 0;
				boolean uspio = true;
				try {
					br = Integer.parseInt(tMjesta.getText());
				} catch (Exception ee) {
					uspio = false;
					tMjesta.setText("");
					JOptionPane.showMessageDialog(null,
							"Invalid input - field 'Max number of tourists' must be a number!");
				}
				Double cijena = 0.0;
				try {
					cijena = Double.parseDouble(tCijena.getText());
				} catch (Exception ee) {
					uspio = false;
					tCijena.setText("");
					JOptionPane.showMessageDialog(null, "Invalid input - field 'Price' must be a number!");
				}
				if (uspio) {
					Aplikacija.trenutni.kreiranjeObilaska(tNaziv.getText(), lokacije, br, nazivGrada, cijena);
					this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
				}

			}
		});

		add(labCijena);
		add(tCijena);
		add(zavrsi);
		add(dodajLok);
		add(labLoks);
		add(cLoks);
		add(labMjesta);
		add(labNaziv);
		add(tMjesta);
		add(tNaziv);
		add(labGrad);
		add(cGrad);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			nazivGrada = (String) cGrad.getSelectedItem();
			cLoks.removeAllItems();
			cLoks.addItem("");
			if (nazivGrada.length() != 0) {
				dodajLok.setVisible(true);
				crLocT.setVisible(true);
				crLocB.setVisible(true);
				crLoc.setVisible(true);
			} else {
				dodajLok.setVisible(false);
				crLocT.setVisible(false);
				crLocB.setVisible(false);
				crLoc.setVisible(false);
			}
			// grad = Aplikacija.gradovi.get(naziv);
			if (nazivGrada.length() != 0) {
				for (Lokacija l : Aplikacija.gradovi.get(nazivGrada).getLokacije()) {
					cLoks.addItem(l.getNaziv());
				}
			}
		}
	}

}
