package sw46_2015;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import model.Aplikacija;
import model.FileHandler;

public class LoadDataTest {
	
	@Before
	public void prepare() throws NumberFormatException, IOException, ParseException{
		FileHandler.ucitavanje();
	}
	
	@Test
	public void testLoadData() throws IOException {
		boolean d=FileHandler.upisUFajl();
		System.out.println(d);
		assertTrue("Neuspesno ucitavanje u fajl", d);
;	}

}
