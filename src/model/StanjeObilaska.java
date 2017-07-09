package model;

public abstract class StanjeObilaska {
	protected Obilazak obilazak;
	protected String upis;
	
	public abstract void izbrisanObilazak();
	

	public Obilazak getObilazak() {
		return obilazak;
	}


	public void setObilazak(Obilazak obilazak) {
		this.obilazak = obilazak;
	}



	public String getUpis() {
		return upis;
	}

	public void setUpis(String upis) {
		this.upis = upis;
	}
}
