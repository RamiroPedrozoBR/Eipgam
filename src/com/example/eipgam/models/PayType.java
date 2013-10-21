package com.example.eipgam.models;

import java.io.Serializable;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eipgam.helpers.Utils;
import com.example.eipgam.helpers.data.EntityBase;
import com.example.eipgam.helpers.data.IRow;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="PayTypes")
public class PayType extends EntityBase implements  IRow{

	
	
	@DatabaseField
	private String name;
	@DatabaseField
	private String descriptions;
	@DatabaseField(dataType = DataType. BYTE_ARRAY ) 
	private byte [] icon;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	public void setIcon(byte [] icon) {
		this.icon = icon;
	}
	

	@Override
	public View getRow(Context context) {
		
		TextView text = new TextView(context);
        
        text.setText(name);		
		
        //Utils.converterByteToImage(context, icon);
		
        return	text;
	}
	
	

	
}
