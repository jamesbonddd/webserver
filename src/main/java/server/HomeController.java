package server;

import java.io.IOException;
import java.io.PrintWriter;

public class HomeController extends HttpServlet{

	@Override
	public void doGet(HttpRequest request, HttpResponse response) throws IOException {
		try {
			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age"));
			ExampleModel model = new ExampleModel(name,age);
			response.sendView("template.mustache", model);
		} catch (Exception e) {
			response.sendView("error.mustache",new ErrorResponse("invalid get parameters (?name=x&age=y)") );
		}
	}
}

class ExampleModel{
	private String name;
	private int age;
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	public ExampleModel(String name,int age) {
		this.name = name;
		this.age = age;
	}
}

class ErrorResponse{
	public String message;
	public ErrorResponse(String msg) {
		message = msg;
	}
}
