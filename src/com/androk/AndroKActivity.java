package com.androk;



import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;


import android.location.Location;

import android.widget.Button;

public class AndroKActivity extends Activity implements OnClickListener
{
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		View loginButton=findViewById(R.id.login_button);
		loginButton.setOnClickListener(this);
			
	}
	

	public void onClick(View v)
	{
		
		Intent i = new Intent(this, login.class);
		startActivity(i);
	
	}
	
}



