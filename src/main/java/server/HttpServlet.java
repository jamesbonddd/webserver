package server;

import java.io.IOException;

public abstract class HttpServlet {
	public abstract void doGet(HttpRequest request,HttpResponse response) throws Exception;
}
