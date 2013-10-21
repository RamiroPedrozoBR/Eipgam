package com.example.eipgam.spending;

import java.util.List;

import com.example.eipgam.R;
import com.example.eipgam.adapters.ListAdapter;
import com.example.eipgam.helpers.ActivityBase;
import com.example.eipgam.helpers.Utils;
import com.example.eipgam.helpers.Utils.TypeDatePicker;
import com.example.eipgam.models.PayType;
import com.example.eipgam.models.Spending;
import com.example.eipgam.models.SpendingType;
import com.example.eipgam.services.PayTypeServices;
import com.example.eipgam.services.SpendingServices;
import com.example.eipgam.services.GoalTypeServices;
import com.example.eipgam.services.SpendingTypeServices;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.DatePicker;

public class EditSpendingActivity extends ActivityBase implements OnItemSelectedListener
{
	private SpendingServices serviceSpending;
	private SpendingTypeServices servicesSpendingType;
	private ListAdapter<SpendingType> adapterSpending;
	private ListAdapter<PayType> adapterPayType;
	private Spending currentSpending;
	private PayTypeServices payTypeServices;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_edit_spending);

		serviceSpending = new SpendingServices();
		servicesSpendingType = new SpendingTypeServices();
		payTypeServices = new PayTypeServices();
		currentSpending = (Spending)getIntent().getSerializableExtra("Spending");

		loadInterface(currentSpending);
	}


	public void onClickOk(View view) {

		currentSpending = load(currentSpending);

		currentSpending.setId(serviceSpending.saveOrUpdate(currentSpending));

		if(currentSpending.getId() > 0){	
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

	private void loadInterface(Spending entity) {

		loadSpendingTypeCollection();
		loadPayTypeCollection();
		
		if (entity == null)
		{
			refreshViblesControlBy(1);
			return;
		}

		editSetText(R.id.spending_edit_name, entity.getName());
		editSetDouble(R.id.spending_edit_value, entity.getValue());
		editSetInt(R.id.spending_edit_plots, entity.getPlots());
		datePickerSetDate(R.id.spending_date,entity.getDate());


		refreshViblesControlBy(entity.getPayType().getId());

		if(entity.getType() == null)
			spinnerSetSelected(R.id.spending_list_type, 0);
		else
			spinnerSetSelected(R.id.spending_list_type, entity.getType().getId());



		if(entity.getPayType() == null)
			spinnerSetSelected(R.id.spending_list_paytype,0);
		else
			spinnerSetSelected(R.id.spending_list_paytype, entity.getPayType().getId());


	}

	private Spending load(Spending entity) {

		if (entity == null)
			entity = new Spending();

		entity.setName(editGetText(R.id.spending_edit_name));
		entity.setValue(editGetDouble(R.id.spending_edit_value));
		entity.setPlots(editGetInt(R.id.spending_edit_plots));
		entity.setDate(datePickerGetDate(R.id.spending_date));
		entity.setPayType((PayType)spinnerGetSelected(R.id.spending_list_paytype));
		entity.setType((SpendingType)spinnerGetSelected(R.id.spending_list_type));

		return entity;
	}

	private void refreshViblesControlBy(int payTypeid) {
		// Paga - 1 /Parcelado -2/mensal-3/Anual -4/
		DatePicker datePicker = (DatePicker) findViewById (R.id.spending_date);

		String legendText="";
		String legendTextEnd=" ";
		
		if(payTypeid== 1)
		{
			viewSetVisibility(R.id.spending_date,true);
			Utils.changeTypeDatePicker(datePicker,TypeDatePicker.Show_Normal);
			viewSetVisibility(R.id.spending_edit_plots,false);
			
			  legendText = "Data ";
			  
		}

		if(payTypeid== 2)
		{
			viewSetVisibility(R.id.spending_edit_plots,true);
			Utils.changeTypeDatePicker(datePicker,TypeDatePicker.Show_Days);
			legendText = "Todo Dia ";
			legendTextEnd =" do mês.";
		}
		if(payTypeid== 3)
		{
			Utils.changeTypeDatePicker(datePicker,TypeDatePicker.Show_Days);
			viewSetVisibility(R.id.spending_edit_plots,false);
			legendText = "Todo Dia ";
			legendTextEnd =" do mês.";
		}
		if(payTypeid== 4)
		{
			Utils.changeTypeDatePicker(datePicker,TypeDatePicker.Show_Days_Month);
			viewSetVisibility(R.id.spending_edit_plots,false);
			
			
			legendText = "Todo Dia/Mês ";
			legendTextEnd =" do ano.";
		}

		textViewSetText(R.id.spending_edit_date_legend,legendText);
		textViewSetText(R.id.spending_edit_date_legendEnd,legendTextEnd);

	}

	private void loadSpendingTypeCollection() {

		List<SpendingType> list = servicesSpendingType.retrieveAll();

		if(Utils.isNullOrEmpty(list))
			return;

		adapterSpending =new ListAdapter<SpendingType>(this,list);

		spinnerSetAdapter(R.id.spending_list_type,adapterSpending,null);


	}

	private void loadPayTypeCollection() {


		List<PayType> list = payTypeServices.retrieveAll();

		adapterPayType =new ListAdapter<PayType>(this,list);

		spinnerSetAdapter(R.id.spending_list_paytype,adapterPayType,this);

	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
			long arg3) {

		PayType payType =  adapterPayType.getItem(position);
		refreshViblesControlBy(payType.getId());

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}


}