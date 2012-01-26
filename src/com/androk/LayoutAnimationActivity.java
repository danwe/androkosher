/*This class was written by: Or Asnin
 * Display data from mySql server.
*/

package com.androk;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class LayoutAnimationActivity extends Activity
{
	public Search search;
	String value="";
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_layout);
		search = new Search();
		Bundle extras = getIntent().getExtras();
		
		     setupListView(extras);
	}
	private void setupListView(Bundle extras)
	{
		String[] listItems = null;
		
		try {
				if(extras !=null)
					value = extras.getString("new_variable_name").toString();
			
			
			//Get server data:
			listItems = search.executeHttpGet("Resturants_Jerusalem", value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayAdapter<Object> listItemAdapter =
				new ArrayAdapter<Object>(this
				,android.R.layout.simple_list_item_1
				,listItems);
				ListView lv = (ListView)this.findViewById(R.id.list_view_id);
				lv.setAdapter(listItemAdapter);
	}

}