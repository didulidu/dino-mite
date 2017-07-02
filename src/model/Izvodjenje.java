package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Izvodjenje {
	protected String idIzv;
	protected int brMjesta;
	protected Obilazak obilazak;
	protected HashMap<Korisnik, String> turisti = new HashMap<Korisnik, String>();
	protected Stanje stanje;
	protected Date termin;
	
	public Stanje getStanje() {
		return stanje;
	}

	public void setStanje(Stanje stanje) {
		this.stanje = stanje;
	}

	public Izvodjenje(){
		turisti = new HashMap<Korisnik, String>();
	}
	
	public Izvodjenje(String idIzv, int brMjesta, Obilazak obilazak, HashMap<Korisnik, String> turisti) {
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

	public HashMap<Korisnik, String> getTuristi() {
		return turisti;
	}

	public void setTuristi(HashMap<Korisnik, String> turisti) {
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
	
	
	

	public Date getTermin() {
		return termin;
	}

	public void setTermin(Date termin) {
		this.termin = termin;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
		String linija;
		linija = obilazak.getIdOb()+"|"+idIzv+"|"+brMjesta+"|"+stanje.toString()+"|";
		boolean i=true;
		for(Korisnik k : turisti.keySet()){
			if(i){
				linija=linija+k.getKorisnickoIme()+"/"+turisti.get(k);
				i=false;
				continue;
			}
			linija=linija+";"+k.getKorisnickoIme()+"/"+turisti.get(k);
		}
		return linija+"|"+sdf.format(termin)+"\n";
	}
	
	public void otkazivanjeObilaska(){
		
	}
	
	public void prijavaDolaska(){
		this.brMjesta--;
		if(this.brMjesta==0){
			Popunjen p = new Popunjen();
		}
		
	}
	
	public void odjavaDolaska(){
		
	}
	
	public void promijeniStanje(){
		
	}
	
	
}
