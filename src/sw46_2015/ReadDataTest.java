package sw46_2015;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Test;

import model.FileHandler;

public class ReadDataTest {
//
	@Test
	public void readDataTest() throws NumberFormatException, IOException, ParseException {
		boolean d = FileHandler.ucitavanje();
		System.out.println(d);
		assertTrue("Neuspelo ucitavanje", d);
	}


}
