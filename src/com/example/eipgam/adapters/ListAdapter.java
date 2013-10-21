package com.example.eipgam.adapters;

import java.util.List;

import com.example.eipgam.helpers.data.EntityBase;
import com.example.eipgam.helpers.data.IRow;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class ListAdapter<T extends IRow> extends BaseAdapter implements SpinnerAdapter {

	private List<T> allLists;
	private Context context;

	public ListAdapter(Context context,List<T> allLists){
		this.allLists = allLists;
		this.context = context;
	}

	public int getPositionBy(int id) {

		for(T item : allLists)	
		{
			if(item.getId() == id)
				return allLists.indexOf(item);
		}

		return -1;
	}

	@Override
	public int getCount() {

		return allLists.size();
	}

	@Override
	public T getItem(int position) {
		return allLists.get(position);
	}

	@Override
	public long getItemId(int position) {
		IRow base = getItem(position);

		if( base== null)
			return 0;

		return base.getId();
	}

	public void remove(int position){
		remove(getItem(position));
	}
	public void remove(Object item){
		
		allLists.remove(item);
		
		notifyDataSetChanged();
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		return allLists.get(position).getRow(context);

	}

}
