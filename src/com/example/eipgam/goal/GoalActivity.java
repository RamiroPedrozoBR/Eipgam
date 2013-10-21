package com.example.eipgam.goal;

import java.util.List;

import com.example.eipgam.R;
import com.example.eipgam.R.layout;
import com.example.eipgam.R.menu;
import com.example.eipgam.adapters.ListAdapter;
import com.example.eipgam.helpers.ActivityBase;
import com.example.eipgam.helpers.Utils;
import com.example.eipgam.models.Goal;
import com.example.eipgam.models.Spending;
import com.example.eipgam.services.GoalServices;
import com.example.eipgam.services.SpendingServices;
import com.example.eipgam.spending.EditSpendingActivity;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class GoalActivity extends ActivityBase implements OnItemClickListener,OnItemLongClickListener {

	private ListAdapter<Goal> adapterGoal;
	private GoalServices goalServices;

	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.view_goal);

		goalServices = new GoalServices();

		LoadGoal();
	}

	public void onClickNewGoal(View view) {

		Intent intent = new Intent(this,EditGoalActivity.class);
		startActivityForResult(intent ,R.layout.view_edit_goal);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		if(requestCode == R.layout.view_edit_goal)		
			if(resultCode == RESULT_OK)
			{
				LoadGoal();
			}
	}

	private void LoadGoal() {

		List<Goal> list = goalServices.retrieveAll();

		if(Utils.isNullOrEmpty(list)){return;}

		adapterGoal = new ListAdapter<Goal>(this,list);
		
		lisViewSetAdapter(R.id.goal_list_main,adapterGoal,true,true);
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position,long id) {
	
		Goal item = adapterGoal.getItem(position);

		Intent intent = new Intent(adapter.getContext(),EditGoalActivity.class);

		intent.putExtra("Goal",item);  

		startActivityForResult(intent,R.layout.view_edit_goal);

	}

	@Override
	public boolean onItemLongClick(  AdapterView<?> adapter, View view, final int position,final long id) {

		showWindows("Deletar","Deseja mesmo deletar esta meta?",new AlertDialog.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				goalServices.delete(id);

				adapterGoal.remove(position);

			}});

		return false;
	}


}
