package model;

public abstract class StanjeKorisnika {
	protected Korisnik korisnik;
	protected String upis;
	
	public abstract void izbrisanKorisnik();

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public String getUpis() {
		return upis;
	}

	public void setUpis(String upis) {
		this.upis = upis;
	}
	
	
}
