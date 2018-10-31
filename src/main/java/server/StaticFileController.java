package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import util.RequestPath;

public class StaticFileController  extends HttpServlet {

	@Override
	public void doGet(HttpRequest request, HttpResponse response) {
		String url = request.getRequestUrl().toString();
		RequestPath rp = new RequestPath(url);
		String path = Config.STATIC_PATH + "/" +  rp.getPath();
		File requestedFile = new File(path);
		if(!requestedFile.exists()) {
			response.sendError(404);
		}else {
			response.setHeader("Content-Type", "text/html");
			PrintWriter writer = response.getWriter();
			try {
				BufferedReader reader = new BufferedReader(new FileReader(requestedFile));
				String buffer;
				while((buffer = reader.readLine()) != null) {
					writer.println(buffer);
				}
				writer.flush();
			}catch(Exception e) {
				// TODO
			}
		}	
	}
}