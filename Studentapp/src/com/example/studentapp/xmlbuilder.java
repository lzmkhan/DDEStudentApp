package com.example.studentapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;
import android.util.Log;

public class xmlbuilder {

	
	public Context c;
	public String web_login_data;
	 public newsstructure ns; 
	public xmlbuilder(){}
	
	
	public xmlbuilder(Context ch)
	{
	 
	 c=ch;
	}
	
	
	private String Construct_usrinfo_xml()
	{
		StringBuilder Stuff = new StringBuilder();
	
		
		Stuff.append("<year>");
		Stuff.append(DataHolder.getInstance().Year);
		Stuff.append("</year>");
		
		Stuff.append("<name>");
		Stuff.append(DataHolder.getInstance().Name);
		Stuff.append("</name>");
		
		Stuff.append("<enroll>");
		Stuff.append(DataHolder.getInstance().Enroll_number);
		Stuff.append("</enroll>");
		
		Stuff.append("<dob>");
		Stuff.append(DataHolder.getInstance().DOB);
		Stuff.append("</dob>");
		
		Stuff.append("<duration>");
		Stuff.append(DataHolder.getInstance().Duration);
		Stuff.append("</duration>");
		
		Stuff.append("<cname>");
		Stuff.append(DataHolder.getInstance().C_name);
		Stuff.append("</cname>");
		
		Stuff.append("<ccode>");
		Stuff.append(DataHolder.getInstance().C_code);
		Stuff.append("</ccode>");
		
		Stuff.append("<pcpcode>");
		Stuff.append(DataHolder.getInstance().PCP_code);
		Stuff.append("</pcpcode>");
		

		Log.d("usrinfobuild",Stuff.toString());
		
		return Stuff.toString();
	}

	private String Construct_mycenter_xml()
	{
		StringBuilder Stuff = new StringBuilder();

		
		Stuff.append("<PCPCenter>");
		Stuff.append(DataHolder.getInstance().PCP_center);
		Stuff.append("</PCPCenter>");
		
		Stuff.append("<PCPcode>");
		Stuff.append(DataHolder.getInstance().PCP_code);
		Stuff.append("</PCPcode>");
		
		Stuff.append("<Address1>");
		Stuff.append(DataHolder.getInstance().Address1);
		Stuff.append("</Address1>");
		
		Stuff.append("<Address2>");
		Stuff.append(DataHolder.getInstance().Address2);
		Stuff.append("</Address2>");
		
		Stuff.append("<City>");
		Stuff.append(DataHolder.getInstance().city);
		Stuff.append("</City>");
		
		Stuff.append("<Taluk>");
		Stuff.append(DataHolder.getInstance().Taluk);
		Stuff.append("</Taluk>");
		
		Stuff.append("<District>");
		Stuff.append(DataHolder.getInstance().District);
		Stuff.append("</District>");
		
		Stuff.append("<State>");
		Stuff.append(DataHolder.getInstance().State);
		Stuff.append("</State>");
		
		Stuff.append("<PostalCode>");
		Stuff.append(DataHolder.getInstance().postal_code);
		Stuff.append("</PostalCode>");
		
		Stuff.append("<Telephone>");
		Stuff.append(DataHolder.getInstance().Telephone);
		Stuff.append("</Telephone>");
		
	
		
		Log.d("mycenterbuild",Stuff.toString());
		return Stuff.toString();
	}

    private String Construct_webaddress_xml()
    {

    	StringBuilder Stuff = new StringBuilder();

	
		Stuff.append("<PCP>");
		Stuff.append(DataHolder.getInstance().PCP_address);
		Stuff.append("</PCP>");
		
		Stuff.append("<exam_theory>");
		Stuff.append(DataHolder.getInstance().Exam_Theory_address);
		Stuff.append("</exam_theory>");
		
		Stuff.append("<exam_practical>");
		Stuff.append(DataHolder.getInstance().Exam_Prac_address);
		Stuff.append("</exam_practical>");
		
		Stuff.append("<result_reg>");
		Stuff.append(DataHolder.getInstance().Result_Reg_address);
		Stuff.append("</result_reg>");
		
		Stuff.append("<result_over>");
		Stuff.append(DataHolder.getInstance().Result_OverS_address);
		Stuff.append("</result_over>");
		
		Stuff.append("<main>");
		Stuff.append(DataHolder.getInstance().main_address);
		Stuff.append("</main>");
	
	
		
		Log.d("webaddressesbuild",Stuff.toString());
		return Stuff.toString();
	}

    public void Save_webaddress_to_file()
    {
    	 String stuff=Construct_webaddress_xml();

    	   
    	   try {
    		   File f =new File("webaddress.zaf");
    		   if(f.exists())
    		   c.deleteFile("webaddress.zaf");
    		  
    		    FileOutputStream fos = c.openFileOutput("webaddress.zaf", Context.MODE_PRIVATE);

    		    fos.write(stuff.getBytes());

    		    fos.close();

    		} catch (Exception e) {

    		    e.printStackTrace();

    		}
    		
    }
	
	public void Save_usrinfo_to_file()
	{
	

   String stuff=Construct_usrinfo_xml();

   
   try {
	   File f =new File("usrinfo.zaf");
	   if(f.exists())
	   c.deleteFile("usrinfo.zaf");
	  
	    FileOutputStream fos = c.openFileOutput("usrinfo.zaf", Context.MODE_PRIVATE);

	    fos.write(stuff.getBytes());

	    fos.close();

	} catch (Exception e) {

	    e.printStackTrace();

	}
	
	}

