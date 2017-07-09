package model;

public class Registrovan extends StanjeKorisnika{
	
	public Registrovan(){
		upis = "registrovan";
	}

	@Override
	public void izbrisanKorisnik() {
		Izbrisan stanjeIzbrisan = new Izbrisan();
		stanjeIzbrisan.setKorisnik(Aplikacija.trenutni);
		Aplikacija.trenutni.promijeniStanje(stanjeIzbrisan);////
	}

	@Override
	public String toString() {
		return upis;
	}
	
	

	
}
