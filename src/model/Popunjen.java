package model;

public class Popunjen extends Stanje{

	public Popunjen(){
		upis = "popunjen";
	}

	@Override
	public String toString() {
		return upis;
	}

	@Override
	public void otkazanTermin() {
		this.getIzvodjenje().povracajNovca("");
		Otkazan s = new Otkazan();
		s.setIzvodjenje(this.getIzvodjenje());
		this.izvodjenje.promijeniStanje(s);
	}

	@Override
	public void prijavaNaTermin(String kime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void odjavaOdTermina(String kime) {
		this.getIzvodjenje().povracajNovca(kime);
		Kreiran s = new Kreiran();
		s.setIzvodjenje(this.getIzvodjenje());
		this.getIzvodjenje().promijeniStanje(s);
	}

	@Override
	public void poceloIzvodjenje() {
		UToku s = new UToku();
		s.setIzvodjenje(this.getIzvodjenje());
		this.getIzvodjenje().promijeniStanje(s);
	}

	@Override
	public void zavrsenoIzvodjenje() {
		
	}

}