	public void Save_mycenter_to_file()
	{
	

   String stuff=Construct_mycenter_xml();
   
   try {
	   c.deleteFile("mycenter.zaf");
	   Log.d("SPARTAAAA","execute");
	    FileOutputStream fos = c.openFileOutput("mycenter.zaf", Context.MODE_PRIVATE);

	    fos.write(stuff.getBytes());

	    fos.close();

	} catch (Exception e) {

	    e.printStackTrace();

	}
	
	
	}
	
	

	public void parse_xml(int mode) throws XmlPullParserException, IOException
	{
		//mode =1 for userinfo
		//mode =2 for mycenter
		//mode =3 for consume webservice login
		//mode =4 for consume webservice updateaddress
		//mode =5 for local read of webaddress from file
		int i=0;
		String source ="";
		
		if (mode==1){
		source = read_xml_from_file(1);
	}else if (mode==2){
			source = read_xml_from_file(2);
	}else if (mode==3){
			source = web_login_data;
	}else if (mode==4){
		    source = web_login_data;
	}else if (mode==5){
	    source = read_xml_from_file(3);
	}else if (mode==6){
	    source = web_login_data;
}
		
		
	String[] key_value = new String[40];
		
	
	
	
	 XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
     factory.setNamespaceAware(true);
     XmlPullParser xpp = factory.newPullParser();

     xpp.setInput( new StringReader ( source ) );
     int eventType = xpp.getEventType();
     while (eventType != XmlPullParser.END_DOCUMENT) {
      if(eventType == XmlPullParser.START_TAG) {
    	
      } else if(eventType == XmlPullParser.END_TAG) {
       
      } else if(eventType == XmlPullParser.TEXT) {
        key_value[i]=xpp.getText();
        i++;
     
      }
      eventType = xpp.next();
     }
     
     if(mode==1)
     {
    	DataHolder.getInstance().Year= key_value[0];
    	 DataHolder.getInstance().Name= key_value[1];
    	 DataHolder.getInstance().Enroll_number= key_value[2];
    	 DataHolder.getInstance().DOB= key_value[3];
    	 DataHolder.getInstance().Duration= key_value[4];
    	 DataHolder.getInstance().C_name= key_value[5];
    	 DataHolder.getInstance().C_code= key_value[6];
    	 DataHolder.getInstance().PCP_code= key_value[7];
    	 
     }
     else if(mode==2)
     {
    	 DataHolder.getInstance().PCP_center=key_value[0];
    	 DataHolder.getInstance().Address1=key_value[1];
    	 DataHolder.getInstance().Address2=key_value[2];
    	 DataHolder.getInstance().city=key_value[3];
    	 DataHolder.getInstance().Taluk=key_value[4];
    	 DataHolder.getInstance().District=key_value[5];
    	 DataHolder.getInstance().State=key_value[6];
    	 DataHolder.getInstance().postal_code=key_value[7];
    	 DataHolder.getInstance().Telephone=key_value[8];
 
     }
     else if(mode==3)
     {
    	 DataHolder.getInstance().Year= key_value[0];
	     DataHolder.getInstance().Name= key_value[1];
	     DataHolder.getInstance().Enroll_number= key_value[2];
	     DataHolder.getInstance().DOB= key_value[3];
	     DataHolder.getInstance().Duration= key_value[4];
	     DataHolder.getInstance().C_name= key_value[5];
	     DataHolder.getInstance().C_code= key_value[6];
	     DataHolder.getInstance().PCP_code= key_value[7];
	 
    	 DataHolder.getInstance().PCP_center=key_value[8];
    	 DataHolder.getInstance().Address1=key_value[9];
    	 DataHolder.getInstance().Address2=key_value[10];
    	 DataHolder.getInstance().city=key_value[11];
    	 DataHolder.getInstance().Taluk=key_value[12];
    	 DataHolder.getInstance().District=key_value[13];
    	 DataHolder.getInstance().State=key_value[14];
    	 DataHolder.getInstance().postal_code=key_value[15];
    	 DataHolder.getInstance().Telephone=key_value[16];
     }
     else if ( mode == 4 || mode == 5){
    	
	     DataHolder.getInstance().PCP_address= key_value[0];
	     DataHolder.getInstance().Exam_Theory_address= key_value[1];
	     DataHolder.getInstance().Exam_Prac_address= key_value[2];
	     DataHolder.getInstance().Result_Reg_address= key_value[3];
	     DataHolder.getInstance().Result_OverS_address= key_value[4];
	    DataHolder.getInstance().main_address= key_value[5];
     }
     else if ( mode ==6)
     {
    	 ns = new newsstructure();
    	 ns.newsTitle=key_value[0];
    	 ns.newsNo =key_value[1];
    	 ns.newsDate = key_value[2];
    	 ns.newsContent =key_value[3];
    	 savegcmtodb();
     }
		
	}
	
	public void  savegcmtodb()
	{
		
		Database_Handler db = new Database_Handler(c);
		db.insert_news(ns);
		
		
	}
	
	
	public String read_xml_from_file(int mode)
	{
		//mode 1 for useinfo
		//mode 2 for mycenter
		//mode 3 for webaddress
		//mode 4 for registerid for gcm
		 StringBuffer  stringBuffer = new StringBuffer();  
		 
		 String s ="";
		 
		 if(mode==1)
		 s="usrinfo.zaf";
		 else if(mode==2)
			 s="mycenter.zaf";
		 else if(mode==3)
			 s="webaddress.zaf";
		 else if(mode==4)
			 s="register_status.zaf";
		 try {

			 BufferedReader inputReader = new BufferedReader(new InputStreamReader(

					 c.openFileInput(s)));

			 String inputString;

                 

			 while ((inputString = inputReader.readLine()) != null) {

				 stringBuffer.append(inputString);

			 	}

  
		 	} catch (IOException e) {

		 		e.printStackTrace();

		 		}


		
		
		 return stringBuffer.toString();

		
	}
	
	
	
	
	
}
