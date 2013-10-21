package com.example.eipgam.services;

import java.util.List;

import com.example.eipgam.helpers.Utils;
import com.example.eipgam.models.User;

public class UserServices extends ServicesBase<User> {

	public UserServices() { super(User.class); }

	public User getCadastre()
	{
		List<User> list = GetRepository().getAll();

		if(Utils.isNullOrEmpty(list))
			return null;

		return  list.get(0);

	}


}
