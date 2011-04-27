package com.github.seratch.yahooapis.setsuden.fields;

import com.github.seratch.yahooapis.setsuden.fields.Area.*;
import static org.junit.Assert.*;
import org.junit.Test;


public class AreaTest {

	@Test
	public void type() throws Exception {
		assertNotNull(Area.class);
	}

	@Test
	public void toString_A$() throws Exception {
		Area target = Area.tokyo;
		String actual = target.toString();
		String expected = "tokyo";
		assertEquals(expected, actual);
	}

}
