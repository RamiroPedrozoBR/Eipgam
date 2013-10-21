package com.example.eipgam.helpers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.example.eipgam.R;
import com.j256.ormlite.field.DataType;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;

public class Utils {
	public static boolean isNullOrEmpty(String s) {
		return s == null || s.length() == 0;
	}

	public static boolean isNullOrWhitespace(String s) {
		return s == null || isWhitespace(s);

	}
	private static boolean isWhitespace(String s) {
		int length = s.length();
		if (length > 0) {
			for (int i = 0; i < length; i++) {
				if (!Character.isWhitespace(s.charAt(i))) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	public static boolean isNullOrEmpty(List<?> list){

		return list == null || list.isEmpty();

	}
	public static boolean equalsIfNotNull(String a,String b) {


		return	a != null && 
				b!= null &&
				b.equals(a);

	}
	public static ImageView converterByteToImage(Context context,byte[] icon){

		Bitmap bmp=BitmapFactory.decodeByteArray(icon,0,icon.length);

		ImageView image=new ImageView(context);

		image.setImageBitmap(bmp);

		return image;
	}

	public static int getMaxDaysOfMonth(int yers,int month) {
		Calendar mycal = new GregorianCalendar(yers, month-1, 1);
		int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return daysInMonth;
	}

	public static Date StringToDate(String dateString) {

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try 
		{
			Date date = formatter.parse(dateString);

			return date;

		} catch (ParseException e) {

			e.printStackTrace();
		}
		return null;

	}

	public static void changeTypeDatePicker(DatePicker datePicker,TypeDatePicker typeShow) {
		//mMonthSpinner
		//mYearSpinner
		try{
			java.lang.reflect.Field[] f = datePicker.getClass().getDeclaredFields();
			for (java.lang.reflect.Field field : f) 
			{

				if(typeShow == TypeDatePicker.Show_Days)		
				{
					if (field.getName().equals("mMonthSpinner") ||field.getName().equals("mMonthPicker")||
							field.getName().equals("mYearSpinner") ||field.getName().equals("mYearPicker")) 
							fieldSetViewGone(datePicker, field);
				}

				if(typeShow == TypeDatePicker.Show_Days_Month)		
				{
					if (field.getName().equals("mMonthSpinner") ||field.getName().equals("mMonthPicker")) 
						fieldSetViewVisible(datePicker, field);
					
					if (field.getName().equals("mYearSpinner") ||field.getName().equals("mYearPicker")) 
						fieldSetViewGone(datePicker, field);
				}
				if(typeShow == TypeDatePicker.Show_Normal)		
				{
					if (field.getName().equals("mMonthSpinner") ||field.getName().equals("mMonthPicker")||
							field.getName().equals("mYearSpinner") ||field.getName().equals("mYearPicker")) 
						fieldSetViewVisible(datePicker, field);
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}

	}

	private static void fieldSetViewGone(DatePicker datePicker,
			java.lang.reflect.Field field) throws IllegalAccessException {
		field.setAccessible(true);
		Object yearPicker = new Object();
		yearPicker = field.get(datePicker);
		((View) yearPicker).setVisibility(View.GONE);
	}
	private static void fieldSetViewVisible(DatePicker datePicker,
			java.lang.reflect.Field field) throws IllegalAccessException {
		field.setAccessible(true);
		Object yearPicker = new Object();
		yearPicker = field.get(datePicker);
		((View) yearPicker).setVisibility(View.VISIBLE);
	}
	public enum TypeDatePicker{
		Show_Days_Month,
		Show_Days,
		Show_Normal,
	}

	@SuppressLint("SimpleDateFormat")
	public static String DateToString(java.util.Date dtData){  
		SimpleDateFormat formatBra;     
		formatBra = new SimpleDateFormat("dd/MM/yyyy");  
		try {  
			java.util.Date newData = formatBra.parse(dtData.toString());  
			return (formatBra.format(newData));  
		} catch (ParseException Ex) {  
			return "Erro na conversão da data";  
		}  
	}  
}
