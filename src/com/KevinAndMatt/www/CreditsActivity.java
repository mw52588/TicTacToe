package com.KevinAndMatt.www;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreditsActivity extends Activity {
    /** Called when the activity is first created. */
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credit);
        
        
        Button cred = (Button) findViewById(R.id.button1);
        cred.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	 finish();
                
            }

        });
        
	}
}
