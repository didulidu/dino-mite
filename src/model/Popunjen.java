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
		Otkazan s = new Otkazan();
		s.setIzvodjenje(this.getIzvodjenje());
		this.izvodjenje.promijeniStanje(s);
	}

	@Override
	public void prijavaNaTermin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void odjavaOdTermina() {
		Kreiran s = new Kreiran();
		s.setIzvodjenje(this.getIzvodjenje());
		this.getIzvodjenje().promijeniStanje(s);
	}

	@Override
	public void poceoObilazak() {
		UToku s = new UToku();
		s.setIzvodjenje(this.getIzvodjenje());
		this.getIzvodjenje().promijeniStanje(s);
	}

	@Override
	public void zavrsenObilazak() {
		
	}
}
