package com.example.eipgam;
import java.sql.SQLException;

import com.example.eipgam.R.id;
import com.example.eipgam.goal.GoalActivity;
import com.example.eipgam.helpers.ActivityHelper;
import com.example.eipgam.helpers.data.ProviderHelper;
import com.example.eipgam.models.User;
import com.example.eipgam.services.UserServices;
import com.example.eipgam.spending.SpendingActivity;
import com.example.eipgam.user.LoginActivity;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {

	private User currentUser;
	private ActivityHelper helper;
	private UserServices service;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	     Intent question = new Intent(this, LoginActivity.class);
		 startActivityForResult(question,R.layout.view_login);
    }
	@Override
	public void onResume(){
	    super.onResume();
	    setContentView(R.layout.activity_main);
		 initializeTabHost();
		try {
			initializeData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initializeTabHost() {
		TabHost tabHost = getTabHost();
		tabHost.setup();

		//addTab(tabHost,R.string.title_activity_login,LoginActivity.class);
		addTab(tabHost,R.string.title_activity_summary,SumaryActivity.class);
		addTab(tabHost,R.string.title_activity_spending,SpendingActivity.class);
		addTab(tabHost,R.string.title_activity_goal,GoalActivity.class);
		addTab(tabHost,R.string.title_activity_config,ConfigActivity.class);
		addTab(tabHost,R.string.title_activity_saving_tips,SavingTipsActivity.class);
	}


	private void addTab(TabHost tabHost,int titleId,Class<?> classActivity) {
		TabSpec tab = tabHost.newTabSpec("Cadastro");
		tab.setIndicator(getString(titleId));
		Intent content = new Intent(this, classActivity);
		tab.setContent(content);
		tabHost.addTab(tab);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	 @Override
	  public void onActivityResult(int requestCode, int resultCode, Intent data) {
	   
		 if(requestCode == R.layout.view_login)		
		 	if(resultCode != RESULT_OK) 
		 		finish();
		 
	  }
	
	private void initializeData() throws SQLException {
		ProviderHelper.Initialize(this);
		helper = new ActivityHelper(this);
		service = new UserServices();
		currentUser = service.getCadastre();
	}


}
