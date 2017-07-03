package model;

public abstract class Stanje {
	protected Izvodjenje izvodjenje;
	protected String upis;
	
	public abstract void otkazanTermin();
	public abstract void prijavaNaTermin();
	public abstract void odjavaOdTermina();
	public abstract void poceoObilazak();
	public abstract void zavrsenObilazak();
	
	public Izvodjenje getIzvodjenje() {
		return izvodjenje;
	}
	public void setIzvodjenje(Izvodjenje izvodjenje) {
		this.izvodjenje = izvodjenje;
	}
	public String getUpis() {
		return upis;
	}
	public void setUpis(String upis) {
		this.upis = upis;
	}
	
	
}
