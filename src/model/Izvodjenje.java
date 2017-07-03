package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
public class Izvodjenje {
	protected String idIzv;
	protected int brMjesta;
	protected Obilazak obilazak;
	protected HashMap<Korisnik, String> turisti;
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
		linija = obilazak.getIdOb()+"|"+idIzv+"|"+stanje.toString()+"|";
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
	
	public void zapocniObilazak(){
		this.stanje.poceoObilazak();
	}
	
	public void otkazivanjeObilaska(){
		this.brMjesta=0;
		this.stanje.otkazanTermin();
	}
	
	public void prijavaDolaska(String kime){
		this.brMjesta--;
		this.turisti.put(Aplikacija.korisnici.get(kime), "tba");
		this.stanje.prijavaNaTermin();
	}
	
	public void odjavaDolaska(String kime){
		this.brMjesta++;
		this.turisti.remove(Aplikacija.korisnici.get(kime));
		this.stanje.odjavaOdTermina();
	}
	
	public void promijeniStanje(Stanje s){
		this.setStanje(s);
	}
	
	public void zavrsiObilazak(){
		ArrayList<Korisnik> temp = this.obilazak.getTuristiPrisutni();
		for(Korisnik k: this.turisti.keySet()){
			if(this.turisti.get(k).compareTo("bio")==0){
				temp.add(k);
			}
			ArrayList<Obilazak> obs = k.getTurista();
			obs.add(this.obilazak);
			k.setTurista(obs);
		}
		this.obilazak.setTuristiPrisutni(temp);
		this.stanje.zavrsenObilazak();
	}
}
