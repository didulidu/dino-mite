package model;

public class Registrovan extends StanjeKorisnika{
	
	public Registrovan(){
		upis = "registrovan";
	}

	@Override
	public void izbrisanKorisnik() {
		
	}

	@Override
	public String toString() {
		return upis;
	}
	
	

	
}
