package com.github.seratch.yahooapis.setsuden.exception;

import com.github.seratch.yahooapis.setsuden.exception.ClientException.*;
import static org.junit.Assert.*;
import org.junit.Test;


public class ClientExceptionTest {

	@Test
	public void type() throws Exception {
		assertNotNull(ClientException.class);
	}

	@Test
	public void instantiation() throws Exception {
		String message = null;
		Throwable cause = null;
		ClientException target = new ClientException(message, cause);
		assertNotNull(target);
	}

}
