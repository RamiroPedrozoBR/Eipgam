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
@DatabaseTable(tableName="Goals")
public class Goal extends EntityBase implements IRow {

	@DatabaseField
	private String name;
	@DatabaseField
	private double value;
	@DatabaseField
	private double amountCollected;
	@DatabaseField(foreign = true)
	private GoalType type;
	@DatabaseField(dataType =DataType.DATE)
	private Date date;
	@DatabaseField
	private int plots;
	@DatabaseField
	private boolean enableAlert;
	@DatabaseField
	private boolean enableSpending;
	@DatabaseField
	private String tags;
	@DatabaseField(foreign = true)
	private SpendingType spendingType;
	
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
	public GoalType getType() {
		return type;
	}
	public void setType(GoalType type) {
		this.type = type;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getPlots() {
		return plots;
	}
	public void setPlots(int plots) {
		this.plots = plots;
	}
	public boolean isEnableAlert() {
		return enableAlert;
	}
	public void setEnableAlert(boolean enableAlert) {
		this.enableAlert = enableAlert;
	}
	public boolean isEnableSpending() {
		return enableSpending;
	}
	public void setEnableSpending(boolean enableSpending) {
		this.enableSpending = enableSpending;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public double getAmountCollected() {
		return amountCollected;
	}
	public void setAmountCollected(double amountCollected) {
		this.amountCollected = amountCollected;
	}
	@Override
	public View getRow(Context context) {
	
		TextView text = new TextView(context);
        
        text.setText(name);		
		
        //Utils.converterByteToImage(context, icon);
		
        return	text;
	}
	public SpendingType getSpendingType() {
		return spendingType;
	}
	public void setSpendingType(SpendingType spendingType) {
		this.spendingType = spendingType;
	}
	
}
