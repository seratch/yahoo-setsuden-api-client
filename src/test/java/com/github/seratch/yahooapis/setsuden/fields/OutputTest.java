package com.github.seratch.yahooapis.setsuden.fields;

import com.github.seratch.yahooapis.setsuden.fields.Output.*;
import static org.junit.Assert.*;
import org.junit.Test;


public class OutputTest {

	@Test
	public void type() throws Exception {
		assertNotNull(Output.class);
	}

	@Test
	public void toString_A$() throws Exception {
		Output target = Output.xml;
		String actual = target.toString();
		String expected = "xml";
		assertEquals(expected, actual);
	}

}
