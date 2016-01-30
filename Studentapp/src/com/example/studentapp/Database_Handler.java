package com.example.studentapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;



public class Database_Handler extends SQLiteOpenHelper{
	
	
	
	
	
	
	 private static final int DATABASE_VERSION = 1;
	private static String DB_NAME = "Studentdb";
	  private static String DB_PATH = "/data/data/com.example.studentapp/databases/";
	  private static final String TABLE_LABELS = "PCP_CENTRE";
	  private Context myContext=null;
	  private SQLiteDatabase myDataBase;
	  
	  
	  public Database_Handler(Context con)
	  {
	    super(con, DB_NAME, null, 1);
	   
	    if(android.os.Build.VERSION.SDK_INT >= 17){
	        DB_PATH = con.getApplicationInfo().dataDir + "/databases/";         
	     }
	     else
	     {
	        DB_PATH = "/data/data/" + con.getPackageName() + "/databases/";
	     }
	    
	    
	    Log.d("Note", "Point 0");
	    this.myContext = con;
	  }
	  
	  
	  
	  public void createDataBase() throws IOException
	  {
	      //If database not exists copy it from the assets

	      boolean mDataBaseExist = checkDataBase();
	      if(!mDataBaseExist)
	      {
	          this.getReadableDatabase();
	          this.close();
	          try 
	          {
	              //Copy the database from assests
	              copyDataBase();
	              Log.e("TAG", "createDatabase database created");
	          } 
	          catch (IOException mIOException) 
	          {
	              throw new Error("ErrorCopyingDataBase");
	          }
	      }
	  }
	  
	  private boolean checkDataBase()
	    {
	        File dbFile = new File(DB_PATH + DB_NAME);
	        //Log.v("dbFile", dbFile + "   "+ dbFile.exists());
	        return dbFile.exists();
	    }

	    //Copy the database from assets
	    private void copyDataBase() throws IOException
	    {
	        InputStream mInput = myContext.getAssets().open(DB_NAME);
	        String outFileName = DB_PATH + DB_NAME;
	        OutputStream mOutput = new FileOutputStream(outFileName);
	        byte[] mBuffer = new byte[1024];
	        int mLength;
	        while ((mLength = mInput.read(mBuffer))>0)
	        {
	            mOutput.write(mBuffer, 0, mLength);
	        }
	        mOutput.flush();
	        mOutput.close();
	        mInput.close();
	    }
	  
	  
	    public List<newsstructure> getAllNews(){
	        List<newsstructure> names = new ArrayList<newsstructure>();
	          
	        // Select All Query
	        String selectQuery = "SELECT * FROM news";
            openDataBase();
            //close();
	        SQLiteDatabase db = myDataBase;
	        Cursor cursor = db.rawQuery(selectQuery, null);
	     
	       
	        newsstructure nw;
	        
	        
	        
	        
	        
	        // looping through all rows and adding to list
	        if (cursor.moveToFirst()) {
	            do {
	            	nw = new newsstructure();
	              nw.newsTitle = cursor.getString(0);
	              nw.newsContent = cursor.getString(1);
	              nw.newsDate = cursor.getString(2);
	              nw.newsNo = cursor.getString(3);
	               
	              names.add(nw);
	             
	            } while (cursor.moveToNext());
	        }
	          
	        // closing connection
	        cursor.close();
	        db.close();
	         
	          
	          
	          
	          
	        // returning names
	        return names;
	    }
	    
	    
	    public void clear_news()
	    {
	    	
	    	String delete ="DELETE FROM news WHERE news_no <> '1';";
	    	
	    	openDataBase();
	    	SQLiteDatabase db =myDataBase;
	    	db.execSQL(delete);
	    	db.close();
	    	
	    }
	    
	    
	    
	    
	    
	    
	    public  void insert_news(newsstructure ns)
	    {
	    	String insert="INSERT INTO news (news_title, news_content, news_date, news_no) VALUES ('"+ ns.newsTitle +"', '"+ns.newsContent + " ', ' "+ns.newsDate+ " ', '"+ns.newsNo  +"')";
	    	 openDataBase();
	            //close();
		        SQLiteDatabase db = myDataBase;
		        db.execSQL(insert);
		        db.close();
	    }
	  
	  public List<String> getAllNames(){
	        List<String> names = new ArrayList<String>();
	          
	        // Select All Query
	        String selectQuery = "SELECT * FROM PCP_CENTRE";
            openDataBase();
            //close();
	        SQLiteDatabase db = myDataBase;
	        Cursor cursor = db.rawQuery(selectQuery, null);
	       
	        // looping through all rows and adding to list
	        if (cursor.moveToFirst()) {
	            do {
	                names.add(cursor.getString(1));
	            } while (cursor.moveToNext());
	        }
	          
	        // closing connection
	        cursor.close();
	        db.close();
	          
	        // returning names
	        return names;
	    }
	  
	  public String[] getdetails(String paramString)
	  {
	    String[] arrayOfString = new String[30];
	    String str = "SELECT * FROM PCP_CENTRE WHERE X_PCP_NAME='" + paramString + "'";

	    close();
	    SQLiteDatabase localSQLiteDatabase = getReadableDatabase();
	    Cursor localCursor = localSQLiteDatabase.rawQuery(str, null);
	    if (localCursor.moveToFirst()) {
	      do
	      {
	        arrayOfString[0] = localCursor.getString(0);
	        arrayOfString[1] = localCursor.getString(1);
	        arrayOfString[2] = localCursor.getString(2);
	        arrayOfString[3] = localCursor.getString(3);
	        arrayOfString[4] = localCursor.getString(4);
	        arrayOfString[5] = localCursor.getString(5);
	        arrayOfString[6] = localCursor.getString(6);
	        arrayOfString[7] = localCursor.getString(7);
	        arrayOfString[8] = localCursor.getString(8);
	        arrayOfString[9] = localCursor.getString(9);
	        arrayOfString[10] = localCursor.getString(10);
	      } while (localCursor.moveToNext());
	    }
	    localCursor.close();
	    localSQLiteDatabase.close();
	    return arrayOfString;
	  }
	 
	  
	  
	  @Override
	    public synchronized void close() 
	    {
	        if(myDataBase != null)
	            myDataBase.close();
	        super.close();
	    }
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		try {
			createDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	public void openDataBase() {
		// TODO Auto-generated method stub
	    this.myDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, 0);

	}
	
	
	
}