package model;

public class AktuelanObilazak extends StanjeObilaska {

	public AktuelanObilazak() {
		upis="aktuelan";
	}

	@Override
	public void izbrisanObilazak() {
		NeaktuelanObilazak s = new NeaktuelanObilazak();
		s.setObilazak(obilazak);
		obilazak.promeniStanje(s);

	}

	@Override
	public String toString() {
		return upis;
	}
	
	

}
