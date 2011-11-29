package com.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AkActivity extends Activity implements View.OnClickListener{
	Button button;
	int touchCount;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        button.setText("Touch me!");
        button.setOnClickListener(this);
        setContentView(R.layout.main);
    }
    
    public void onClick(View v){
    	touchCount++;
    	button.setText("Touch me "+ touchCount + " time(s)");
    }
}