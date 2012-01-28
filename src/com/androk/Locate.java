/*This class was written by: Or Asnin
 * Get restaurants details.
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

public class Locate {
	public String [][] executeHttpGet(String city) throws Exception {
		BufferedReader in = null;
		
			try {
				HttpClient client = new DefaultHttpClient();
				HttpPost request = new HttpPost("http://androkosher.x10.mx/locate.php");
				
				List<NameValuePair> postParameters = new ArrayList<NameValuePair>(1);
				postParameters.add(new BasicNameValuePair("c", city));
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
				
				return getPoints(sb);
				
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
	
		public String [][]getPoints(StringBuffer sb){
			String [] splitted = (sb.toString()).split("<br/><br/>");
			int COLS = 4;
			int ROW = splitted.length;
			String [][] location = new String[ROW][COLS];
			
			for(int i=0; i<ROW; i++){
				    String [] spli =(splitted[i].toString()).split("<br/>");
				    location[i] = spli;
			}
			
			return location;
			
		}


}
