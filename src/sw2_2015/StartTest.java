package sw2_2015;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Test;

import model.Aplikacija;

public class StartTest {

	
	
	@Test
	public void registrationTest() throws NumberFormatException, ParseException, IOException {
		Aplikacija a = new Aplikacija();
		assertTrue(a.pokreni());
	}

}
