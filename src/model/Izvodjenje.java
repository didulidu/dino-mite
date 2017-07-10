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
		linija = idIzv+"|"+stanje.toString()+"|";
		boolean i=true;
		for(Korisnik k : turisti.keySet()){
			if(i){
				linija=linija+k.getKorisnickoIme()+"/"+turisti.get(k);
				i=false;
				continue;
			}
			linija=linija+";"+k.getKorisnickoIme()+"/"+turisti.get(k);
		}
		return linija+"|"+sdf.format(termin)+"|"+this.brMjesta+"\n";
	}
	
	public void zapocniIzvodjenje(){
		this.stanje.poceloIzvodjenje();
	}
	
	public void otkazivanjeIzvodjenja(){
		this.brMjesta=0;
		this.stanje.otkazanTermin();
	}
	
	public void prijavaDolaska(String kime){
		this.brMjesta--;
		this.turisti.put(Aplikacija.korisnici.get(kime), "tba");
		this.stanje.prijavaNaTermin(kime);
	}
	
	public void odjavaDolaska(String kime, String idOb){
		this.brMjesta++;
		this.turisti.remove(Aplikacija.korisnici.get(kime));
		ArrayList<Izvodjenje> temp = Aplikacija.korisnici.get(kime).getPrijavljen();
		temp.remove(this);
		Aplikacija.korisnici.get(kime).setPrijavljen(temp);
		this.stanje.odjavaOdTermina(kime);
	}
	
	public void promijeniStanje(Stanje s){
		this.setStanje(s);
	}
	
	public void zavrsiIzvodjenje(){
		ArrayList<Korisnik> temp = this.obilazak.getTuristiPrisutni();
		for(Korisnik k: this.turisti.keySet()){
			if(this.turisti.get(k).compareTo("bio")==0){
				if(!temp.contains(k)){
					temp.add(k);
				}
			}
			ArrayList<Obilazak> obs = k.getTurista();
			obs.add(this.obilazak);
			k.setTurista(obs);
		}
		this.obilazak.setTuristiPrisutni(temp);
		this.stanje.zavrsenoIzvodjenje();
	}
	
	public void placanje(String korisnickoIme){
		Korisnik k = Aplikacija.korisnici.get(korisnickoIme);
		double stanjeRac = k.getOsoba().getStanjeNaRacunu();
		k.getOsoba().setStanjeNaRacunu(stanjeRac-this.getObilazak().getCena());		
	}
	
	public void povracajNovca(String korisnickoIme){
		double stanjeRac;
		if(korisnickoIme.isEmpty()){
			for(Korisnik k : this.getTuristi().keySet()){
				stanjeRac = k.getOsoba().getStanjeNaRacunu();
				k.getOsoba().setStanjeNaRacunu(stanjeRac+this.getObilazak().getCena());
			}
		}else{
			Korisnik k = Aplikacija.korisnici.get(korisnickoIme);
			stanjeRac = k.getOsoba().getStanjeNaRacunu();
			k.getOsoba().setStanjeNaRacunu(stanjeRac+this.getObilazak().getCena());
		}
	}
	
}
