package model;

public class Lokacija {
	protected String naziv;
	
	public Lokacija(){
		
	}

	public Lokacija(String naziv) {
		super();
		this.naziv = naziv;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	
}
