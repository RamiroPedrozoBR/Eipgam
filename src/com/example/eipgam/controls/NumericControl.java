package com.example.eipgam.controls;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class NumericControl extends LinearLayout{

	public NumericControl(Context context) {
		super(context);
		 
		Button btn = new Button(context);
		btn.setText("Clicked");
		btn.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
		addView(btn);
	}
	
	public NumericControl(Context context, AttributeSet attrs) {
		super(context, attrs);
	
	}

	

}
