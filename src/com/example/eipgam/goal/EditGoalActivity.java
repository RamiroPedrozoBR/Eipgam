package com.example.eipgam.goal;

import java.util.List;

import com.example.eipgam.R;
import com.example.eipgam.R.id;
import com.example.eipgam.R.layout;
import com.example.eipgam.R.menu;
import com.example.eipgam.adapters.ListAdapter;
import com.example.eipgam.helpers.ActivityBase;
import com.example.eipgam.helpers.Utils;
import com.example.eipgam.helpers.Utils.TypeDatePicker;
import com.example.eipgam.models.Goal;
import com.example.eipgam.models.GoalType;
import com.example.eipgam.models.PayType;
import com.example.eipgam.models.Spending;
import com.example.eipgam.models.SpendingType;
import com.example.eipgam.services.GoalServices;
import com.example.eipgam.services.PayTypeServices;
import com.example.eipgam.services.SpendingServices;
import com.example.eipgam.services.GoalTypeServices;
import com.example.eipgam.services.SpendingTypeServices;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.AdapterView.OnItemSelectedListener;

public class EditGoalActivity extends ActivityBase implements OnItemSelectedListener
{
	private GoalServices serviceGoal;
	private GoalTypeServices servicesGoalType;
	private ListAdapter<GoalType> adapterGoal;
	private Goal currentGoal;
	private SpendingTypeServices spendingTypeServices;
	private ListAdapter<SpendingType> adapterSpending;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_edit_goal);

		serviceGoal = new GoalServices();
		servicesGoalType = new GoalTypeServices();
		spendingTypeServices = new SpendingTypeServices();

		currentGoal = (Goal)getIntent().getSerializableExtra("Goal");

		loadGoalType();
		loadSpendingTypes();
		loadInterface(currentGoal);

	}


	private void loadInterface(Goal entity) {
		if (entity == null)
		{
			refreshViblesControlBy(1);
			return;
		}

		editSetText(R.id.goal_edit_name, entity.getName());
		editSetText(R.id.goal_edit_tags, entity.getTags());
		editSetDouble(R.id.goal_edit_value, entity.getValue());
		editSetInt(R.id.goal_edit_plots, entity.getPlots());
		datePickerSetDate(R.id.goal_date_estimative,entity.getDate());
		chSetchecked(R.id.goal_check_alerts, entity.isEnableAlert());
		chSetchecked(R.id.goal_check_economize_alerts, entity.isEnableAlert());
		chSetchecked(R.id.goal_check_spending, entity.isEnableSpending());


		refreshViblesControlBy(entity.getType().getId());

		if(entity.getType() == null)
			spinnerSetSelected(R.id.goal_list_type, 0);
		else
			spinnerSetSelected(R.id.goal_list_type, entity.getType().getId());

		if(entity.getSpendingType() == null)
			spinnerSetSelected(R.id.goal_list_spendingtype, 0);
		else
			spinnerSetSelected(R.id.goal_list_spendingtype, entity.getSpendingType().getId());
	}
	private Goal load(Goal entity) {

		if (entity == null)
			entity = new Goal();

		entity.setName(editGetText(R.id.goal_edit_name));
		entity.setValue(editGetDouble(R.id.goal_edit_value));
		entity.setPlots(editGetInt(R.id.goal_edit_plots));
		entity.setDate(datePickerGetDate(R.id.goal_date_estimative));
		entity.setEnableSpending(chGetChecked(R.id.goal_check_spending));		
		entity.setTags(editGetText(R.id.goal_edit_tags));
		entity.setSpendingType((SpendingType)spinnerGetSelected(R.id.goal_list_spendingtype));
		entity.setType((GoalType)spinnerGetSelected(R.id.goal_list_type));

		if(entity.getType().getId() == 1)
			entity.setEnableAlert(chGetChecked(R.id.goal_check_alerts));
		else
			entity.setEnableAlert(chGetChecked(R.id.goal_check_economize_alerts));

		return entity;
	}

	private void loadGoalType() {

		List<GoalType> list = servicesGoalType.retrieveAll();

		if(Utils.isNullOrEmpty(list))
			return;

		adapterGoal =new ListAdapter<GoalType>(this,list);

		spinnerSetAdapter(R.id.goal_list_type,adapterGoal,this);
	}
	private void loadSpendingTypes() {

		List<SpendingType> list = spendingTypeServices.retrieveAll();

		if(Utils.isNullOrEmpty(list))
			return;

		adapterSpending =new ListAdapter<SpendingType>(this,list);

		spinnerSetAdapter(R.id.goal_list_spendingtype,adapterSpending,null);


	}

	public void onClickOk(View view) {

		currentGoal = load(currentGoal);

		currentGoal.setId(serviceGoal.saveOrUpdate(currentGoal));

		if(currentGoal.getId() > 0){	
			setResult(RESULT_OK);
			finish();
		}else{
			showWindows("error", "não foi possivel salvar este gasto");
		}
	}

	public void onClickCancel(View view) {
		setResult(RESULT_CANCELED);
		finish();
	}


	private void refreshViblesControlBy(int typeid) {

		// Compra - 1 /Economizar -2
		if(typeid == 1){
			viewSetVisibility(id.goal_linearlayout_pay, true);
			viewSetVisibility(id.goal_linearlayout_economize,false);
		}
		if(typeid == 2){
			viewSetVisibility(id.goal_linearlayout_pay, false);
			viewSetVisibility(id.goal_linearlayout_economize,true);
		}

	}


	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
			long arg3) {

		GoalType type =  adapterGoal.getItem(position);
		refreshViblesControlBy(type.getId());

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}