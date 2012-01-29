/*This class was written by: Or Asnin
 * Login to application.
*/

package com.androk;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class AndroKActivity extends Activity implements OnClickListener
{
	public Login login;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.premiss);
		
		setContentView(R.layout.main);
		
		login = new Login();
		
		View loginButton=findViewById(R.id.login_button);
		loginButton.setOnClickListener(this);
	}
	

	public void onClick(View v)
	{
		EditText userTx = (EditText)findViewById(R.id.editText2);
		String user = userTx.getText().toString();
		
		EditText passwordTx = (EditText)findViewById(R.id.editText1);
		String password = passwordTx.getText().toString();
		
		Toast.makeText(this,"Connecting...",Toast.LENGTH_LONG).show();
	
		try {
				if(login.executeHttpGet(user.toString(), password.toString())){
					Toast.makeText(this,"Authentication Granted",Toast.LENGTH_LONG).show();
					Intent i = new Intent(this, Menu.class);
					startActivity(i);
			}
			else
				Toast.makeText(this,"Authentication failed!",Toast.LENGTH_LONG).show();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
}



