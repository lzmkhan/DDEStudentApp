package com.example.studentapp;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class webservice extends AsyncTask<Object, Object, Object>{

private  String SOAP_NAMESPACE ="urn:getmsWSDL";
private  String SOAP_URL="http://autestdomain.comule.com/Getinfo.php";
private  String SOAP_ACTION ="http://autestdomain.comule.com/db1";
private  String SOAP_METHOD_NAME="db1";
private SoapObject request;
private PropertyInfo pi1;
public ProgressDialog pgd;
private Context c;
private int mode;
public TextView txt1;
public MainActivity mn;

public webservice(int mode)
{
	//mode 0 for login
	//mode 1 for Grievance track
	//mode 2 for Grievance submit
	//mode 3 for Webservice to obtain link.
	//mode 4 for sending registrationid to gcm table
	
	this.mode=mode;
	Log.d("tag", "" + this.mode);
	
}


public void set_context(Context c)
{this.c =c;
	
}


public void call_webservice_login() 
{
//webservice start
	
  String SOAP_URL=DataHolder.getInstance().main_address +"/Getinfo.php";
	

request = new SoapObject(SOAP_NAMESPACE,SOAP_METHOD_NAME);

pi1= new PropertyInfo();
pi1.setName("a");
pi1.setValue(DataHolder.getInstance().Enroll_number);
Log.d("enroll",DataHolder.getInstance().Enroll_number);
//pi1.setType(String.class);
request.addProperty(pi1);



SoapSerializationEnvelope envp = new SoapSerializationEnvelope(SoapEnvelope.VER11);
envp.dotNet=false;
envp.setOutputSoapObject(request);
HttpTransportSE androidHttpTransport = new HttpTransportSE(SOAP_URL);
try
{
	androidHttpTransport.call(SOAP_ACTION,envp);
	Object response=  (Object)envp.getResponse();
	Log.d("webservice reply",response.toString());
	
	DataHolder.getInstance().web_login_data=response.toString();
	
}
catch(Exception e)
{
	Log.d("ws error1",e.toString());
	
}


}



public void call_webservice_grievance_track()
{
//webservice start
 SOAP_URL=DataHolder.getInstance().main_address +"/status.php";
 

request = new SoapObject(SOAP_NAMESPACE,SOAP_METHOD_NAME);
DataHolder dh = DataHolder.getInstance();

pi1= new PropertyInfo();
pi1.setName("a");
pi1.setValue(dh.G_track_num);
//pi1.setType(String.class);
request.addProperty(pi1);



SoapSerializationEnvelope envp = new SoapSerializationEnvelope(SoapEnvelope.VER11);
envp.dotNet=false;
envp.setOutputSoapObject(request);
HttpTransportSE androidHttpTransport = new HttpTransportSE(SOAP_URL);
try
{
	androidHttpTransport.call(SOAP_ACTION,envp);
	Object response=  (Object)envp.getResponse();
	dh.G_status = response.toString();
	Log.d("webservice reply",dh.G_status.toString());
}
catch(Exception e)
{
	Log.d("ws error",e.toString());
	
}


}



public void call_webservice_grievance_submit()
{
//webservice start
	SOAP_URL=DataHolder.getInstance().main_address + "/grey1s.php";
	
DataHolder dh = DataHolder.getInstance();

request = new SoapObject(SOAP_NAMESPACE,SOAP_METHOD_NAME);

pi1= new PropertyInfo();
pi1.setName("a");
pi1.setValue(dh.Enroll_number);
//pi1.setType(String.class);
request.addProperty(pi1);

PropertyInfo pi2= new PropertyInfo();
pi2.setName("b");
pi2.setValue(dh.C_code);
//pi1.setType(String.class);
request.addProperty(pi2);

PropertyInfo pi3= new PropertyInfo();
pi3.setName("c");
pi3.setValue(dh.G_Phone);
//pi1.setType(String.class);
request.addProperty(pi3);

PropertyInfo pi4= new PropertyInfo();
pi4.setName("d");
pi4.setValue(dh.G_Email);
//pi1.setType(String.class);
request.addProperty(pi4);

PropertyInfo pi5= new PropertyInfo();
pi5.setName("e");
pi5.setValue(dh.G_Spinner_entry);
//pi1.setType(String.class);
request.addProperty(pi5);

PropertyInfo pi6= new PropertyInfo();
pi6.setName("f");
pi6.setValue(dh.G_Query);
//pi1.setType(String.class);
request.addProperty(pi6);

Log.d("request",request.toString());

SoapSerializationEnvelope envp = new SoapSerializationEnvelope(SoapEnvelope.VER11);
envp.dotNet=false;
envp.setOutputSoapObject(request);
HttpTransportSE androidHttpTransport = new HttpTransportSE(SOAP_URL);
try
{
	androidHttpTransport.call(SOAP_ACTION,envp);
	Object response=  (Object)envp.getResponse();
	dh.G_Result =response.toString();
	Log.d("webservice reply",response.toString());
}
catch(Exception e)
{
	Log.d("ws error",e.toString());
	
}


}


public void call_webservice_obtainaddress() 
{
//webservice start
	
	SOAP_URL=DataHolder.getInstance().main_address + "/get_link.php";
	

request = new SoapObject(SOAP_NAMESPACE,SOAP_METHOD_NAME);

pi1= new PropertyInfo();
pi1.setName("a");
pi1.setValue("hi");

request.addProperty(pi1);



SoapSerializationEnvelope envp = new SoapSerializationEnvelope(SoapEnvelope.VER11);
envp.dotNet=false;
envp.setOutputSoapObject(request);
HttpTransportSE androidHttpTransport = new HttpTransportSE(SOAP_URL);
try
{
	androidHttpTransport.call(SOAP_ACTION,envp);
	Object response=  (Object)envp.getResponse();
	Log.d("webservice reply",response.toString());
	
	DataHolder.getInstance().web_login_data=response.toString();
	
}
catch(Exception e)
{
	Log.d("ws error1",e.toString());
	
}


}


public void register_with_server()
{
//webservice start
	SOAP_URL=DataHolder.getInstance().main_address + "/gcm_reg.php";
	
DataHolder dh = DataHolder.getInstance();

request = new SoapObject(SOAP_NAMESPACE,SOAP_METHOD_NAME);

pi1= new PropertyInfo();
pi1.setName("a");
pi1.setValue(dh.Enroll_number);
//pi1.setType(String.class);
request.addProperty(pi1);

PropertyInfo pi2= new PropertyInfo();
pi2.setName("b");
pi2.setValue(dh.RegisterId);
//pi1.setType(String.class);
request.addProperty(pi2);


Log.d("request",request.toString());

SoapSerializationEnvelope envp = new SoapSerializationEnvelope(SoapEnvelope.VER11);
envp.dotNet=false;
envp.setOutputSoapObject(request);
HttpTransportSE androidHttpTransport = new HttpTransportSE(SOAP_URL);
try
{
	androidHttpTransport.call(SOAP_ACTION,envp);
	Object response=  (Object)envp.getResponse();
	dh.G_Result =response.toString();
	Log.d("webservice reply",response.toString());
}
catch(Exception e)
{
	Log.d("ws error",e.toString());
	
}


}








public boolean isOnline(Context context) {
	  ConnectivityManager connectivityManager  = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
	  NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	  return activeNetworkInfo != null && activeNetworkInfo.isConnected();
}










@Override
protected Object doInBackground(Object... arg0) {
	// TODO Auto-generated method stub
	if(isOnline(c))
	{
	switch(mode)
	{
	case 0://login webservice
		call_webservice_login();
		  pgd.dismiss();
		break;
		
	case 1://Grievance track
		 call_webservice_grievance_track();
		 pgd.dismiss();
		break;
		
	case 2://Grievance submit
		call_webservice_grievance_submit();
		pgd.dismiss();
		
		
		break;
		
	case 3://Webaddress fetch
		call_webservice_obtainaddress();
	
		
		break;
		
	case 4://register with gcm table
		register_with_server();
	 break;
	
	}
	return 1;
	}
	
	    
	return 0;
}




protected void onProgressUpdate(Integer... progress) {
   
}


protected void onPreExecute(){ 
	   super.onPreExecute();
	        pgd = new ProgressDialog(c);
	        pgd.setMessage("Loading...");
	        pgd.show();    
	}

	
protected void onPostExecute(Object result) {
 // TODO Auto-generated method stub
 super.onPostExecute(result);
if(result.equals(1))
{
 switch(mode)
 {
 case 0:
	Log.d("weblogindata", DataHolder.getInstance().web_login_data);
	 if(DataHolder.getInstance().web_login_data.equals("<Name>Null</Name>"))
	 {
		 Toast.makeText(c, "Profile not found!",
				   Toast.LENGTH_LONG).show();
	 }else{
	 xmlbuilder xm = new xmlbuilder();
		xm.web_login_data=DataHolder.getInstance().web_login_data;
		try{
		xm.parse_xml(3);}catch(Exception e){Log.d("exception", e.toString());}
		Toast.makeText(c, "Profile found!",
				   Toast.LENGTH_LONG).show();
		DataHolder.getInstance().loginsuccess="true";
		
		xm.c=this.c;
		xm.Save_usrinfo_to_file();
		xm.Save_mycenter_to_file();
	
		if(DataHolder.getInstance().loginsuccess.equals("true"))
		{
			register_gcm rg = new register_gcm();
			rg.c=this.c;
			rg.execute();
		}
		
		
	 }
		
	 break;
 case 1:
 DataHolder dh = DataHolder.getInstance();
 if(dh.G_status.equals("Status: \n "))
 {
	 Toast.makeText(c, "No status found, try again later!",
			   Toast.LENGTH_LONG).show();
 }else
 {
 txt1.setText(dh.G_status);
 }
 break;
 
 
 case 2:
	 
	 if(DataHolder.getInstance().G_Result.equals(null))
	 {
		 Toast.makeText(c, "Query not submitted, please try again",
				   Toast.LENGTH_LONG).show();
	 }
	 else
	 {
	 AlertDialog.Builder as= new AlertDialog.Builder(c);
	    as.setTitle("Submission Number");
	    DataHolder dh1= DataHolder.getInstance();
	    
	    as.setMessage("Please write down this number and check the status of your submission on the track screen. Your submission number is <" +dh1.G_Result+">");
	    as.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // continue with delete
	        }
	     });
	    
	    as.setIcon(android.R.drawable.ic_popup_reminder);
	     as.show();
	 }
		
	 break;
	 
 case 3:
		pgd.dismiss();
		Toast.makeText(c, "Links updated!",
				   Toast.LENGTH_LONG).show();
		xmlbuilder xm = new xmlbuilder(c);
		xm.web_login_data=DataHolder.getInstance().web_login_data;
	try {
		xm.parse_xml(4);
	} catch (XmlPullParserException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		xm.Save_webaddress_to_file();
	 break;
	 
	 
 case 4:
	 pgd.dismiss();
	 Toast.makeText(c, "Registered with Server",
			   Toast.LENGTH_LONG).show();
	 
	 break;
	 
 }
}else
	{
	pgd.dismiss();
		Toast.makeText(c, "No working internet!",
				   Toast.LENGTH_LONG).show();
	}

}

}