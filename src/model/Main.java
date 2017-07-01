package model;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
	public static ArrayList<Korisnik> korisnici = new ArrayList<Korisnik>();
	public static Korisnik trenutni = new Korisnik();
	public static ArrayList<Obilazak> sviObilasci = new ArrayList<Obilazak>();
	public static ArrayList<Grad> gradovi = new ArrayList<Grad>();
	
	public static void main(String[] args) {
		Aplikacija ap = new Aplikacija();
		try {
			ap.ucitavanje();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
