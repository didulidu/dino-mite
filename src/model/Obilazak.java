package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Obilazak {
	protected String idOb;
	protected String naziv;
	protected ArrayList<Lokacija> lokacije;
	protected ArrayList<Izvodjenje> izvodjenja;
	protected ArrayList<Korisnik> turistiPrisutni;
	protected Korisnik vodic;
	protected Grad grad;
	protected int brMjesta;
	protected HashMap<String, ArrayList<String>> komentari = new HashMap<String, ArrayList<String>>(); //kljuc: username, vrednost: lista komentara
	
	
	
	public HashMap<String, ArrayList<String>> getKomentari() {
		return komentari;
	}

	public void setKomentari(HashMap<String, ArrayList<String>> komentari) {
		this.komentari = komentari;
	}

	public int getBrMjesta() {
		return brMjesta;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public void setBrMjesta(int brMjesta) {
		this.brMjesta = brMjesta;
	}

	public ArrayList<Korisnik> getTuristiPrisutni() {
		return turistiPrisutni;
	}

	public void setTuristiPrisutni(ArrayList<Korisnik> turistiPrisutni) {
		this.turistiPrisutni = turistiPrisutni;
	}
	
	public Grad getGrad() {
		return grad;
	}

	public void setGrad(Grad grad) {
		this.grad = grad;
	}

	public Obilazak(){
		lokacije = new ArrayList<Lokacija>();
		izvodjenja = new ArrayList<Izvodjenje>();
		turistiPrisutni = new ArrayList<Korisnik>();
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

	@Override
	public String toString() {
		String linija;
		linija = getIdOb()+"|"+this.brMjesta+"|"+this.naziv+"|"+getGrad().getNaziv()+"|"+getVodic().getKorisnickoIme();
		boolean i = true;
		for(Lokacija l : getLokacije()){
			if (i){
				linija = linija+"|"+l.getNaziv();
				i = false;
				continue;
			}
			linija = linija + ";"+l.getNaziv();
		}
		for(Korisnik k : getTuristiPrisutni()){
			if(!i){
				linija = linija + "|"+k.getKorisnickoIme();
				i=true;
				continue;
			}
			linija = linija + ";" + k.getKorisnickoIme();
		}
		return linija+"\n";
	}
	
	
}
