package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/*
 * Simple parser: read 512 bytes, without interpreting them, 
 * then return the request in a string format
 * TODO: implement an HTTP parser returning a HttpRequest object.
 * (see https://docs.oracle.com/javaee/6/api/javax/servlet/http/HttpServletRequest.html)
 */
public class SimpleParser {

	public static String parse(InputStream is) throws IOException {
		InputStreamReader isr = new InputStreamReader(is);
		char[] buffer = new char[1024];
		isr.read(buffer);
		return new String(buffer);
	}
}
