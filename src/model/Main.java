package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	public static Korisnik trenutni = new Korisnik();
	public static HashMap<String, Korisnik> korisnici = new HashMap<String, Korisnik>();
	public static HashMap<String, Obilazak> sviObilasci = new HashMap<String, Obilazak>();
	public static HashMap<String, Grad> gradovi = new HashMap<String, Grad>();
	
	public static void main(String[] args) {
		Aplikacija ap = new Aplikacija();
		try {
			ap.ucitavanje();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
