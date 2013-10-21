package com.example.eipgam.user;

import java.sql.SQLException;

import com.example.eipgam.R;
import com.example.eipgam.R.id;
import com.example.eipgam.R.layout;
import com.example.eipgam.R.menu;
import com.example.eipgam.helpers.ActivityBase;
import com.example.eipgam.helpers.ActivityHelper;
import com.example.eipgam.helpers.Utils;
import com.example.eipgam.helpers.data.ProviderHelper;
import com.example.eipgam.models.User;
import com.example.eipgam.services.UserServices;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class LoginActivity extends ActivityBase {

	private User currentUser;
	private ActivityHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_login);

		 UserServices service;
		service = new UserServices();
		 currentUser = service.getCadastre();
	    
		
		helper = new  ActivityHelper(this);

			if(currentUser != null)
				helper.textViewSetText(R.id.main_edit_userName, currentUser.getUserName());
		
			helper.ViewSetVisibility(R.id.main_login_grid, currentUser != null);
			helper.ViewSetVisibility(R.id.main_cadastre_grid, currentUser == null);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;

	}


	public void onClickLogin(View view) throws Throwable {

		String psw =  currentUser.getPsw();
		String editPsw = helper.editGetText(R.id.main_edit_psw);

		if(Utils.equalsIfNotNull(editPsw,psw)){
			setResult(RESULT_OK);
			finish();
		}
		else{
			
			showWindows("Senha incorreta!","OK");
		}

	}
	 @Override
	  public void onActivityResult(int requestCode, int resultCode, Intent data) {
	   
		 if(requestCode == R.layout.view_cadastre)		
		 	 if(resultCode == RESULT_OK)
		 	 {
		 		setResult(RESULT_OK);
				finish();
		 	 }
	  }
	
	public void onClickCadastre(View view) {
		Intent questionCadastre = new Intent(this,CadastreActivity.class);
		startActivityForResult(questionCadastre ,R.layout.view_cadastre);
	}
}
