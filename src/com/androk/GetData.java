package com.androk;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class GetData {
	
	public String [] executeHttpGet() throws Exception {
		BufferedReader in = null;
		
			try {
				HttpClient client = new DefaultHttpClient();
				HttpGet request = new HttpGet();
				request.setURI(new URI("http://androkosher.x10.mx/get.php"));
				HttpResponse response = client.execute(request);
				in = new BufferedReader
						(new InputStreamReader(response.getEntity()
								.getContent()));
				StringBuffer sb = new StringBuffer("");
				String line = "";
				String NL = System.getProperty("line.separator");
				
				while ((line = in.readLine()) != null) {
					sb.append(line + NL);
				}
				
				in.close();
				
				String [] splitted = (sb.toString()).split("<br/>");
				
				return splitted;
				} finally {
					if (in != null) {
						try {
							in.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			
		}
}
					
			