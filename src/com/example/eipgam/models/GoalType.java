package com.example.eipgam.models;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.eipgam.helpers.data.EntityBase;
import com.example.eipgam.helpers.data.IRow;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="GoalTypes")
public class GoalType extends EntityBase implements IRow{
	
	@DatabaseField
	private String name;
	@DatabaseField
	private String description ;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public View getRow(Context context) {
	
		TextView text = new TextView(context);
        
        text.setText(name);		
		
        //Utils.converterByteToImage(context, icon);
		
        return	text;
	}
	
}
