/*This class was written by: Or Asnin
 * Get user name &password from server.
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

import android.widget.Toast;

public class Login {
	public boolean executeHttpGet(String user, String password) throws Exception {
	BufferedReader in = null;
	
	try {
		HttpClient client = new DefaultHttpClient();
		HttpPost request = new HttpPost("http://androkosher.x10.mx/login.php");
		
		List<NameValuePair> postParameters = new ArrayList<NameValuePair>(2);
		postParameters.add(new BasicNameValuePair("u", user.toString()));
		postParameters.add(new BasicNameValuePair("p", password.toString()));
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
		//Check values:
		if(splitted[0].equals(user) && splitted[1].equals(password))
			return true;
		
		return false;
		
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
