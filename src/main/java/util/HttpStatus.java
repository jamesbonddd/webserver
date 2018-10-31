package util;

import java.util.HashMap;
import java.util.Map;

public class HttpStatus {

	private Map<Integer, String> statusText;
	public HttpStatus() {
		statusText = new HashMap<Integer, String>();
		statusText.put(200, "OK");
		statusText.put(201, "Created");
		statusText.put(404, "Not Found");
		statusText.put(500, "Internal Server Error");
		// ...
	}
	
	public String getText(int status) {
		return statusText.get(status);
	}
	
	
	
}
