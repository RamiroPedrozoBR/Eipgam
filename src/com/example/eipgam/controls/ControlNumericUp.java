package com.example.eipgam.controls;

import com.example.eipgam.R;
import com.example.eipgam.helpers.Utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class ControlNumericUp extends LinearLayout  {


	private int value;
	private boolean isSupendLayout;
	private Button bntSum;
	private Button bntSubtract;
	private EditText editDisplay;
	private int minValue;
	private int maxValue;

	public ControlNumericUp(Context context, AttributeSet attrs) {
		super(context, attrs);

		TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.numericUp, 0, 0);
		value= (a.getInt(R.styleable.numericUp_defaultValue,0));
		maxValue =(a.getInt(R.styleable.numericUp_maxValue,100));
		minValue= (a.getInt(R.styleable.numericUp_minimum,0));
		a.recycle();

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.control_numeric_up, this, true);

		bntSum = (Button) findViewById(R.id.control_numeric_up_bnt_sum);
		editDisplay = (EditText) findViewById(R.id.control_numeric_up_edit_display);
		bntSubtract = (Button) findViewById(R.id.control_numeric_up_bnt_subtract);

		editDisplay.setText(Integer.toString(getValue()));

		editDisplay.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				RefreshValueByDisplay();
				return false;
			}
		});
		bntSum.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				RefreshValue(value + 1);
			}
		});
		bntSubtract.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				RefreshValue(value - 1);
			}
		});


	}

	private void RefreshValueByDisplay(){
		
		if(isSupendLayout)
			return;
		
		String text = editDisplay.getText().toString();
		
		if(Utils.isNullOrEmpty(text)){
			RefreshValue(minValue);
			return;
		}
		
		int newValue =	Integer.parseInt(text);
		RefreshValue(newValue);
	}
	private void RefreshValue(int newValue) {

		isSupendLayout = true;
		if(newValue >= maxValue)		
			value = maxValue;
		else
			if(newValue <= minValue)		
				value = minValue;
			else
				value = newValue;

		
		editDisplay.setText(Integer.toString(getValue()));

		isSupendLayout = false;

	}

	public void setValue(int value) {
		RefreshValue(value);
	}

	public void setMinValue(int minValue) {
		this.minValue = minValue;
		RefreshValue(value);
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
		RefreshValue(value);
	}

	public int getMaxValue() {
		return maxValue;
	}
	public int getMinValue() {
		return minValue;
	}
	public int getValue() {
		return value;
	}


}
