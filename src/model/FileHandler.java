package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class FileHandler {
	protected static String sep = System.getProperty("file.separator");
	
	public static void ucitavanje() throws IOException, NumberFormatException, ParseException{
		String linija;
		String[] lista;
		
		SimpleDateFormat datRodj = new SimpleDateFormat("dd.MM.YYYY.");
		SimpleDateFormat termin = new SimpleDateFormat("dd.MM.YYYY. HH:mm");
		
		//ucitavanje registrovanih korisnika
		File fajl = new File("src/fajlovi/korisnici.txt");
		if(!fajl.exists()){
			fajl.createNewFile();
		}
		BufferedReader cit = new BufferedReader(new FileReader(fajl.getAbsolutePath()));
		while((linija=cit.readLine())!=null){
			lista = linija.split("\\|");
			Osoba os = new Osoba(lista[2], lista[3], lista[4], lista[5],datRodj.parse(lista[6]),lista[7],Double.parseDouble(lista[8]),lista[9]);
			Korisnik kor = new Korisnik(lista[0], lista[1], os, true);
			Aplikacija.korisnici.put(lista[0], kor);
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
			Aplikacija.gradovi.put(lista[0], g);
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
			Integer br = Integer.parseInt(lista[1]);
			ob.setBrMjesta(br);
			ob.setNaziv(lista[2]);
			ob.setGrad(Aplikacija.gradovi.get(lista[3]));
			ob.setVodic(Aplikacija.korisnici.get(lista[4]));
			String[] help = lista[5].split(";");
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
			
			help = lista[6].split(";");
			ArrayList<Korisnik> turisti = ob.getTuristiPrisutni();
			for(String i : help){
				ArrayList<Obilazak> turisticki = Aplikacija.korisnici.get(i).getTurista();
				turisticki.add(ob);
				Aplikacija.korisnici.get(i).setTurista(turisticki);
				turisti.add(Aplikacija.korisnici.get(i));
			}
			ArrayList<Obilazak> vodic = Aplikacija.korisnici.get(lista[4]).getVodic();
			vodic.add(ob);
			Aplikacija.korisnici.get(lista[4]).setVodic(vodic);
			ob.setTuristiPrisutni(turisti);
			Aplikacija.sviObilasci.put(lista[0], ob);
		}
		cit.close();
		
		fajl = new File("."+sep+"src"+sep+"fajlovi"+sep+"izvodjenja.txt");
		cit = new BufferedReader(new FileReader(fajl.getCanonicalPath()));
		while((linija=cit.readLine())!=null){
			lista = linija.split("\\|");
			String[] help = lista[0].split("\\.");
			Izvodjenje izv = new Izvodjenje();
			ArrayList<Izvodjenje> temp = Aplikacija.sviObilasci.get(help[0]).getIzvodjenja();
			izv.setObilazak(Aplikacija.sviObilasci.get(help[0]));
			izv.setIdIzv(help[1]);
			izv.setBrMjesta(izv.getObilazak().getBrMjesta());
			if(lista[1].compareTo("kreiran")==0){
				Kreiran s = new Kreiran();
				izv.setStanje(s);
			}else if(lista[1].compareTo("utoku")==0){
				UToku s = new UToku();
				izv.setStanje(s);
			}else if(lista[1].compareTo("zavrsen")==0){
				Zavrsen s = new Zavrsen();
				izv.setStanje(s);
			}else if(lista[1].compareTo("otkazan")==0){
				Otkazan s = new Otkazan();
				izv.setStanje(s);
			}else if(lista[1].compareTo("popunjen")==0){
				Popunjen s = new Popunjen();
				izv.setStanje(s);
			}
			izv.getStanje().setIzvodjenje(izv);
			String[] h = lista[2].split(";");
			HashMap<Korisnik, String> turisti = izv.getTuristi();
			for(String i : h){
				String[] prijavljeniTurista = i.split("/");
				turisti.put(Aplikacija.korisnici.get(prijavljeniTurista[0]), prijavljeniTurista[1]);
			}
			izv.setTermin(termin.parse(lista[3]));
			izv.setTuristi(turisti);
			temp.add(izv);
			Aplikacija.sviObilasci.get(help[0]).setIzvodjenja(temp);
		}
		cit.close();
		
		//ucitavanje komentara
		fajl = new File("."+sep+"src"+sep+"fajlovi"+sep+"komentari.txt");
		cit = new BufferedReader(new FileReader(fajl.getAbsolutePath()));
		while((linija=cit.readLine())!=null){
			lista = linija.split("\\|");
			if (!Aplikacija.sviObilasci.get(lista[0]).getKomentari().containsKey(lista[1])){
				Aplikacija.sviObilasci.get(lista[0]).getKomentari().put(lista[1], new ArrayList<String>());
			}
			Aplikacija.sviObilasci.get(lista[0]).getKomentari().get(lista[1]).add(lista[2]);
		}
		cit.close();
	}
	
	public static void upisUFajl() throws IOException{
		File fajl = new File("."+sep+"src"+sep+"fajlovi"+sep+"korisnici.txt");
		PrintWriter pis = new PrintWriter(new FileWriter(fajl.getAbsolutePath()));
		String linija;
		for(Korisnik k : Aplikacija.korisnici.values()){
			pis.write(k.toString());
		}
		pis.close();
		fajl = new File("."+sep+"src"+sep+"fajlovi"+sep+"gradovi.txt");
		pis = new PrintWriter(new FileWriter(fajl.getAbsolutePath()));
		for(Grad g : Aplikacija.gradovi.values()){
			pis.write(g.toString());
		}
		pis.close();
		fajl = new File("."+sep+"src"+sep+"fajlovi"+sep+"obilasci.txt");
		pis = new PrintWriter(new FileWriter(fajl.getAbsolutePath()));
		fajl = new File("."+sep+"src"+sep+"fajlovi"+sep+"izvodjenja.txt");
		PrintWriter pis2 = new PrintWriter(new FileWriter(fajl.getAbsolutePath()));
		for(Obilazak o : Aplikacija.sviObilasci.values()){
			pis.write(o.toString());
			for(Izvodjenje izv : o.getIzvodjenja()){
				pis2.write(izv.toString());
			}
		}
		pis.close();
		pis2.close();
		
		fajl = new File("."+sep+"src"+sep+"fajlovi"+sep+"komentari.txt");
		pis = new PrintWriter(new FileWriter(fajl.getAbsolutePath()));
		for (Obilazak o:Aplikacija.sviObilasci.values()){
			for (String user:o.getKomentari().keySet()){
				for (String kom:o.getKomentari().get(user)){
					pis.write(o.getIdOb()+"|"+user+"|"+kom+"\n");
				}
			}
		}
		pis.close();
	}
}