package server;

import java.io.BufferedReader;
import java.util.Enumeration;
import java.util.Map;

import util.RequestPath;

/**
 * 
 * Inspired by HttpServletRequest
 *
 */

public class HttpRequest {
	private String verb;
	private String protocolVersion;
	private String url;
	private Map<String, String> headers;
	private RequestPath requestPath;
	
	public void setUrl(String url) throws Exception {
		if(url.contains("../"))
			// security concert (by default chrome removes the ..)
			throw new Exception("../ not allowed");
		this.url = url;
	}
	
	public void setRequestPath(RequestPath p) {
		requestPath = p;
	}
	
	public String getParameter(String name) {
		return requestPath.getParameter(name);
	}
	
	public void setVerb(String verb) {
		this.verb = verb;
	}

	public void setProtocolVersion(String protocolVersion) {
		this.protocolVersion = protocolVersion;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public HttpRequest() {
		
	}
	
	public HttpRequest(String verb,String protocoVersion, Map<String, String> headers) {
		this.verb = verb;
		this.protocolVersion = protocolVersion;
		this.headers = headers;
	}
	
	public String getHeader(String name) {
		return headers.get(name);
	}
	
	public String getMethod() {
		return verb;
	}
	
	public StringBuffer getRequestUrl() {
		return new StringBuffer(url);
	}
	
	public BufferedReader getReader() {
		return null; // TODO
	}
	
}
