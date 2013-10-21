package com.example.eipgam.spending;

import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.example.eipgam.R;
import com.example.eipgam.R.id;
import com.example.eipgam.R.layout;
import com.example.eipgam.adapters.ListAdapter;
import com.example.eipgam.helpers.ActivityBase;
import com.example.eipgam.helpers.Utils;
import com.example.eipgam.models.Spending;
import com.example.eipgam.services.SpendingServices;

public class SpendingActivity extends ActivityBase implements OnItemClickListener,OnItemLongClickListener{

	private SpendingServices servicesSpendings;
	private ListAdapter<Spending> adapterSpending;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.view_spending);

		servicesSpendings = new SpendingServices();

		LoadSpending();
	}

	public void onClickNewSpending(View view) {

		Intent intent = new Intent(this,EditSpendingActivity.class);
		startActivityForResult(intent ,R.layout.view_edit_spending);

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		if(requestCode == R.layout.view_edit_spending)		
			if(resultCode == RESULT_OK)
			{
				LoadSpending();
			}
	}

	private void LoadSpending() {

		List<Spending> list = servicesSpendings.retrieveAll();

		if(Utils.isNullOrEmpty(list)){return;}

		adapterSpending = new ListAdapter<Spending>(this,list);
		lisViewSetAdapter(R.id.spending_list_main,adapterSpending,true,true);
	}


	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position,long id) {
		ListAdapter<Spending> adt = (ListAdapter<Spending>)adapter.getAdapter();

		Spending spe = adt.getItem(position);

		Intent intent = new Intent(adapter.getContext(),EditSpendingActivity.class);

		intent.putExtra("Spending",spe);  

		startActivityForResult(intent ,R.layout.view_edit_spending);

	}

	@Override
	public boolean onItemLongClick( final AdapterView<?> adapter, View view, final int position,final long id) {

		showWindows("Deletar","Deseja mesmo deletar este gasto?",new AlertDialog.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				SpendingServices servicesSpendings = new SpendingServices();
				servicesSpendings.delete(id);

				ListAdapter<Spending> adt = (ListAdapter<Spending>)adapter.getAdapter();

				adt .remove(position);

			}});

		return false;
	}


}
