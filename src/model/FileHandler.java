package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileHandler {
	protected static String sep = System.getProperty("file.separator");
	
	public static void ucitavanje() throws IOException{
		String linija;
		String[] lista;
		
		//ucitavanje registrovanih korisnika
		File fajl = new File("src/fajlovi/korisnici.txt");
		if(!fajl.exists()){
			fajl.createNewFile();
		}
		BufferedReader cit = new BufferedReader(new FileReader(fajl.getAbsolutePath()));
		while((linija=cit.readLine())!=null){
			lista = linija.split("\\|");
			Osoba os = new Osoba(lista[2], lista[3], lista[4], lista[5]);
			Korisnik kor = new Korisnik(lista[0], lista[1], os, true);
			Main.korisnici.put(lista[0], kor);
		}
		cit.close();
		
		//ucitavanje gradova i lokacija
		fajl = new File("."+sep+"src"+sep+"fajlovi"+sep+"gradovi.txt");
		if(!fajl.exists()){
			fajl.createNewFile();
		}
		cit = new BufferedReader(new FileReader(fajl.getAbsolutePath()));
		while((linija=cit.readLine())!=null){
			lista = linija.split("\\|");
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
			Main.gradovi.put(lista[0], g);
		}
		cit.close();
		
		//ucitavanje obilazaka
		fajl = new File("."+sep+"src"+sep+"fajlovi"+sep+"obilasci.txt");
		if(!fajl.exists()){
			fajl.createNewFile();
		}
		cit = new BufferedReader(new FileReader(fajl.getAbsolutePath()));
		while((linija=cit.readLine())!=null){
			lista = linija.split("\\|");
			Obilazak ob = new Obilazak();
			ob.setIdOb(lista[0]);
			ob.setGrad(Main.gradovi.get(lista[1]));
			ob.setVodic(Main.korisnici.get(lista[2]));
			String[] help = lista[3].split(";");
			ArrayList<Lokacija> temp = new ArrayList<Lokacija>();
			for(String i : help){
				for(Lokacija lok : ob.getGrad().getLokacije()){
					if(i.compareTo(lok.getNaziv())==0){
						temp.add(lok);
						break;
					}
				}
			}
			ob.setLokacije(temp);
			
			help = lista[4].split(";");
			ArrayList<Korisnik> turisti = ob.getTuristiPrisutni();
			for(String i : help){
				ArrayList<Obilazak> turisticki = Main.korisnici.get(i).getTurista();
				turisticki.add(ob);
				Main.korisnici.get(i).setTurista(turisticki);
				turisti.add(Main.korisnici.get(i));
			}
			ArrayList<Obilazak> vodic = Main.korisnici.get(lista[2]).getVodic();
			vodic.add(ob);
			Main.korisnici.get(lista[2]).setVodic(vodic);
			ob.setTuristiPrisutni(turisti);
			Main.sviObilasci.put(lista[0], ob);
		}
		cit.close();
		
		fajl = new File("."+sep+"src"+sep+"fajlovi"+sep+"izvodjenja.txt");
		cit = new BufferedReader(new FileReader(fajl.getCanonicalPath()));
		while((linija=cit.readLine())!=null){
			lista = linija.split("\\|");
			Izvodjenje izv = new Izvodjenje();
			System.out.println(lista[0]);
			ArrayList<Izvodjenje> temp = Main.sviObilasci.get(lista[0]).getIzvodjenja();
			izv.setObilazak(Main.sviObilasci.get(lista[0]));
			
			izv.setIdIzv(lista[1]);
			Integer br = Integer.parseInt(lista[2]);
			izv.setBrMjesta(br);
			if(lista[3].compareTo("zakazan")==0){
				Zakazan s = new Zakazan();
				izv.setStanje(s);
			}else if(lista[3].compareTo("utoku")==0){
				UToku s = new UToku();
				izv.setStanje(s);
			}else if(lista[3].compareTo("zavrsen")==0){
				Zavrsen s = new Zavrsen();
				izv.setStanje(s);
			}else if(lista[3].compareTo("otkazan")==0){
				Otkazan s = new Otkazan();
				izv.setStanje(s);
			}
			String[] help = lista[4].split(";");
			ArrayList<Korisnik> turisti = izv.getTuristi();
			for(String i : help){
				turisti.add(Main.korisnici.get(i));
			}
			izv.setTuristi(turisti);
			temp.add(izv);
			Main.sviObilasci.get(lista[0]).setIzvodjenja(temp);
		}
	}
	
	public static void upisUFajl() throws IOException{
		File fajl = new File("."+sep+"src"+sep+"fajlovi"+sep+"korisnici.txt");
		PrintWriter pis = new PrintWriter(new FileWriter(fajl.getAbsolutePath()));
		String linija;
		for(Korisnik k : Main.korisnici.values()){
			pis.write(k.toString());
		}
		pis.close();
		fajl = new File("."+sep+"src"+sep+"fajlovi"+sep+"gradovi.txt");
		pis = new PrintWriter(new FileWriter(fajl.getAbsolutePath()));
		for(Grad g : Main.gradovi.values()){
			pis.write(g.toString());
		}
		pis.close();
		fajl = new File("."+sep+"src"+sep+"fajlovi"+sep+"obilasci.txt");
		pis = new PrintWriter(new FileWriter(fajl.getAbsolutePath()));
		fajl = new File("."+sep+"src"+sep+"fajlovi"+sep+"izvodjenja.txt");
		PrintWriter pis2 = new PrintWriter(new FileWriter(fajl.getAbsolutePath()));
		for(Obilazak o : Main.sviObilasci.values()){
			pis.write(o.toString());
			for(Izvodjenje izv : o.getIzvodjenja()){
				pis2.write(izv.toString());
			}
		}
		pis.close();
		pis2.close();
	}
}