package model;
//zasto smo stavili klasu Grad, a ne Adresa?
import java.util.ArrayList;

public class Grad {
	protected String naziv;
	protected ArrayList<Lokacija> lokacije;
	
	public Grad(String nazivv){
		this.naziv = nazivv;
		lokacije = new ArrayList<Lokacija>();
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public ArrayList<Lokacija> getLokacije() {
		return lokacije;
	}

	public void setLokacije(ArrayList<Lokacija> lokacije) {
		this.lokacije = lokacije;
	}

	@Override
	public String toString() {
		String linija = naziv;
		for(Lokacija l : lokacije){
			linija=linija+"|"+l.toString();
		}
		return linija+"\n";
	}
	
	
}
