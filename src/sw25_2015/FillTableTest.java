package sw25_2015;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import jdk.nashorn.internal.AssertsEnabled;
import model.Aplikacija;
import view.StartWindow;
import view.TourWindow;

public class FillTableTest {

	
	Aplikacija a;
	@Before
	public void prepare() throws NumberFormatException, ParseException, IOException{
		a = new Aplikacija();
		a.pokreni();
	}
	
	@Test
	public void test() {
		StartWindow s =a.ex;
		s.t = new TourWindow("1");
		boolean k= s.t.fillTable();
		assertEquals(true, k);
	}

}
