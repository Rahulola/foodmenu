package com.example.foodmenu;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class orderListAdapter extends BaseAdapter{

	Context context;
	LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data;
	HashMap<String, String> result = new HashMap<String, String>();
 

	public orderListAdapter(Context context,
			ArrayList<HashMap<String, String>> arraylist) {
		this.context = context;
		data = arraylist;
		
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView itemname;
		TextView itemprice;
		
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View itemView = inflater.inflate(R.layout.orderlistitem, parent, false);
		itemname = (TextView) itemView.findViewById(R.id.itemprice);
		itemprice = (TextView) itemView.findViewById(R.id.itemname);
		
		result= data.get(position);
		
		itemname.setText(result.get("name"));
		itemprice.setText(result.get("price"));
			
		return itemView;
	}

}
