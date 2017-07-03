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
		Otkazan s = new Otkazan();
		s.setIzvodjenje(this.izvodjenje);
		this.izvodjenje.promijeniStanje(s);
	}

	@Override
	public void prijavaNaTermin() {
		if(this.izvodjenje.brMjesta==0){
			Popunjen s = new Popunjen();
			s.setIzvodjenje(this.izvodjenje);
			this.izvodjenje.promijeniStanje(s);
		}
	}

	@Override
	public void odjavaOdTermina() {
		//ne radi nista, ostaje u ovom stanju
	}

	@Override
	public void poceloIzvodjenje() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void zavrsenoIzvodjenje() {
		// TODO Auto-generated method stub
		
	}

	
	

}
