package model;
//pamtiti i izvodjenja obilazaka u fajlu?
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Aplikacija {
	
	private static Aplikacija instance = null;
	
	public static Aplikacija getInstance(){
		if (instance==null){
			instance = new Aplikacija();
		}
		return instance;
	}
	
	void ucitavanje() throws IOException{
		String sep = System.getProperty("file.separator");
		String linija;
		String[] lista;
		
		//ucitavanje registrovanih korisnika
		File fajl = new File("."+sep+"src"+sep+"fajlovi"+sep+"korisnici.txt");
		if(!fajl.exists()){
			fajl.createNewFile();
		}
		BufferedReader cit = new BufferedReader(new FileReader(fajl.getAbsolutePath()));
		while((linija=cit.readLine())!=null){
			lista = linija.split("\\|");
			Osoba os = new Osoba(lista[2], lista[3], lista[4], lista[5]);
			Korisnik kor = new Korisnik(lista[0], lista[1], os, true);
			Main.korisnici.add(kor);
		}
		cit.close();
		
		//ucitavanje gradova i lokacija
		fajl = new File("."+sep+"src"+sep+"fajlovi"+sep+"gradovi.txt");
		if(!fajl.exists()){
			fajl.createNewFile();
		}
		cit = new BufferedReader(new FileReader(fajl.getAbsolutePath()));
		while((linija=cit.readLine())!=null){
			lista = linija.split("|");
			Grad g = new Grad(lista[0]);
			ArrayList<Lokacija> temp = g.getLokacije();
			for(int i =0;i<lista.length;i++){
				if(i==0){
					continue;
				}
				Lokacija lok = new Lokacija(lista[i]);
				temp.add(lok);
			}
			g.setLokacije(temp);
			Main.gradovi.add(g);
		}
		
		cit.close();
		
		//ucitavanje obilazaka
		fajl = new File("."+sep+"src"+sep+"fajlovi"+sep+"obilasci.txt");
		if(!fajl.exists()){
			fajl.createNewFile();
		}
		cit = new BufferedReader(new FileReader(fajl.getAbsolutePath()));
		boolean pronadjen = false;
		while((linija=cit.readLine())!=null){
			lista = linija.split("\\|");
			Obilazak ob = new Obilazak();
			ob.setIdOb(lista[0]);
			for(Grad g : Main.gradovi){
				if(g.getNaziv().compareTo(lista[1])==0){
					pronadjen = true;
					ob.setGrad(g);
					break;
				}
			}
			if(!pronadjen){
				//greska, kako?
				return;
			}
			pronadjen = false;
			for(Korisnik kor : Main.korisnici){
				if(kor.getKorisnickoIme().compareTo(lista[2])==0){
					pronadjen = true;
					ob.setVodic(kor);
					break;
				}
			}
			if(!pronadjen){
				//greska, kako?
				return;
			}
			String[] loks = lista[3].split(";");
			ArrayList<Lokacija> temp = new ArrayList<Lokacija>();
			for(String i : loks){
				for(Lokacija lok : ob.getGrad().getLokacije()){
					if(i.compareTo(lok.getNaziv())==0){
						temp.add(lok);
						break;
					}
				}
			}
			ob.setLokacije(temp);
			//izvodjenja obilazaka?
			
			Main.sviObilasci.add(ob);
		}
	}
}
