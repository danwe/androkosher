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
	public GetData getData;
	String value="";
	String value2="";
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_layout);
		getData = new GetData();
		Bundle extras = getIntent().getExtras();
		
		setupListView(extras);
	}
	private void setupListView(Bundle extras)
	{
		String[] listItems = null;
		
		try {
				if(extras !=null){
					value = extras.getString("name").toString();
				    value2 = extras.getString("street").toString();
				}
			//Get server data:
			listItems = getData.executeHttpGet("Resturants_Jerusalem", value.toString(), value2.toString());
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