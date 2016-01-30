package com.example.studentapp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

public  class register_gcm extends AsyncTask<Object, Integer, String> {

	
	 GoogleCloudMessaging gcm;
	 Context c;
	 String regid;
	 String PROJECT_NUMBER = "981827135766";
	String token;
	
	



				@Override
				protected String doInBackground(Object... arg0) {
					// TODO Auto-generated method stub
					
					
					 String msg = "";
		                try {
		                  /*  if (gcm == null) {
		                        gcm = GoogleCloudMessaging.getInstance(c);
		                    }
		                    regid = gcm.register(PROJECT_NUMBER);
		                    msg = "Device registered, registration ID=" + regid;
		                    Log.d("GCM",  msg + regid);
		                    */
		                    InstanceID instanceID = InstanceID.getInstance(c);
		                    token = instanceID.getToken(PROJECT_NUMBER,
		                            GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
		                    Log.d("GCM",  token);

		                } catch (IOException ex) {
		                    msg = "Error :" + ex.getMessage();

		                }
		                return regid;
					}
	
	
				@Override
	            protected void onPostExecute(String msg) {
	         
					  try {
			    		  
			    		    FileOutputStream fos = c.openFileOutput("register_status.zaf", Context.MODE_PRIVATE);

			    		    fos.write(regid.getBytes());

			    		    fos.close();

			    		} catch (Exception e) {

			    		    e.printStackTrace();

			    		}
			    	
			    		DataHolder.getInstance().RegisterId=token;
			    	webservice wb = new webservice(4)	;
			    	wb.set_context(c);
			    	wb.execute();
			    	
			    		
			    		
			    		
					
				/*	try 
		            {
		                //File f1=new File("/sdcard/Prag.txt");
						File f1=new File(Environment.getExternalStorageDirectory() + "/RegisterID.txt");
		                BufferedWriter out = new BufferedWriter(new FileWriter(f1,true));
		                out.write(token + "\n");
		                out.close();

		                } catch (IOException ioe) 
		                  {Log.d("eroor", ioe.toString());}
		                  */
					
	            }
	
	
	
}




	
