package model;

import java.util.ArrayList;

public class Obilazak {
	protected String idOb;
	protected ArrayList<Lokacija> lokacije;
	protected ArrayList<Izvodjenje> izvodjenja;
	protected Korisnik vodic;
	protected Grad grad;
	
	public Grad getGrad() {
		return grad;
	}

	public void setGrad(Grad grad) {
		this.grad = grad;
	}

	public Obilazak(){
		lokacije = new ArrayList<Lokacija>();
		izvodjenja = new ArrayList<Izvodjenje>();
	}

	public Obilazak(String idOb, ArrayList<Lokacija> lokacije, ArrayList<Izvodjenje> izvodjenja, Korisnik vodic) {
		super();
		this.idOb = idOb;
		this.lokacije = lokacije;
		this.izvodjenja = izvodjenja;
		this.vodic = vodic;
	}

	public String getIdOb() {
		return idOb;
	}

	public void setIdOb(String idOb) {
		this.idOb = idOb;
	}

	public ArrayList<Lokacija> getLokacije() {
		return lokacije;
	}

	public void setLokacije(ArrayList<Lokacija> lokacije) {
		this.lokacije = lokacije;
	}

	public ArrayList<Izvodjenje> getIzvodjenja() {
		return izvodjenja;
	}

	public void setIzvodjenja(ArrayList<Izvodjenje> izvodjenja) {
		this.izvodjenja = izvodjenja;
	}

	public Korisnik getVodic() {
		return vodic;
	}

	public void setVodic(Korisnik vodic) {
		this.vodic = vodic;
	}
	
	
}
