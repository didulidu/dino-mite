package sw2_2015;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import model.Aplikacija;
import model.Korisnik;
import view.StartWindow;

public class TestLogIn {

	Aplikacija ap = new Aplikacija();
	
	@Before
	public void configureWindow() throws NumberFormatException, ParseException, IOException{
		ap.pokreni();
//		ap.ex.getUserT().setText("maery");
//		ap.ex.getPassT().setText("pass1");
//		ap.ex.setPassword("pass1");
//		ap.ex.setUsername("maery");
		ap.ex.setVisible(false);
	}
	
	@Test
	public void testCheckUsernameAndPasswordPositive() {
		assertTrue(ap.ex.checkUsernameAndPassword("maery", "pass1"));
	}
	
	@Test
	public void testCheckUsernameAndPasswordNegative() {
		assertFalse(ap.ex.checkUsernameAndPassword("dummy user", "with some dummy password"));
	}
	
	@Test
	public void testCheckUsernameAndPasswordNegative_2() {
		assertFalse(ap.ex.checkUsernameAndPassword("vladsy", "with some dummy password"));
	}
	
	@Test
	public void testCheckUsernameAndPasswordNegative_3() {
		assertFalse(ap.ex.checkUsernameAndPassword("dummy user", "pass1"));
	}

}
