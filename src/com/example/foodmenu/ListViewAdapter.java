package com.example.foodmenu;

import java.util.ArrayList;
import java.util.HashMap;
 
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class ListViewAdapter extends BaseAdapter {
 
	// Declare Variables
	Context context;
	LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data;
	ImageLoader imageLoader;
	HashMap<String, String> resultp = new HashMap<String, String>();
 
	public ListViewAdapter(Context context,
			ArrayList<HashMap<String, String>> arraylist) {
		this.context = context;
		data = arraylist;
		imageLoader = new ImageLoader(context);
	}
 
	@Override
	public int getCount() {
		return data.size();
	}
 
	@Override
	public Object getItem(int position) {
		return null;
	}
 
	@Override 
	public long getItemId(int position) {
		return 0;
	}
 
	public View getView(final int position, View convertView, ViewGroup parent) {
		// Declare Variables
		
		TextView name;
		TextView description;
		ImageView thumb_image_url;
 
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View itemView = inflater.inflate(R.layout.lisiview_item, parent, false);
		// Get the position
		resultp = data.get(position);
 
		// Locate the TextViews in listview_item.xml
		
		name = (TextView) itemView.findViewById(R.id.name);
		description = (TextView) itemView.findViewById(R.id.description);
		thumb_image_url = (ImageView) itemView.findViewById(R.id.thumb_image_url);
 
		// Capture position and set results to the TextViews	
		name.setText(resultp.get(MainActivity.TAG_NAME));
		description.setText(resultp.get(MainActivity.TAG_DESCRIPTION));
		// Capture position and set results to the ImageView
		// Passes images URL into ImageLoader.class
		imageLoader.DisplayImage(resultp.get(MainActivity.TAG_THUMB_IMAGE_URL), thumb_image_url);
		// Capture ListView item click
		itemView.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				// Get the position
     			resultp = data.get(position);
				Intent intent = new Intent(context, SingleItemView.class);
				intent.putExtra("name", resultp.get(MainActivity.TAG_NAME));
				intent.putExtra("description",resultp.get(MainActivity.TAG_DESCRIPTION));
				intent.putExtra("price", resultp.get(MainActivity.TAG_PRICE));
				intent.putExtra("in_stock", resultp.get(MainActivity.TAG_IN_STOCK));
				intent.putExtra("medium_image_url", resultp.get(MainActivity.TAG_MEDIUM_IMAGE_URL));
				intent.putExtra("category", resultp.get(MainActivity.TAG_CATEGORY));
				intent.putExtra("vagetarian", resultp.get(MainActivity.TAG_VEGETARIAN));
				// Start SingleItemView Class
				context.startActivity(intent);
							
				
			}
		});
		return itemView;
	}
}


