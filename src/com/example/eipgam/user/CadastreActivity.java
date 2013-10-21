package com.example.eipgam.user;

import java.sql.SQLException;

import com.example.eipgam.R;
import com.example.eipgam.R.id;
import com.example.eipgam.R.layout;
import com.example.eipgam.R.menu;
import com.example.eipgam.helpers.ActivityHelper;
import com.example.eipgam.models.User;
import com.example.eipgam.services.UserServices;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class CadastreActivity extends Activity {

	private User currentUser;
	private ActivityHelper helper;
	private UserServices service;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_cadastre);

		this.helper = new ActivityHelper(this);
		 service = new UserServices();
		currentUser = service.getCadastre();
		
		loadInterface(currentUser);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastre, menu);
		return true;
	}

	public void onClickOk(View view) {
		currentUser = loadUser(currentUser);
		currentUser.setId(service.saveOrUpdate(currentUser));
		
		setResult(RESULT_OK);
		finish();
	}

	public void onClickCancel(View view) {
		setResult(RESULT_CANCELED);
		finish();
	}

	private void loadInterface(User contextUser) {

		if (contextUser == null)
			return;

		helper.editSetText(R.id.cadastre_edit_email, contextUser.getEmail());
		helper.editSetText(R.id.cadastre_edit_name, contextUser.getUserName());
		helper.editSetDouble(R.id.cadastre_edit_salary, contextUser.getSalary());
		helper.editSetInt(R.id.cadastre_edit_payDay, contextUser.getSalaryDay());
		helper.editSetText(R.id.cadastre_edit_psw, contextUser.getPsw());
		helper.editSetText(R.id.cadastre_edit_pswRepite,
				contextUser.getPswRepite());

	}

	private User loadUser(User contextUser) {

		if (contextUser == null)
			contextUser = new User();

		contextUser.setEmail(helper.editGetText(R.id.cadastre_edit_email));
		contextUser.setUserName(helper.editGetText(R.id.cadastre_edit_name));
		contextUser.setSalary(helper.editGetDouble(R.id.cadastre_edit_salary));
		contextUser.setSalaryDay(helper.editGetInt(R.id.cadastre_edit_payDay));
		contextUser.setPsw(helper.editGetText(R.id.cadastre_edit_psw));
		contextUser.setPswRepite(helper
				.editGetText(R.id.cadastre_edit_pswRepite));
		return contextUser;
	}

}
