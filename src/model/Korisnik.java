package model;

import java.util.ArrayList;

public class Korisnik {
	protected String korisnickoIme;
	protected String lozinka;
	protected Osoba osoba;
	protected boolean regitrovan;
	protected ArrayList<Obilazak> vodic;
	protected ArrayList<Obilazak> turista;
	
	public Korisnik(){
		this.vodic = new ArrayList<Obilazak>();
		this.turista = new ArrayList<Obilazak>();
	}


	public Korisnik(String korisnickoIme, String lozinka, Osoba osoba, boolean regitrovan, ArrayList<Obilazak> vodic,
			ArrayList<Obilazak> turista) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.osoba = osoba;
		this.regitrovan = regitrovan;
		this.vodic = vodic;
		this.turista = turista;
	}
	
	public Korisnik(String korisnickoIme, String lozinka, Osoba osoba, boolean regitrovan) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.osoba = osoba;
		this.regitrovan = regitrovan;
	}


	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public boolean isRegitrovan() {
		return regitrovan;
	}

	public void setRegitrovan(boolean regitrovan) {
		this.regitrovan = regitrovan;
	}
	
	public Osoba getOsoba() {
		return osoba;
	}



	public void setOsoba(Osoba osoba) {
		this.osoba = osoba;
	}



	public ArrayList<Obilazak> getVodic() {
		return vodic;
	}

	public void setVodic(ArrayList<Obilazak> vodic) {
		this.vodic = vodic;
	}



	public ArrayList<Obilazak> getTurista() {
		return turista;
	}



	public void setTurista(ArrayList<Obilazak> turista) {
		this.turista = turista;
	}

}
