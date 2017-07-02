package model;

import java.util.ArrayList;
import java.util.Date;

public class Osoba {
	protected String ime;
	protected String prezime;
	protected String jmbg;
	protected String ulica;
	protected Date datumRodjenja;
	protected String brojRacuna;
	protected double stanjeNaRacunu;
	protected String email;
	
	public Osoba(){
		
	}
	
	
	
	public Osoba(String ime, String prezime, String jmbg, String ulica, Date datumRodjenja, String brojRacuna,
			double stanjeNaRacunu, String email) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.ulica = ulica;
		this.datumRodjenja = datumRodjenja;
		this.brojRacuna = brojRacuna;
		this.stanjeNaRacunu = stanjeNaRacunu;
		this.email = email;
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



	public Date getDatumRodjenja() {
		return datumRodjenja;
	}



	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}



	public String getBrojRacuna() {
		return brojRacuna;
	}



	public void setBrojRacuna(String brojRacuna) {
		this.brojRacuna = brojRacuna;
	}



	public double getStanjeNaRacunu() {
		return stanjeNaRacunu;
	}



	public void setStanjeNaRacunu(double stanjeNaRacunu) {
		this.stanjeNaRacunu = stanjeNaRacunu;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	
}
