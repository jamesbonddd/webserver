package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import util.RequestPath;

public class HttpParser{

	public static HttpRequest parse(InputStream is) throws Exception {
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		HttpRequest request = new HttpRequest();

		// first line
		String[] words = br.readLine().split(" ");
		if(words.length !=3) 
			throw new Exception("protocol error");		
		request.setVerb(words[0]);
		request.setProtocolVersion(words[2]);
		RequestPath rp = new RequestPath(words[1]);
		request.setUrl(rp.getPath());
		request.setRequestPath(rp);
		
		// next lines
		Map<String, String> headers = new HashMap<String, String>();
		String line;
		while(true) {
			line = br.readLine();
			if(line == null || line.equals("") )
				break;
			String key = line.split(":")[0];
			String value = line.split(":")[1];
			headers.put(key, value);
		}
		request.setHeaders(headers);
		return request;
	}

}