package com.example.eipgam;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SavingTipsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_saving_tips);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.saving_tips, menu);
		return true;
	}

}
