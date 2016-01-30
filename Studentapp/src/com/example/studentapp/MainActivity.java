package com.example.studentapp;

/*This code can be optimized by using view holders and placing fragments in to outerclass.
 * 
 * 
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.app.Fragment;
import android.content.Intent;
import android.database.SQLException;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends Activity {

	private String[] Titles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    public static View v;
    private int posi;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 mTitle = mDrawerTitle = getTitle();
	        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
	                R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

	            /** Called when a drawer has settled in a completely closed state. */
	            @Override
				public void onDrawerClosed(View view) {
	                super.onDrawerClosed(view);
	                getActionBar().setTitle(mTitle);
	                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
	            }

	            /** Called when a drawer has settled in a completely open state. */
	            @Override
				public void onDrawerOpened(View drawerView) {
	                super.onDrawerOpened(drawerView);
	                getActionBar().setTitle(mDrawerTitle);
	                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
	            }
	        };

	        // Set the drawer toggle as the DrawerListener
	        mDrawerLayout.setDrawerListener(mDrawerToggle);
	        getActionBar().setDisplayHomeAsUpEnabled(true);
	//getActionBar().setHomeButtonEnabled(true);
		Titles = getResources().getStringArray(R.array.title_array);
   
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, Titles));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        button_click_listener(posi);
    
       contextHolder ch = contextHolder.getInstance();
       ch.con=this;
        
        
        if (savedInstanceState == null) {
            selectItem(0);
            
        }
        
	}

	
	
	
	
	public void  onlogin_click(View v) //handles login page button
	{
		EditText txt1 = (EditText) findViewById(R.id.LoginNo);
		DataHolder.getInstance().Enroll_number=txt1.getText().toString();
		webservice wbl = new webservice(0);
		wbl.set_context(this);
		wbl.execute();
		TextView  tv1 = (TextView) findViewById(R.id.regi1);
				tv1.setVisibility(0);
			
		
	}
	
	
	public void  onclickm(View v)//handles clear fields on all the forms
	{
		EditText edtx;
			edtx=(EditText) findViewById(v.getId());
		edtx.setText("");
	}
	
	
	public  void grievanceweb(View v) //handles grievance page buttons
	{
		int id = v.getId();
		if (id == R.id.buttonS) {
			DataHolder dt1 = DataHolder.getInstance();
			//use the rootview obtained from above
			contextHolder con = contextHolder.getInstance();
			EditText edt = (EditText) findViewById(R.id.editTextT);
			dt1.G_track_num = edt.getText().toString();
			TextView tx1 = (TextView) findViewById(R.id.Status);
			webservice wb1 = new webservice(1);
			wb1.set_context(this);
			wb1.txt1=tx1;
			wb1.execute();
		} else if (id == R.id.button2) {
			DataHolder dt = DataHolder.getInstance();
			EditText ed1 = (EditText) findViewById(R.id.phone1);
			dt.G_Phone = ed1.getText().toString();
			EditText ed2 = (EditText) findViewById(R.id.emailid1);
			dt.G_Email = ed2.getText().toString();
			EditText ed3 = (EditText) findViewById(R.id.editText32);
			dt.G_Query = ed3.getText().toString();
			webservice wb = new webservice(2);
			wb.set_context(this);
			wb.execute();
			Log.d("tag", dt.G_Phone);
		}
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			webservice wb = new webservice(3);
			wb.set_context(this);
			wb.execute();
			return true;
		}
		else if (id == R.id.register)
		{
			Log.d("main","register0");
			
			if(DataHolder.getInstance().loginsuccess.equals("true"))
			{
				register_gcm rg = new register_gcm();
				rg.c=this;
				rg.execute();
			}
						
		//MenuItem mi =(MenuItem) findViewById(R.id.register);
		//mi.setEnabled(false);
			
		}
		 if (mDrawerToggle.onOptionsItemSelected(item)) {
		      return true;
		    }
		return super.onOptionsItemSelected(item);
	}

    
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
	    @Override
	    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	    	
	    	Log.d("position", ""+position);
	    	
	    	if(position !=8){
	        selectItem(position);}
	    	else if(position == 8)
	    	{
	    		Intent i = new Intent(MainActivity.this, messagesactivity.class);
				startActivity(i);

	    	}
	    		
	      
	    }
	}

	
	
	
	
	
	
	
	
	
	private class TabItemClickListener  implements ActionBar.TabListener {
	   

		@Override
		public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
			// TODO Auto-generated method stub
			int pos=0;
			String s = arg0.getText().toString();
			
			if(s.equals("Track"))
				{pos=51;}
			else if(s.equals("Submit"))
			{pos=52;}
			else if(s.equals("E-Regular"))
			{pos=31;}
			else if(s.equals("E-Practical"))
			{pos=32;}
			else if(s.equals("Regular"))
			{pos=41;}
			else if(s.equals("Overseas"))
			{pos=42;}
				
				
			
			
			
			 button_click_listener(pos) ;
		}

		@Override
		public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
			// TODO Auto-generated method stub
			int pos=0;
			String s = arg0.getText().toString();
			
			if(s.equals("Track"))
				{pos=51;}
			else if(s.equals("Submit"))
			{pos=52;}
			else if(s.equals("E-Regular"))
			{pos=31;}
			else if(s.equals("E-Practical"))
			{pos=32;}
			else if(s.equals("Regular"))
			{pos=41;}
			else if(s.equals("Overseas"))
			{pos=42;}
				
				
				
				
				
			button_click_listener(pos) ;
			
		}

		@Override
		public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
			// TODO Auto-generated method stub
			
		}
	}
	
	
	
	
	/** Swaps fragments in the main content view 
	*/
	private void selectItem(int position)  {
	    // Create a new fragment and specify the planet to show based on position
	    Fragment fragment = new PlaceholderFragment();
	    Bundle args = new Bundle();
	    args.putInt(PlaceholderFragment.ARG_FRAGMENT_NUMBER, position);
	    fragment.setArguments(args);
    
	    // Insert the fragment by replacing any existing fragment
	    FragmentManager fragmentManager = getFragmentManager();
	    fragmentManager.beginTransaction()
	                   .replace(R.id.content_frame, fragment)
	                   .commit();
	    
	    Log.d("tap","1");
	    posi=position;
	    getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
	    getActionBar().removeAllTabs();

	    
	  if(posi==0)
	  {
	    	xmlbuilder xb = new xmlbuilder(this);
	    //	xb.Save_usrinfo_to_file();
	    	try {
				xb.parse_xml(1);
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
			Log.d("tag", e.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.d("tag", e.toString());
			}
	  }
	  if(posi==1)
	  {
	    	xmlbuilder xb = new xmlbuilder(this);
	    //	xb.Save_mycenter_to_file();
	    	try {
				xb.parse_xml(2);
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
			Log.d("tag", e.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.d("tag", e.toString());
			}
	  }
	  
	  
        if(posi==5)//grievance below code adds the two tabs track and submit
        {
        	getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        	ActionBar.Tab b = getActionBar().newTab();
        	b.setText("Track");
        	b.setTabListener(new TabItemClickListener());
        	ActionBar.Tab b1 = getActionBar().newTab();
        	b1.setText("Submit");
        	b1.setTabListener(new TabItemClickListener());
        	getActionBar().addTab(b);
        	getActionBar().addTab(b1);
       
      
        }
	    
	    if(posi==3)//for exam adds Regular and Oversees tab
	    {
	    	getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        	ActionBar.Tab b = getActionBar().newTab();
        	b.setText("E-Regular");
        	b.setTabListener(new TabItemClickListener());
        	ActionBar.Tab b1 = getActionBar().newTab();
        	b1.setText("E-Practical");
        	b1.setTabListener(new TabItemClickListener());
        	getActionBar().addTab(b);
        	getActionBar().addTab(b1);
      
	    }

	    if(posi==4)
	    {
	    	getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        	ActionBar.Tab b = getActionBar().newTab();
        	b.setText("Regular");
        	b.setTabListener(new TabItemClickListener());
        	ActionBar.Tab b1 = getActionBar().newTab();
        	b1.setText("Overseas");
        	b1.setTabListener(new TabItemClickListener());
        	getActionBar().addTab(b);
        	getActionBar().addTab(b1);
     
	    }
	    button_click_listener(posi);
	    // Highlight the selected item, update the title, and close the drawer
	    mDrawerList.setItemChecked(position, true);
	    setTitle(Titles[position]);
	    mDrawerLayout.closeDrawer(mDrawerList);
	    
	    
	    
	    
	    
	}
	
	
	
	

	@Override
	public void setTitle(CharSequence title) {
	    mTitle = title;
	    getActionBar().setTitle(mTitle);
	}

	
	
	
	  @Override
	    protected void onPostCreate(Bundle savedInstanceState) {
	        super.onPostCreate(savedInstanceState);
	        // Sync the toggle state after onRestoreInstanceState has occurred.
	        mDrawerToggle.syncState();
	     
	    }
	
	
	  public void  button_click_listener(int layout_position) 
	  {//used to handle tab clicks
		  int fragmentnum=layout_position;
		  
		  Fragment fragment3 = new PlaceholderFragment();
		    Bundle args3 = new Bundle();
		    
		    
		
		  
		  
		  
		  
		  switch(layout_position)
			{
			 	  case 2:// pcp Schedule
					  //call web address by intent
					  
				  break;
			 	  case 3:
				  case 31:
						fragmentnum = 31;		
				  break;
				  case 32:// Exam Schedule
					  //call web address by intent
					fragmentnum = 32;			  
				  break;
				  case 4:
				  case 41:
						fragmentnum = 41;		
					  break;
				  case 42:// results
					  //call web address by intent
						fragmentnum = 42;		
				  break;
				  case 5:
				  case 51://Grievances
					  // call a fragment provide an option to which we can replace the below portion
						fragmentnum = 51;		
					  break;
				  
				  case 52:
					  fragmentnum = 52;
						break;
						} 
		  
		  //add below on conditions in which the layouts u dont want to be replaced  by tabs
		  if((layout_position!=0 & layout_position!=1 & layout_position!=2 & layout_position!=6 & layout_position!=7 & layout_position!=8 & layout_position!=9 & layout_position!=10))
		  {  args3.putInt(PlaceholderFragment.ARG_FRAGMENT_NUMBER, fragmentnum);
		    fragment3.setArguments(args3);
		    FragmentManager fragmentManager1 = getFragmentManager();
		    fragmentManager1.beginTransaction()
		                   .replace(R.id.grievance_frame, fragment3)
		                   .commit();
		  }
		  
	  }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public static final String ARG_FRAGMENT_NUMBER = "fragment_position";
		 List<String> lables;
		int Spinner_mode;
		View searchview;
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView;
			  rootView = inflater.inflate(R.layout.fragment_main, container,
						false);
			
			int i = getArguments().getInt(ARG_FRAGMENT_NUMBER);
			
			  switch(i)
			  {
			  /***********************************************************************************/
			  case 0:// User information
			  rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			DataHolder dh = DataHolder.getInstance();
			//Log.d("main", DataHolder.getInstance().main_address);
			  TextView[] tv = new TextView[10];
			  String ids[] = new String[10];
			  for(int j=0; j<10; j++)
			  {
			     ids[j] = "tv" + Integer.toString(j);
			  }
			  
			  for(int i1=0; i1<10; i1++)
			  {
			     int resID = getResources().getIdentifier(ids[i1], "id", "com.example.studentapp");
			     tv[i1] = (TextView) rootView.findViewById(resID);
			     
			     switch(i1)
			     {
			     case 0:
			    	tv[i1].setText(dh.Year); 
			    	break;
			     case 1:
				    	tv[i1].setText(dh.Name); 
				     break;
			     case 2:
				    	tv[i1].setText(dh.Enroll_number); 
				     break;
			     case 3:
				    	tv[i1].setText(dh.DOB); 
				     break;
			     case 4:
				    	tv[i1].setText(dh.Duration); 
				     break;
			     case 5:
				    	tv[i1].setText(dh.C_name); 
				     break;
			     case 6:
				    	tv[i1].setText(dh.C_code); 
				     break;
			     case 7:
				    	tv[i1].setText(dh.PCP_code); 
				     break;
			     }
			     
			     
			  }
			
			  break;
		/*******************************************************************************************/
			  case 1:// My center details
			  rootView = inflater.inflate(R.layout.my_center, container,
						false);
			    LinearLayout Call_layout = (LinearLayout )rootView.findViewById(R.id.call_layout);
	 			   Call_layout.setOnClickListener(new View.OnClickListener() {      
	 			    @Override
	 			    public void onClick(View v) {
	 			        //Intent picture_intent = new Intent(CurrentActivity.this,PictureActivity.class);
	 			         Log.d("tap","clicked");
	 			         DataHolder dh1 =DataHolder.getInstance();
	 			        Uri call1 = Uri.parse("tel:"+dh1.Telephone);             
		 			       Intent surf = new Intent(Intent.ACTION_DIAL, call1); 
		 			       startActivity(surf);
	 			    }
	 			   });
	 			   
	 			   
	 			   
	 			   
	 			  DataHolder dh1 = DataHolder.getInstance();
				  TextView[] tx = new TextView[10];
				  String ids1[] = new String[10];
				  for(int j=0; j<10; j++)
				  {
				     ids1[j] = "tx" + Integer.toString(j);
				  }
				  
				  for(int i1=0; i1<10; i1++)
				  {
				     int resID = getResources().getIdentifier(ids1[i1], "id", "com.example.studentapp");
				     tx[i1] = (TextView) rootView.findViewById(resID);
				     
				     switch(i1)
				     {
				     case 0:
				    	tx[i1].setText(dh1.PCP_center); 
				    	break;
				     case 1:
					    	tx[i1].setText(dh1.PCP_code); 
					     break;
				     case 2:
					    	tx[i1].setText(dh1.Address1); 
					     break;
				     case 3:
					    	tx[i1].setText(dh1.Address2); 
					     break;
				     case 4:
					    	tx[i1].setText(dh1.city); 
					     break;
				     case 5:
					    	tx[i1].setText(dh1.Taluk); 
					     break;
				     case 6:
					    	tx[i1].setText(dh1.District); 
					     break;
				     case 7:
					    	tx[i1].setText(dh1.State); 
					     break;
				     case 8:
					    	tx[i1].setText(dh1.postal_code); 
					     break;
				     case 9:
					    	tx[i1].setText(dh1.Telephone); 
					     break;
				     }
	 			   
				  }
			  break;
			
			  
			  
			  /***********************************************************************************************/
			  case 2:// PCP Schedule
				  rootView = inflater.inflate(R.layout.webview, container,
							false);
				  WebView webView = ((WebView)rootView.findViewById(R.id.webview1));
				  webView.getSettings().setJavaScriptEnabled(true);
				   
				      WebViewClient localWebViewClient1 = new WebViewClient();
				      webView.setWebViewClient(localWebViewClient1);
				      if(DataHolder.getInstance().PCP_address != null)
				      webView.loadUrl(DataHolder.getInstance().PCP_address);
			  break;
			  
			  case 31://exam schedule theory
				
				  rootView = inflater.inflate(R.layout.webview, container,
							false);
				  WebView webView7 = ((WebView)rootView.findViewById(R.id.webview1));
				  webView7.getSettings().setJavaScriptEnabled(true);
				   
				      WebViewClient localWebViewClient7 = new WebViewClient();
				      webView7.setWebViewClient(localWebViewClient7);
				      if(DataHolder.getInstance().Exam_Theory_address != null)
				      webView7.loadUrl(DataHolder.getInstance().Exam_Theory_address);
				    
				  break;
				  
			  case 32://exam schedule practical
				  rootView = inflater.inflate(R.layout.webview, container,
							false);
				
				  WebView webView1 = ((WebView)rootView.findViewById(R.id.webview1));
				  webView1.getSettings().setJavaScriptEnabled(true);
				   
				      WebViewClient localWebViewClient2 = new WebViewClient();
				      webView1.setWebViewClient(localWebViewClient2);
				      if(DataHolder.getInstance().Exam_Prac_address != null)
				      webView1.loadUrl(DataHolder.getInstance().Exam_Prac_address);
				  break;
				  
				  
			  case 41://results regular fragment
				  rootView = inflater.inflate(R.layout.webview, container,
							false);
				
				  WebView webView2 = ((WebView)rootView.findViewById(R.id.webview1));
				  webView2.getSettings().setJavaScriptEnabled(true);
				   
				      WebViewClient localWebViewClient3 = new WebViewClient();
				      webView2.setWebViewClient(localWebViewClient3);
				      if(DataHolder.getInstance().Result_Reg_address != null)
				      webView2.loadUrl(DataHolder.getInstance().Result_Reg_address);
				  break;
				  	  
			  case 42://results overaseas fragment
				  rootView = inflater.inflate(R.layout.webview, container,
							false);
				
				  WebView webView3 = ((WebView)rootView.findViewById(R.id.webview1));
				  webView3.getSettings().setJavaScriptEnabled(true);
				   
				      WebViewClient localWebViewClient4 = new WebViewClient();
				      webView3.setWebViewClient(localWebViewClient4);
				      if(DataHolder.getInstance().Result_OverS_address != null)
				      webView3.loadUrl(DataHolder.getInstance().Result_OverS_address);
				  break;  
				  
				  
				  
				  
			  
			  case 3:// Exam Schedule container
				
			  case 4:// results container
				  //call web address by intent
				  rootView = inflater.inflate(R.layout.grievance_check, container,
							false);
			  break;
				  
			  case 5://Grievances container
				  // call a fragment provide an option to which we can replace the below portion
				  rootView = inflater.inflate(R.layout.grievance_check, container,
							false);
			  break;
			  case 51://grievance_1 aka track status fragment
				  rootView = inflater.inflate(R.layout.grievance_1, container,
							false);

					//get the rootview view to be used inside onclick
				  contextHolder con = contextHolder.getInstance();
				  con.view=rootView;
				  Button bt3 = (Button) rootView.findViewById(R.id.buttonC);
					bt3.setOnClickListener(new OnClickListener(){
							@Override
							public void onClick(View arg0) {
								// TODO Auto-generated method stub
								
								
								//use the rootview obtained from above
								 contextHolder con = contextHolder.getInstance();
								EditText ed1 = (EditText) con.view.findViewById(R.id.editTextT);
								ed1.setText("");
								
								
							}
							
							
					});
				
					
			  break;
			  
			  
			  case 52://grievance_2 aka submit query fragment
				  rootView = inflater.inflate(R.layout.grievance_2, container,
							false);
				  Spinner spinner1 = (Spinner) rootView.findViewById(R.id.spinner1);
					spinner1.setOnItemSelectedListener( new SpinnerItemClickListener());
				Spinner_mode=0;
				
				//get the rootview view to be used inside onclick
				contextHolder con1 = contextHolder.getInstance();
				  con1.view=rootView;
			
			
					 Button bt2 = (Button) rootView.findViewById(R.id.button12);
						bt2.setOnClickListener(new OnClickListener(){
								@Override
								public void onClick(View arg0) {
									// TODO Auto-generated method stub
									
									
									//use the rootview obtained from above
									 contextHolder con = contextHolder.getInstance();
									EditText ed1 = (EditText) con.view.findViewById(R.id.phone1);
									ed1.setText("");
									EditText ed2 = (EditText) con.view.findViewById(R.id.emailid1);
									ed2.setText("");
									EditText ed3 = (EditText) con.view.findViewById(R.id.editText32);
									ed3.setText("");
									
								}
								
								
						});
			  break;
			  
			  
			  case 6:// Search Centers
				  //call a activity or fragment
				  Spinner_mode=1;
					
				  rootView = inflater.inflate(R.layout.search_center, container,
							false);
				  searchview=rootView;
				  Spinner spinner2 = (Spinner) rootView.findViewById(R.id.spinner2);
					spinner2.setOnItemSelectedListener( new SpinnerItemClickListener());
				  contextHolder ch = contextHolder.getInstance();
			        // database handler
			        Database_Handler db = new Database_Handler(ch.con);
				try {
					db.createDataBase();
					db.openDataBase();   
					lables = db.getAllNames();
						 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			        // Spinner Drop down elements
			      
			        // Creating adapter for spinner
			        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ch.con,
			                android.R.layout.simple_spinner_item, lables);
			  // attaching data adapter to spinner
			        spinner2.setAdapter(dataAdapter);
				  
				  
			        LinearLayout center_call = (LinearLayout )rootView.findViewById(R.id.search_center_call);
		 			   center_call.setOnClickListener(new View.OnClickListener() {      
		 			    @Override
		 			    public void onClick(View v) {
		 			   
		 			         Log.d("tap","clicked");
		 			         DataHolder dh = DataHolder.getInstance();
		 			        Uri call = Uri.parse("tel:"+dh.phone_number);             
		 			       Intent surf = new Intent(Intent.ACTION_DIAL, call); 
		 			       startActivity(surf);
		 			    }
		 			   });  
				  
		 			  LinearLayout center_gmaps = (LinearLayout )rootView.findViewById(R.id.search_center_gmaps);
		 			   center_gmaps.setOnClickListener(new View.OnClickListener() {      
		 			    @Override
		 			    public void onClick(View v) {
		 			        //Intent picture_intent = new Intent(CurrentActivity.this,PictureActivity.class);
		 			         Log.d("tap","clicked");
		 			         DataHolder dh = DataHolder.getInstance();
		 			      String link=  "https://www.google.co.in/maps/place/" + dh.Gmap_address;
		 			     Intent i = new Intent(Intent.ACTION_VIEW);
		 			    i.setData(Uri.parse(link));
		 			    startActivity(i);
		 			         
		 			    }
		 			   });  
				  
			  break;
			  
			  
			  case 7:// Login
				  //call a fragment
				  rootView = inflater.inflate(R.layout.fragment_first_time, container,
							false);
				  		
			  break;
			  
			  case 9:// contact us
				  //contact us
				  rootView = inflater.inflate(R.layout.contactus, container,
							false);
				  contextHolder ch1 = contextHolder.getInstance();
				  ArrayList<ListviewContactItem> listContact = GetlistContact();
				    ListView lv = (ListView)rootView.findViewById(R.id.listView1);
				    lv.setAdapter(new ListViewContactAdapter(ch1.con, listContact));
				    

				contextHolder.getInstance().view=rootView;
					
				  lv.setOnItemClickListener(new OnItemClickListener() {
					 

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
						// TODO Auto-generated method stub
						 ArrayList<ListviewContactItem> listContact = GetlistContact();
						
						if(arg2 < 7){
						TextView tvs = (TextView)contextHolder.getInstance().view.findViewById(R.id.value);
						   Uri call = Uri.parse("tel:"+listContact.get(arg2).Phone);             
		 			       Intent surf = new Intent(Intent.ACTION_DIAL, call); 
		 			       startActivity(surf);
						}
						else
						{
							 
							      
							Intent send = new Intent(Intent.ACTION_SENDTO);
							String uriText = "mailto:" + Uri.encode(listContact.get(arg2).Phone) + 
							          "?subject=" + Uri.encode(" ") + 
							          "&body=" + Uri.encode("");
							Uri uri = Uri.parse(uriText);

							send.setData(uri);
							startActivity(Intent.createChooser(send, "Send mail..."));
						}
						 
					}
				  });
				    
				    
				    
				    
			  break;
			  
			  case 10:// about
				  rootView = inflater.inflate(R.layout.about, container,
							false);
			  break;
			  
		
			  
			  default:
				/*
				 * 
				 * if you are adding a new fragment without child tabs then be sure to include it in if statement on button click listener method. or it
				 * will give u no view found error.
				 * 
				 * 
				 */
			  break;
			  }
			
			
			
          
			
				return rootView;
			
			
			
		
		}
		private ArrayList<ListviewContactItem> GetlistContact(){
		    ArrayList<ListviewContactItem> contactlist = new ArrayList<ListviewContactItem>();

		    ListviewContactItem contact = new ListviewContactItem();

		    contact.setName("University Office Operator Service (Exchange)");
		    contact.setPhone("914144238282");
		    contactlist.add(contact);

		    contact = new ListviewContactItem();
		    contact.setName("May I Help – Desk");
		    contact.setPhone("914144238348");
		    contactlist.add(contact);

		    contact = new ListviewContactItem();
		    contact.setName("Examination Enquiry Desk");
		    contact.setPhone("914144238358");
		    contactlist.add(contact);

		    contact = new ListviewContactItem();
		    contact.setName("Joint Controller of Examinations");
		    contact.setPhone("914144237368");
		    contactlist.add(contact);
		    
		    contact = new ListviewContactItem();
		    contact.setName("Directorate of Distance Education Enquiry Desk");
		    contact.setPhone("914144238610");
		    contactlist.add(contact);
		  
		    
		    contact = new ListviewContactItem();
		    contact.setName("Students Grievances Redressal Cell(Only SMS)");
		    contact.setPhone("9486927780");
		    contactlist.add(contact);
		    
		    contact = new ListviewContactItem();
		    contact.setName("Rajah Muthiah Medical College & Hospital ");
		    contact.setPhone("914144238147");
		    contactlist.add(contact);
		    
		    contact = new ListviewContactItem();
		    contact.setName("Controller of Examinations ");
		    contact.setPhone("auexamdde.W@gmail.com");
		    contactlist.add(contact);
		    
		    contact = new ListviewContactItem();
		    contact.setName("Director, Directorate of Distance Education");
		    contact.setPhone("ddedirector2013@gmail.com");
		    contactlist.add(contact);
		    
		    
		    
		    
		    
		    return contactlist; 
		    }   
		
		  private void generateAddress(String[] paramArrayOfString)
		  {
		   DataHolder dh= DataHolder.getInstance();
		   dh.phone_number = paramArrayOfString[9];
		   dh.Gmap_address= paramArrayOfString[2] + ", "+paramArrayOfString[3]+", "+paramArrayOfString[4]
				   				+", "+paramArrayOfString[7]+", "+paramArrayOfString[8];		   
		   
		 
		  }
		  
		  public void loaddata(String paramString)
		  {
			  contextHolder ch = contextHolder.getInstance();
		    new Database_Handler(ch.con);
		    Database_Handler localDatabaseHandler = new Database_Handler(ch.con);
		    try
		    {
		      localDatabaseHandler.openDataBase();
		      Log.d("note1", paramString);
		      String[] lables1 = localDatabaseHandler.getdetails(paramString);
		      localDatabaseHandler.close();
		      generateAddress(lables1);
		      TextView[] tx = new TextView[10];
			  String ids1[] = new String[10];
			  for(int j=0; j<10; j++)
			  {
			     ids1[j] = "sc" + Integer.toString(j);
			  }
			  
			  for(int i1=0; i1<10; i1++)
			  {
			     int resID = getResources().getIdentifier(ids1[i1], "id", "com.example.studentapp");
			     tx[i1] = (TextView) searchview.findViewById(resID);
		      tx[i1].setText(lables1[i1]);
			  }
		      return;
		    }
		    catch (SQLException localSQLException)
		    {
		      throw localSQLException;
		    }
		    
		  }
		
		private class SpinnerItemClickListener implements OnItemSelectedListener {
			   
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				DataHolder dh = DataHolder.getInstance();
				
				
				Log.d("pos",""+arg2);
			
		
		
			if(Spinner_mode==0)//executes for Grievance Spinner
			{
				String s[] = new String[4];
				s=getResources().getStringArray(R.array.grievance_array);
			dh.G_Spinner_entry=s[arg2];
			Log.d("view",""+ Spinner_mode);
			}
			else//executes for Search Center Spinner
			{
				Log.d("view",""+ Spinner_mode);
				
				String selected=lables.get(arg2);//this is the selected item
				loaddata(selected);
			}
			}

			
			
			
			
			
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		}
	}

}
