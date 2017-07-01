package model;

import java.util.ArrayList;

public class Izvodjenje {
	protected String idIzv;
	protected ArrayList<Korisnik> turisti;
	
	public Izvodjenje(){
		
	}

	public Izvodjenje(String idIzv, ArrayList<Korisnik> turisti) {
		super();
		this.idIzv = idIzv;
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
	
	
}
