package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import view.StartWindow;
//import view.TourWindow;
import view.TourWindow;

public class Aplikacija {
	public static Korisnik trenutni = null;
	public static HashMap<String, Korisnik> korisnici = new HashMap<String, Korisnik>();
	public static HashMap<String, Obilazak> sviObilasci = new HashMap<String, Obilazak>();
	public static HashMap<String, Grad> gradovi = new HashMap<String, Grad>();
	
	
	private static Aplikacija instance = null;
	
	public static Aplikacija getInstance(){
		if (instance==null){
			instance = new Aplikacija();
		}
		return instance;
	}
	
	void pokreni() throws NumberFormatException, ParseException{
		try {
			FileHandler.ucitavanje();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	StartWindow ex = new StartWindow();
        ex.setVisible(true);
        //TourWindow tw = new TourWindow("1");
        //tw.setVisible(true);
        
        
        //na kraju
        /*try {
			FileHandler.upisUFajl();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	
}
