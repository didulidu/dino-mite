package model;

public abstract class Stanje {
	protected Izvodjenje izvodjenje;
	protected String upis;
	
	public abstract void placanje();
	public abstract void povracajNovca();
	
}
