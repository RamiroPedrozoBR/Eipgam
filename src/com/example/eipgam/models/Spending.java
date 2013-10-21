package com.example.eipgam.models;


import java.util.Date;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.eipgam.helpers.data.EntityBase;
import com.example.eipgam.helpers.data.IRow;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="Spendings")
public class Spending extends EntityBase implements IRow {

	@DatabaseField	
	private String name;
	@DatabaseField
	private double value;
	@DatabaseField(foreign = true)
	private SpendingType type;
	@DatabaseField(foreign = true)
	private PayType payType;
	@DatabaseField
	private int plots;
	@DatabaseField(dataType = DataType.DATE )
	private Date date;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	public int getPlots() {
		return plots;
	}
	public void setPlots(int plots) {
		this.plots = plots;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public View getRow(Context context) {
		
		TextView text = new TextView(context);
        
        text.setText(name);		
		
        //Utils.converterByteToImage(context, icon);
		
        return	text;
	}
	public SpendingType getType() {
		return type;
	}
	public void setType(SpendingType type) {
		this.type = type;
	}
	
	public PayType getPayType() {
		return payType;
	}
	public void setPayType(PayType payType) {
		this.payType = payType;
	}
}

