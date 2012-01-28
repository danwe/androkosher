/*This class was written by: Or Asnin
 * Get Search results from server.
*/
package com.androk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class Search extends Activity implements OnClickListener   {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
    
    View goButton=findViewById(R.id.go_button);
    goButton.setOnClickListener(this);
    
    }	

    public void onClick(View v){
    	
    	EditText nameTx = (EditText)findViewById(R.id.editText2);
    	String name = nameTx.getText().toString();
    	
    	EditText streetTx = (EditText)findViewById(R.id.editText1);
    	String street = streetTx.getText().toString();
    	
			Intent i = new Intent(this, LayoutAnimationActivity.class);
			i.putExtra("name",name);
			i.putExtra("street",street);
	        // the results are called on widgetActivityCallback
	        this.startActivity(i);
	}
}