package com.example.studentapp;


import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class splashactivity extends Activity {

	// Splash screen timer
	private static int SPLASH_TIME_OUT = 3000;
	xmlbuilder xm = new xmlbuilder(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);
		
		new Handler().postDelayed(new Runnable() {

			/*
			 * Showing splash screen with a timer. This will be useful when you
			 * want to show case your app logo / company
			 */

			@Override
			public void run() {
				// This method will be executed once the timer is over
				// Start your app main activity
				
			
		try {
			xm.parse_xml(5);
		} catch (XmlPullParserException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//
		if(	(DataHolder.getInstance().main_address== null) || (DataHolder.getInstance().main_address.equals("null") ))
			DataHolder.getInstance().main_address=DataHolder.getInstance().first_time_address;
		Log.d("splash",DataHolder.getInstance().main_address);
				
				Intent i = new Intent(splashactivity.this, MainActivity.class);
				startActivity(i);

				// close this activity
				finish();
			}
		}, SPLASH_TIME_OUT);
	}

}

