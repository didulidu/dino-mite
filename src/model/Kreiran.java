package model;

public class Kreiran extends Stanje{

	public Kreiran(){
		upis = "kreiran";
	}

	@Override
	public String toString() {
		return upis;
	}

	@Override
	public void otkazanTermin() {
		this.getIzvodjenje().povracajNovca("");
		Otkazan s = new Otkazan();
		s.setIzvodjenje(this.izvodjenje);
		this.izvodjenje.promijeniStanje(s);
	}

	@Override
	public void prijavaNaTermin(String kime) {
		this.getIzvodjenje().placanje(kime);
		if(this.izvodjenje.brMjesta==0){
			Popunjen s = new Popunjen();
			s.setIzvodjenje(this.izvodjenje);
			this.izvodjenje.promijeniStanje(s);
		}
	}

	@Override
	public void odjavaOdTermina(String kime) {
		this.getIzvodjenje().povracajNovca(kime);
	}

	@Override
	public void poceloIzvodjenje() {
		UToku s = new UToku();
		s.setIzvodjenje(this.getIzvodjenje());
		this.getIzvodjenje().promijeniStanje(s);
	}

	@Override
	public void zavrsenoIzvodjenje() {
		// TODO Auto-generated method stub
		
	}

	
	

}
