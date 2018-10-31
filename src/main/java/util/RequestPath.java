package util;

import java.util.HashMap;
import java.util.Map;

public class RequestPath {
	
	private String query;
	private String path;
	private Map<String , String> queryParams;
	
	public RequestPath(String path) {
		String[] testQuery = path.split("\\?");
		if(testQuery.length == 1) {
			query = null;
			this.path = path;
		}else {
			query = testQuery[1];
			this.path = testQuery[0];
		}
		
		// parse query parameters
		queryParams = null;
		if(query != null) {
			queryParams = new HashMap<String, String>();
			String[] params = query.split("&");
			for(String param : params) {
				String[] split = param.split("=");
				queryParams.put(split[0], split[1]);
			}
		}
	}
	
	public String getPath() {
		return path;
	}
	
	public String getQuery() {
		return query;
	}
	
	public String getParameter(String name) {
		if(queryParams == null)
			return null;
		return queryParams.get(name);
	}

}
