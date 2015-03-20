package com.xiaoqian.coffee;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.xiaoqian.coffee.adater.CoffeeListViewAdapter;
import com.xiaoqian.coffee.app.AppController;
import com.xiaoqian.coffee.model.Coffee;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.xiaoqian.coffee.R;

public class MainActivity extends Activity {
	// Log tag
	private static final String TAG = MainActivity.class.getSimpleName();

	// Movies json url
	//private static final String url = "http://api.androidhive.info/json/movies.json";
    private static final String url = "https://coffeeapi.percolate.com/api/coffee/?api_key=WuVbkuUsCXHPx3hsQzus4SE";

	private ProgressDialog pDialog;
	//private List<Movie> movieList = new ArrayList<Movie>();
    private List<Coffee> coffeeList = new ArrayList<Coffee>();
	private ListView listView;
	private CoffeeListViewAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.list);
		//adapter = new CoffeeListViewAdapter(this, movieList);
        adapter = new CoffeeListViewAdapter(this, coffeeList);
		listView.setAdapter(adapter);

		pDialog = new ProgressDialog(this);
		// Showing progress dialog before making http request
		pDialog.setMessage("Loading...");
		pDialog.show();

		// changing action bar color
        /*
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#1b1b1b")));
*/
        ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#F16421")));
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        LayoutInflater inflater = getLayoutInflater();
        View layoutView = inflater.inflate(R.layout.custom_action_bar, null);
        ImageView iconInLayout = (ImageView)layoutView.findViewById(R.id.imageView);
        iconInLayout.setColorFilter(Color.parseColor("#FFFFFF"));
        iconInLayout.setImageResource(R.drawable.drip);
        actionBar.setCustomView(layoutView);


		// Creating volley request obj
		JsonArrayRequest movieReq = new JsonArrayRequest(url,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG, response.toString());
						hidePDialog();

						// Parsing json
						for (int i = 0; i < response.length(); i++) {
							try {

								JSONObject obj = response.getJSONObject(i);

                                Coffee coffee = new Coffee();
                                coffee.setCoffeeName(obj.getString("name"));
                                coffee.setCoffeeDesc(obj.getString("desc"));
                                coffee.setImageUrl(obj.getString("image_url"));

                                coffeeList.add(coffee);
							} catch (JSONException e) {
								e.printStackTrace();
							}

						}

						// notifying list adapter about data changes
						// so that it renders the list view with updated data
						adapter.notifyDataSetChanged();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						hidePDialog();

					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(movieReq);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		hidePDialog();
	}

	private void hidePDialog() {
		if (pDialog != null) {
			pDialog.dismiss();
			pDialog = null;
		}
	}

}
