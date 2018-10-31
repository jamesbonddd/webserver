package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Dispatches requests to the proper HttpServlet
 * by default, if no servlet is registered, the request 
 * will be handled by StaticFileController which is a static file server.
 *  
 * For now mapping is hard coded into the constructor
 * TODO: parse mapping from web.xml file, or using annotations ...
 *
 */
public class RequestDispatcher {

	private  Map<String, HttpServlet> servlets;
	private  HttpServlet defaultStaticFileServer;
	
	public RequestDispatcher() {
		servlets = new HashMap<String, HttpServlet>();
		servlets.put("/home", new HomeController());
		defaultStaticFileServer = new StaticFileController();
	}
	
	public void dispatchRequest(HttpRequest request, HttpResponse response) throws Exception {
		HttpServlet target = servlets.get(request.getRequestUrl().toString());
		if(target != null) {
			target.doGet(request, response);
		}else {
			if(request.getRequestUrl().toString().equals("/"))
				request.setUrl("/index.html");
			defaultStaticFileServer.doGet(request,response);
		}
	}
}
