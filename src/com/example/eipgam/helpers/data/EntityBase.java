package com.example.eipgam.helpers.data;



import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;

@SuppressWarnings("serial")
public abstract class EntityBase implements Serializable {
	
	
	@DatabaseField(generatedId = true)
	private int id;


	public	int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
