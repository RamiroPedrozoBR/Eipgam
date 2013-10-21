package com.example.eipgam.helpers.data;

import java.io.File;
import java.io.IOException;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.DropBoxManager;

public class DatabaseInitializer extends SQLiteOpenHelper{
	
    private static String DB_PATH = "/data/data/Eipgam/databases/";
    							    
    private static String DB_NAME = "eipgamDb.sqlite";
    private static final int DATABASE_VERSION = 2;
 
    private SQLiteDatabase database;

	private Context context; 
   
    
    public DatabaseInitializer(Context context) {
    	super(context, DB_NAME, null, DATABASE_VERSION);
       this.context = context;
    }	
    
	public boolean DropDataBase(){
		return context.deleteDatabase(DB_PATH+DB_NAME);
	}
    
    public void createDatabase() throws IOException{
    	
    	boolean dbExist = checkDatabase();
 
    	if(!dbExist){
        	this.getReadableDatabase();
    	}
 
    }
    
 
    private boolean checkDatabase(){
 
    	 File dbFile = new File(DB_PATH + DB_NAME);
         return dbFile.exists();
    }
 
    @Override
	public synchronized void close() {
	    if(database != null)
		    database.close();
	   
	    super.close();
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
 
}
