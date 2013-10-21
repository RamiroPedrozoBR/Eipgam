package com.example.eipgam.helpers;

import java.util.Date;

import com.example.eipgam.adapters.ListAdapter;
import android.app.Activity;
import android.app.AlertDialog;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class ActivityBase extends Activity{

	//GET
	public  String editGetText(int key) {

		EditText edit  = ((EditText)this.findViewById(key));
		String text = edit.getText().toString();

		if(Utils.isNullOrEmpty(text))
			return "";

		return text.trim();	
	}
	@SuppressWarnings("deprecation")
	public  Date datePickerGetDate(int key) {

		DatePicker datePicker  = ((DatePicker)this.findViewById(key));

		int day = datePicker.getDayOfMonth();
		int month = datePicker.getMonth();
		int year = datePicker.getYear();


		Date selectedDate = new Date(year-1900, month, day);



		return selectedDate;
	}
	public Object spinnerGetSelected(int key) {

		Spinner auto =(Spinner)findViewById(key);
		return auto.getSelectedItem();
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
	public  boolean chGetChecked(int key) {
		
		CheckBox ch  = ((CheckBox)this.findViewById(key));
	return ch.isChecked();

}
	//SET
	
		public  void chSetchecked(int key,boolean value) {
	
			CheckBox ch  = ((CheckBox)this.findViewById(key));
		ch.setChecked(value);

	}
	public  void textViewSetText(int key,String value) {
		if(Utils.isNullOrEmpty(value))
			return;
		TextView edit  = ((TextView)this.findViewById(key));
		edit.setText(value);

	}
	public  void editSetText(int key,String value) {
		if(Utils.isNullOrEmpty(value))
			return;
		EditText edit  = ((EditText)this.findViewById(key));
		edit.setText(value);

	}
	public  void editSetDouble(int key,double value) {

		editSetText(key, Double.toString(value));
	}
	public  void editSetInt(int key,Integer value) {

		editSetText(key, Integer.toString(value));
	}	
	public  void viewSetVisibility(int key,boolean isVisibility) {

		View layout  = ((View)this.findViewById(key));

		if(isVisibility)		
			layout.setVisibility(LinearLayout.VISIBLE);
		else
			layout.setVisibility(LinearLayout.INVISIBLE);
	}
	public void textViewSetInt(int key, int value) {
		textViewSetText(key, Integer.toString(value));
	}	
	public void spinnerSetAdapter(int key, ListAdapter<?> adapter,OnItemSelectedListener onItemSelectedListener) {
		if(adapter == null)
			return;

		Spinner auto = (Spinner)findViewById(key);
	
		auto.setAdapter(adapter);
		
		if(onItemSelectedListener !=null)
			auto.setOnItemSelectedListener( onItemSelectedListener);
		
		
		
	}	
	public  void datePickerSetDate(int key,Date date) {

		int  year = date.getYear();
		int month = date.getMonth();
		int day = date.getDay();

		if(date == null)
			return;

		//	showWindows(date.toLocaleString(),"Data do banco");
		DatePicker datePicker  = ((DatePicker)this.findViewById(key));
		datePicker.init(year+1900,month,date.getDate(),null);

	}


	public void spinnerSetSelected(int key,int id) {

		Spinner auto =(Spinner)findViewById(key);

		ListAdapter<?> ada = (ListAdapter<?>)auto.getAdapter();

		int position = ada.getPositionBy(id);

		auto.setSelection(position);

	}	
	public void lisViewSetAdapter(int key,ListAdapter<?> adapter,boolean isItemClick,boolean isLongClick) {
		OnItemClickListener click = null;
		OnItemLongClickListener longClick = null;

		if(isItemClick)
			click =(OnItemClickListener)this;

		if(isLongClick)
			longClick =(OnItemLongClickListener)this;

		lisViewSetAdapter(key, adapter,click,longClick);
	}

	public void lisViewSetAdapter(int key,ListAdapter<?> adapter) {
		lisViewSetAdapter(key, adapter,null,null);
	}
	public void lisViewSetAdapter(int key,ListAdapter<?> adapter, OnItemClickListener onClickItem,OnItemLongClickListener onLongClick) {

		ListView auto =(ListView)findViewById(key);
		auto.setAdapter(adapter);
		if(onLongClick!=null)	
			auto.setOnItemLongClickListener(onLongClick);
		if(onClickItem!=null)	
			auto.setOnItemClickListener(onClickItem);
	}

	public void showWindows(String title,String body){
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle(title);
		alert.setPositiveButton(body,null);
		alert.show();	
	}
	public void showWindows(String title,String body,android.content.DialogInterface.OnClickListener onClickOk){
		AlertDialog.Builder adb=new AlertDialog.Builder(this);
		adb.setTitle(title);
		adb.setMessage(body);
		adb.setNegativeButton("Cancel", null);
		adb.setPositiveButton("Ok", onClickOk);
		adb.show();
	}
	public void showWindows(int titleId,int bodyId){
		showWindows(getString(titleId),getString(bodyId));
	}
}
