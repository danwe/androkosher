package com.androk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Menu extends Activity implements OnClickListener
{
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);

		View b_map=findViewById(R.id.bmap);
		b_map.setOnClickListener(this);
		View b_search=findViewById(R.id.bsearch);
		b_search.setOnClickListener(this);
		View b_fav=findViewById(R.id.bfav);
		b_fav.setOnClickListener(this);


	}

	public void onClick(View v)
	{	


		switch(v.getId()){

		case R.id.bmap:
			Intent intent = new Intent(this, mymap.class);
			this.startActivity(intent);
			break;

		case R.id.bsearch: 
			Intent intent2 = new Intent(this, Search.class);
			this.startActivity(intent2);
			break;
		case R.id.bfav: 
			finish();
			break;


		}

	}


}