package com.example.foodmenu;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
 

public class SingleItemView extends Activity {
	// Declare Variables
	
	String name;
	String description;
	String price;
	String in_stock;
	String vegetarian;
	String category;
	String medium_image_url;
	static double  subtotal;
	double itemPrice;
	ImageLoader imageLoader = new ImageLoader(this);
	ListView orderList;
	orderListAdapter adapter;
	ArrayList<HashMap<String, String>> arraylist;
	
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from singleitemview.xml
		setContentView(R.layout.singleitemview);
 
		Intent i = getIntent();
		
		// Get the result of name
		name = i.getStringExtra("name");
		description = i.getStringExtra("description");	
		price = i.getStringExtra("price");
		in_stock = i.getStringExtra("in_stock");
		category = i.getStringExtra("category");
		vegetarian = i.getStringExtra("vegetarian");
		medium_image_url = i.getStringExtra("medium_image_url");
		
		// Locate the TextViews in singleitemview.xml
		TextView txtname = (TextView) findViewById(R.id.name);
		TextView txtdescription = (TextView) findViewById(R.id.description);
		TextView txtprice = (TextView) findViewById(R.id.price);
		TextView txtcategory = (TextView) findViewById(R.id.category);
		//TextView txtvegetarian = (TextView) findViewById(R.id.vegetarian);
		TextView txtin_stock = (TextView) findViewById(R.id.in_stock);
		
		// Locate the ImageView in singleitemview.xml
		ImageView img = (ImageView) findViewById(R.id.medium_image_url);
 
		// Set results to the TextViews
		
		txtname.setText(name);
		txtdescription.setText(description);
		txtprice.setText("Price : "+price);
		txtcategory.setText(category);
	//	txtvegetarian.setText(vegetarian);
		txtin_stock.setText("In Stock :"+in_stock);
 
		// Capture position and set results to the ImageView
		// Passes images URL into ImageLoader.class
		imageLoader.DisplayImage(medium_image_url, img);
	

	
	
Button add = (Button) findViewById(R.id.add);
	add.setOnClickListener(new Button.OnClickListener() {
	    public void onClick(View v) 
	    {
	    	arraylist = new ArrayList<HashMap<String, String>>();
	    	int i = Integer.parseInt(in_stock);
	    	if(i>0)
	    	{	
	    	itemPrice = Double.parseDouble(price);
	    	subtotal = subtotal + itemPrice;
	    	HashMap<String, String> maplist = new HashMap<String, String>();
			maplist.put("name", name );
			maplist.put("price", price);
			arraylist.add(maplist); 	    	
	    	}
	    	else{
	    		Toast.makeText(getBaseContext(), "Item Out of Stack", Toast.LENGTH_SHORT).show(); 
	    	    }    	
	    }
	    });
	
	Button ptp = (Button) findViewById(R.id.ptp);
	ptp.setOnClickListener(new Button.OnClickListener() {
	    public void onClick(View v) 
	    {
	    /*	finalOrder fo = new finalOrder();
	    	orderList = (ListView) findViewById(R.id.listview);
			// Pass the results into ListViewAdapter.java
			adapter = new orderListAdapter(fo, arraylist);
			// Set the adapter to the ListView
			orderList.setAdapter(adapter);	
			Intent i  = new Intent(getApplicationContext(), finalOrder.class);
		    startActivity(i);*/
	    }
	    });
	   
	
}	
}


