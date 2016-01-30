package com.example.studentapp;



import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;
 



public class messagesactivity extends Activity {

	 
	    ExpandableListViewAdapter listAdapter;
	    ExpandableListView expListView;
	    List<String> listDataHeader;
	HashMap<String, List<newsstructure>> listDataChild;

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.messages);
	 
	        // get the listview
	        expListView = (ExpandableListView) findViewById(R.id.lvExp);
	        Log.d("message activity", "1");
	        // preparing list data
	        prepareListData();
	    
	        listAdapter = new ExpandableListViewAdapter(this, listDataHeader, listDataChild);
	 
	        // setting list adapter
	        expListView.setAdapter(listAdapter);
	        
	        Log.d("message activity", "1");
	        
	        expListView.setOnChildClickListener(new OnChildClickListener() {
	        	 
	            @Override
	            public boolean onChildClick(ExpandableListView parent, View v,
	                    int groupPosition, int childPosition, long id) {
	                
	                return false;
	            }
	        });
	        
	        
	        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {
	        	 
	            @Override
	            public void onGroupExpand(int groupPosition) {
	               
	            }
	        });
	        
	        
	     // Listview Group collasped listener
	        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
	         
	            @Override
	            public void onGroupCollapse(int groupPosition) {
	                
	            }
	        });
	        
			final Database_Handler db = new Database_Handler(this);
	        
	        Button bt1 = (Button) findViewById(R.id.buttonclearnews);
	        
	        bt1.setOnClickListener(new OnClickListener(){
	        	public void onClick(View v)
	        	{
	        		try{
	        		db.clear_news();
	        		
	        		}catch(Exception e)
	        		{
	        			Log.d("Exception", e.toString());
	        		}
	        		
	        		prepareListData();
	        		listAdapter.changedata(listDataHeader, listDataChild);
	        		listAdapter.notifyDataSetChanged();
	        	}
	        	
	        	
	        });
	        
	        
	        
	    }
	 
	    /*
	     * Preparing the list data
	     */
	    private void prepareListData() {
	        listDataHeader = new ArrayList<String>();
	        listDataChild = new HashMap<String, List<newsstructure>>();
	   
	        
	        List<newsstructure> newsaggregate = new ArrayList<newsstructure>();
	        Database_Handler dh = new Database_Handler(this);
	        
	    	try {
				dh.createDataBase();
				dh.openDataBase();   
			      
				 newsaggregate.addAll(dh.getAllNews()) ;
				} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	        
	        for(int k=0 ; k<newsaggregate.size();k++)
	        {
	        	Log.d("newsaggregate position "+ k, newsaggregate.get(k).newsNo);
	        }
	        
	int j=0;
	int number=1;
	
	        // Adding header data
	 for(int i=newsaggregate.size()-1; i > -1 ;i--)
	 {
		Log.d("count i", ""+i);
		 listDataHeader.add(number + ". " + newsaggregate.get(i).newsTitle);
		 newsstructure n = new newsstructure();
		 List<newsstructure> n1 = new ArrayList<newsstructure>();
		
		 n = newsaggregate.get(i);
		 n1.add(n);
		 listDataChild.put(listDataHeader.get(j),n1);
		 Log.d("message activity",listDataHeader.get(j));
		 j=j+1;
		 number++;
	 }
	 
	 
	 
	    }
	
  
    
    


    
}