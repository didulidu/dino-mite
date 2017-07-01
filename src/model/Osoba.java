package model;

import java.util.ArrayList;

public class Osoba {
	protected String ime;
	protected String prezime;
	protected String jmbg;
	protected String ulica;
	
	public Osoba(){
		
	}
	
	public Osoba(String ime, String prezime, String jmbg, String ulica) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.ulica = ulica;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}
	
	
	
}
