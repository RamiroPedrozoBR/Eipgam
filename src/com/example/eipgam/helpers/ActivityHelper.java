package com.example.eipgam.helpers;

import com.example.eipgam.R;
import com.example.eipgam.user.CadastreActivity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

public class ActivityHelper{
	
	
	private Activity context;

	public ActivityHelper(Activity context){
		this.context= context;
	}
	
	public void frameSetView(int key){
		FrameLayout frame  = ((FrameLayout)this.context.findViewById(key));
		
	}

	public  String editGetText(int key) {
		
		EditText edit  = ((EditText)this.context.findViewById(key));
		String text = edit.getText().toString();
		
		if(Utils.isNullOrEmpty(text))
			return "";
				
		return text.trim();	
	}
	public  double editGetDouble(int key) {
	
		String text =editGetText(key);	
		
		if(Utils.isNullOrEmpty(text))
			return 0;
		 
	return	Double.parseDouble(text.trim()); 
	
	}
	public  int editGetInt(int key) {
		
		
		String text =editGetText(key);
		
		if(Utils.isNullOrEmpty(text))
			return 0;
		 
	return	Integer.parseInt(text.trim()); 
	
	}
	public  void textViewSetText(int key,String value) {
		
		TextView edit  = ((TextView)this.context.findViewById(key));
		edit.setText(value);
			
	}
	public  void editSetText(int key,String value) {
		
		EditText edit  = ((EditText)this.context.findViewById(key));
		edit.setText(value);
			
	}
	public  void editSetDouble(int key,double value) {
		
		editSetText(key, Double.toString(value));
	}
	public  void editSetInt(int key,Integer value) {
	
		editSetText(key, Integer.toString(value));
	}	

	public  void ViewSetVisibility(int key,boolean isVisibility) {
		
		View layout  = ((View)this.context.findViewById(key));
		
		if(isVisibility)		
			layout.setVisibility(LinearLayout.VISIBLE);
		else
			layout.setVisibility(LinearLayout.INVISIBLE);
	}



	public void textViewSetInt(int key, int value) {
		textViewSetText(key, Integer.toString(value));
	}	

	public void addTab(TabHost tabHost, int idString,Class<?> classNa) {
        
		TabSpec tab = tabHost.newTabSpec("tag Cadastro");
        tab.setIndicator(context.getString(idString));
        Intent songsIntent = new Intent(this.context, classNa);
        
        tab.setContent(songsIntent);
        
        tabHost.addTab(tab); 
		
	}
	
}
