package server;

public abstract class HttpServlet {
	public abstract void doGet(HttpRequest request,HttpResponse response);
}
