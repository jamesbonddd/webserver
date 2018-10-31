package server;

import java.io.IOException;
import java.io.PrintWriter;

public class HomeController extends HttpServlet{

	@Override
	public void doGet(HttpRequest request, HttpResponse response) {
		try {
			ExampleModel model = new ExampleModel("Jeff",24);
			response.sendView("template.mustache", model);
		} catch (IOException e) {} // TODO
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
