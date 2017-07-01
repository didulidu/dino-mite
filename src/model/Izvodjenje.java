package model;

import java.util.ArrayList;

public class Izvodjenje {
	protected String idIzv;
	protected int brMjesta;
	protected Obilazak obilazak;
	protected ArrayList<Korisnik> turisti;
	protected Stanje stanje;
	
	public Stanje getStanje() {
		return stanje;
	}

	public void setStanje(Stanje stanje) {
		this.stanje = stanje;
	}

	public Izvodjenje(){
		turisti = new ArrayList<Korisnik>();
	}
	
	public Izvodjenje(String idIzv, int brMjesta, Obilazak obilazak, ArrayList<Korisnik> turisti) {
		super();
		this.idIzv = idIzv;
		this.brMjesta = brMjesta;
		this.obilazak = obilazak;
		this.turisti = turisti;
	}

	public String getIdIzv() {
		return idIzv;
	}

	public void setIdIzv(String idIzv) {
		this.idIzv = idIzv;
	}

	public ArrayList<Korisnik> getTuristi() {
		return turisti;
	}

	public void setTuristi(ArrayList<Korisnik> turisti) {
		this.turisti = turisti;
	}

	public int getBrMjesta() {
		return brMjesta;
	}

	public void setBrMjesta(int brMjesta) {
		this.brMjesta = brMjesta;
	}

	public Obilazak getObilazak() {
		return obilazak;
	}

	public void setObilazak(Obilazak obilazak) {
		this.obilazak = obilazak;
	}
	
	
}
