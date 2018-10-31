package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Callable;

public class ClientHandler implements Callable<Void> {

	private Socket socket;
	private RequestDispatcher dispatcher;
	
	public ClientHandler(Socket s) {
		socket = s;
		dispatcher = new RequestDispatcher();
	}
	
	public Void call() throws Exception {
		InputStream is = socket.getInputStream();
		OutputStream os = socket.getOutputStream();
		
		// Parse request
		HttpRequest request = HttpParser.parse(is);
		HttpResponse response = new HttpResponse(os);
		System.out.println(request.getMethod() + ": " + request.getRequestUrl().toString());
	
		// Dispatch request to the proper controller
		dispatcher.dispatchRequest(request, response);
		
		socket.close();
		return null;
	}
	
	/**
	 * Sample response to display on browser
	 */
	private static byte[] getContent(String val) throws IOException{
		File sampleResponse = new File("response.txt");
		int fileSize = (int) sampleResponse.length();
		byte[] ret = new byte[fileSize];
		FileInputStream fis = new FileInputStream(sampleResponse);
		fis.read(ret);
		return ret;	
	}

}
