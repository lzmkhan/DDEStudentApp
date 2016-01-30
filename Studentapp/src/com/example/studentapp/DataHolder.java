package com.example.studentapp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

public class DataHolder {
/*
 * Singleton class to hold the data to be sent to webservice, populate myinfo and mycenter fragments
 */

	
	
//Grievance Submit form data	
public String G_Spinner_entry ="";
public String G_Email="";
public String G_Phone="";
public String G_Query="";
public String G_Result="";	
//Grievance track data
public String G_track_num="";
public String G_status="";	
	
//Your information Data
public String Year=""; //all these should be empty
public String Name="";
public String Enroll_number="";
public String DOB="";
public String Duration="";
public String C_name="";
public String C_code="";
public String PCP_code="";

//MyCenter Data
public String PCP_center="";
public String Address1="";
public String Address2="";
public String city="";
public String Taluk="";
public String District="";
public String State="";
public String postal_code="";
public String Telephone="";

//search center holders

public String phone_number;
public String Gmap_address;




//Webservice address


public String PCP_address ="";
public String Exam_Theory_address ="";
public String Exam_Prac_address ="";
public String Result_Reg_address ="";
public String Result_OverS_address ="";
public String loginsuccess="false";

public String main_address = null;


//first time parameters
public String first_time_address ="http://autestdomain.comule.com";
	
public String web_login_data="";	
	
public String RegisterId="none";

	
	
	
	
	private static DataHolder dataHolder = new DataHolder();
	
	public static DataHolder getInstance()
	{
		return dataHolder;
	}
	
	
	
	
	
}
