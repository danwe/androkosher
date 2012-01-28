/*This class was written by: Or Asnin
 * Implements data from mySql server.
*/

package com.androk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class GetData {
	
	public String [] executeHttpGet(String table, String name, String street) throws Exception {
		BufferedReader in = null;
		
			try {
				HttpClient client = new DefaultHttpClient();
				HttpPost request = new HttpPost("http://androkosher.x10.mx/search.php");
				
				List<NameValuePair> postParameters = new ArrayList<NameValuePair>(3);
				postParameters.add(new BasicNameValuePair("c", table));
				postParameters.add(new BasicNameValuePair("n", name));
				postParameters.add(new BasicNameValuePair("s", street));
				UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);
				
				request.setEntity(formEntity);
				
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
