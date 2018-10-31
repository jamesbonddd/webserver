package server;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import util.HttpStatus;

/**
 * 
 * Inspired by HttpServletResponse with an additional method: sendView
 * which allows to render views server side as a replacement for JSPs 
 * Rendering is done using mustache templating engine.
 * 
 */

public class HttpResponse {
	
	private PrintWriter writer;
	private boolean setHeaderIgnore;
	private boolean setStatusIgnore;
	
	public HttpResponse(OutputStream os) {
		writer = new PrintWriter(os,false);
		setHeaderIgnore = false;
	}

	private void setFirstLine(int status) {
		writer.println("HTTP/1.0 "+ status + " " + new HttpStatus().getText(status));
		writer.flush();
		setStatusIgnore = true;
	}
	
	public void sendError(int sc) {
		if(!setStatusIgnore)
			setFirstLine(sc);
		else
			System.out.println("warning: cannot send error code after setting status or header");
	}
	
	public void setHeader(String name, String value) {
		if(!setStatusIgnore) {
			setFirstLine(200); // default : 200 OK
		}
		
		if(! setHeaderIgnore) { 
			writer.println(name + ":" + " " + value);
		}else {
			System.out.println("warning: cannot set header after writing to body");
		}
			
	}
	
	public void setStatus(int statusCode) {
		if(!setStatusIgnore) 
			setFirstLine(statusCode);
	}
	
	public PrintWriter getWriter() {
		setFirstLine(200);
		setHeaderIgnore = true;
		writer.println(""); // end header
		return writer;
	}
	
	/**
	 * 
	 * renders a view server side and then sends it in the response body
	 * 
	 * @param name: the name of the view to be rendered, by default the view will be searched in the
	 * web-content directory
	 * @param o: parameter passed to the view
	 * @throws IOException 
	 * 
	 */
	public void sendView(String name, Object o) throws IOException {
	    setHeader("Content-Type", "text/html");
		MustacheFactory mf = new DefaultMustacheFactory();
	    Mustache mustache = mf.compile(Config.TEMPLATES_PATH +  "/"  + name);
	    mustache.execute(getWriter(), o).flush();
	}
}
