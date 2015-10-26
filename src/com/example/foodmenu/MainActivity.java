package com.example.foodmenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
 
public class MainActivity extends Activity {
	// Declare Variables
	JSONObject jsonobject;
	JSONArray jsonarray;
	ListView listview;
	ListViewAdapter adapter;
	ProgressDialog mProgressDialog;
	ArrayList<HashMap<String, String>> arraylist;
    static String url = "http://cookaroo-test-jhmbjdamep.elasticbeanstalk.com/api/v1/food_items_preview/";
	String str;
	    static final String TAG_ID = "id";
	    static final String TAG_NAME = "name";
	    static final String TAG_DESCRIPTION = "description";
	    static final String TAG_VEGETARIAN = "VEGETARIAN";
	    static final String TAG_PRICE = "price";
	    static final String TAG_IN_STOCK = "in_stock";
	    static final String TAG_CATEGORY = "category";
	    static final String TAG_UPDATED_AT = "updated_at";
	    static final String TAG_THUMB_IMAGE_URL = "thumb_image_url";
	    static final String TAG_MEDIUM_IMAGE_URL = "medium_image_url";
		

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from listview items
		setContentView(R.layout.activity_main);
		
		// Execute DownloadJSON AsyncTask
		new DownloadJSON().execute();
	}
 
	// DownloadJSON AsyncTask
	private class DownloadJSON extends AsyncTask<Void, Void, Void> {
 
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Create a progressdialog
			mProgressDialog = new ProgressDialog(MainActivity.this);
			// Set progressdialog title
			mProgressDialog.setTitle("Menu List");
			// Set progressdialog message
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false);
			// Show progressdialog
			//mProgressDialog.show();
		}
 
		@Override
		protected Void doInBackground(Void... params) {
			// Create an array
			arraylist = new ArrayList<HashMap<String, String>>();
			
			
			try {
			
				
				ServiceHandler sh = new ServiceHandler();
				String str = sh.makeServiceCall(url, ServiceHandler.GET);
		        JSONArray jsonarray = new JSONArray(str);
				for (int i = 0; i < jsonarray.length(); i++) {
					HashMap<String, String> map = new HashMap<String, String>();
					jsonobject = jsonarray.getJSONObject(i);
					// Retrive JSON Objects
					map.put("id", jsonobject.getString("id"));
					map.put("name", jsonobject.getString("name"));
					map.put("description", jsonobject.getString("description"));
					map.put("thumb_image_url", jsonobject.getString("thumb_image_url"));
					map.put("vegetarian", jsonobject.getString("vegetarian"));
					map.put("price", jsonobject.getString("price"));
					map.put("in_stock", jsonobject.getString("in_stock"));
					map.put("category", jsonobject.getString("category"));
					map.put("updated_at", jsonobject.getString("updated_at"));
					map.put("medium_image_url", jsonobject.getString("medium_image_url"));
					
					// Set the JSON Objects into the array
					arraylist.add(map);
				}
			} catch (JSONException e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return null;
		}
 
		
		
		@Override
		protected void onPostExecute(Void args) {
			// Locate the listview in listview_main.xml
			listview = (ListView) findViewById(R.id.listview);
			// Pass the results into ListViewAdapter.java
			adapter = new ListViewAdapter(MainActivity.this, arraylist);
			// Set the adapter to the ListView
			listview.setAdapter(adapter);
			// Close the progressdialog
			mProgressDialog.dismiss();
		}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is
		// present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		
		return true;

	}
		
	
}