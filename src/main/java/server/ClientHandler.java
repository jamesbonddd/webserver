package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.Callable;

public class ClientHandler implements Callable<Integer> {

	private Socket socket;
	
	public ClientHandler(Socket s) {
		socket = s;
	}
	
	public Integer call() throws Exception {
		InputStream is = socket.getInputStream();
		OutputStream os = socket.getOutputStream();
		
		// Parse request
		String request = SimpleParser.parse(is);
		System.out.println("Request:\n" + request);
		
		// Request handling code ...
		Thread.sleep(10 * 1000);
		
		// TODO: Abstract response write operations by implementing 
		// an HttpResponse object.
		os.write(getContent(request));
		socket.close();
		return 0;
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
