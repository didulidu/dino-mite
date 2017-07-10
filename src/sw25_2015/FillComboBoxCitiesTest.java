package sw25_2015;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.Aplikacija;
import view.StartWindow;

public class FillComboBoxCitiesTest {

	Aplikacija a;
	@Before
	public void prepare() throws NumberFormatException, ParseException, IOException{
		a = new Aplikacija();
		a.pokreni();
	}
	
	@Test
	public void test() {
		ArrayList<String> l = a.ex.popuni();
		assertEquals(l.get(0), "Modrica");
		assertEquals(l.get(1), "Novi Sad");
	}

}
