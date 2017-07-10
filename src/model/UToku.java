package model;

public class UToku extends Stanje {

	public UToku() {
		upis = "utoku";
	}

	@Override
	public String toString() {
		return upis;
	}

	@Override
	public void otkazanTermin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void prijavaNaTermin(String kime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void odjavaOdTermina(String kime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void poceloIzvodjenje() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void zavrsenoIzvodjenje() {
		Zavrsen s = new Zavrsen();
		s.setIzvodjenje(this.getIzvodjenje());
		this.getIzvodjenje().promijeniStanje(s);
		
	}

	
}
