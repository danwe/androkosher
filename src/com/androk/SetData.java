package com.androk;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.widget.Toast;

public class SetData extends Activity
{
	public void executeHttpPost() throws Exception {
		
		Toast.makeText(this, "CREATING REQEST..",Toast.LENGTH_LONG).show();
		Toast.makeText(this, "CREATING REQEST...",Toast.LENGTH_LONG).show();	
		
		try {
			HttpClient client = new DefaultHttpClient();
			HttpPost request = new HttpPost("http://androkosher.x10.mx/set.php");
			
			List<NameValuePair> postParameters = new ArrayList<NameValuePair>(2);
			postParameters.add(new BasicNameValuePair("u", "michal"));
			postParameters.add(new BasicNameValuePair("p", "michal123"));
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);
			
			request.setEntity(formEntity);
			client.execute(request);
		
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
