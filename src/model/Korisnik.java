package model;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Korisnik {
	protected String korisnickoIme;
	protected String lozinka;
	protected Osoba osoba;
	protected boolean registrovan;
	protected ArrayList<Obilazak> vodic;
	protected ArrayList<Obilazak> turista;
	
	public Korisnik(){
		this.vodic = new ArrayList<Obilazak>();
		this.turista = new ArrayList<Obilazak>();
	}


	public Korisnik(String korisnickoIme, String lozinka, Osoba osoba, boolean registrovan, ArrayList<Obilazak> vodic,
			ArrayList<Obilazak> turista) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.osoba = osoba;
		this.registrovan = registrovan;
		this.vodic = vodic;
		this.turista = turista;
	}
	
	public Korisnik(String korisnickoIme, String lozinka, Osoba osoba, boolean registrovan) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.osoba = osoba;
		this.registrovan = registrovan;
		this.vodic = new ArrayList<Obilazak>();
		this.turista = new ArrayList<Obilazak>();
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

	public boolean isRegistrovan() {
		return registrovan;
	}

	public void setRegitrovan(boolean registrovan) {
		this.registrovan = registrovan;
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


	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY.");
		return korisnickoIme+"|"+lozinka+"|"+osoba.getIme()+"|"+osoba.getPrezime()+"|"+osoba.getJmbg()+"|"+osoba.getUlica()+"|"+sdf.format(osoba.getDatumRodjenja())+"|"+osoba.getBrojRacuna()+"|"+osoba.getStanjeNaRacunu()+"|"+osoba.getEmail()+"\n";
	}

	
	public void dodavanjeTermina(Obilazak ob, Date datum){
		Izvodjenje izv = new Izvodjenje();
		Kreiran s = new Kreiran();
		s.setIzvodjenje(izv);
		izv.promijeniStanje(s);
		izv.setObilazak(ob);
		izv.setTermin(datum);
		izv.setBrMjesta(ob.getBrMjesta());
		String[] lista = ob.getIzvodjenja().get(ob.getIzvodjenja().size()-1).getIdIzv().split(".");
		Integer br = Integer.parseInt(lista[1])+1;
		izv.setIdIzv(lista[0]+"."+br);
		ob.getIzvodjenja().add(izv);
	}
	
	public void kreiranjeObilaska(String naziv, ArrayList<Lokacija> loks, int brm, String g){
		Obilazak ob = new Obilazak();
		ob.setBrMjesta(brm);
		ob.setGrad(Aplikacija.gradovi.get(g));
		ob.setIdOb(Aplikacija.sviObilasci.size()+1+"");
		ob.setLokacije(loks);
		ob.setNaziv(naziv);
		ob.setVodic(this);
	}
	
	public void brisanjeObilaska(String id){
		Obilazak del = Aplikacija.sviObilasci.get(id);
		this.getVodic().remove(del);
		for(Korisnik k : del.getTuristiPrisutni()){
			k.getTurista().remove(del);
		}
		Aplikacija.sviObilasci.remove(id);
	}
}
