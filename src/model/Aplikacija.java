package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import view.StartWindow;

public class Aplikacija {
	
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
        
        
        
        //na kraju
        try {
			FileHandler.upisUFajl();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
