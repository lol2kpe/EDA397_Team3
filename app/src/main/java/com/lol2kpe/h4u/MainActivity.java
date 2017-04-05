package com.lol2kpe.h4u;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


import com.lol2kpe.h4u.markers.MarkerOptionFactory;

import java.util.Iterator;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lol2kpe.h4u.userlocation.UserLocationMonitor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  
	static final int PICK_FILTER_OPTIONS_REQUEST = 1;

	private List<Hospital> hospitals;
	private static String url = "http://lol2kpe.asuscomm.com/hospitals";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
    
    // TODO: Make into LAUNCHER ACTIVITY
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CountDownTimer(1000,1000){
            @Override
            public void onTick(long millisUntilFinished){}

            @Override
            public void onFinish(){
                //set the new Content of your activity
                //MainActivity.this.setContentView(R.layout.activity_main);
                Intent intent = new Intent(MainActivity.this, DrawerActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        }.start();
    
    // ------------------------------------
    
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//Sets the customized menu_main as the app bar for this activity
		Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_main);
		setSupportActionBar(toolbar);
    
        hospitals = new ArrayList<>();
		fetchData();
	}

	/**
	 * Method overrides the standard onCreateOptionsMenu. Makes sure the layout for the custom
	 * toolbar is used as the menu for the activity.
	 * @param menu
	 * @return
	 */
	@Override
	public boolean onCreateOptionsMenu (Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * Method is called when an menu item on the toolbar is selected (clicked on). Method receives
	 * the item clicked and uses the proper case. If no case could be found for the item,
	 * the super class is called to handle that situation.
	 * @param item
	 * @return
	 */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_action_filter:
            	Intent getFilterResults = new Intent(getApplicationContext(), FilterActivity.class);
				startActivityForResult(getFilterResults, PICK_FILTER_OPTIONS_REQUEST );
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Receives results from the FilterActivity.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Check which request MainActivity is responding to
		if (requestCode == PICK_FILTER_OPTIONS_REQUEST) {
			// Make sure the request was successful
			if (resultCode == RESULT_OK) {
                setMarkers();
			}
		}
	}

    /**
     * Filter the selections made in the FilterActivity.
     */
    public void setMarkers() {
        SharedPreferences filterPrefs = getSharedPreferences(
                getResources().getString(R.string.filter_preferences), MODE_PRIVATE);
        String type = filterPrefs.getString(
                getResources().getString(R.string.filter_preferences_type), "");

        if (type.equals("Hospitals")) {
			if(hospitals.isEmpty()) {
                Toast.makeText(getApplicationContext(), "No hospitals found",
                        Toast.LENGTH_SHORT).show();
            } else {
				// TODO: Clear out current markers
				Iterator<Hospital> iterator = hospitals.iterator();
				while(iterator.hasNext()) {
                    Hospital item = iterator.next();
                    Toast.makeText(getApplicationContext(), item.getName()
                            + " Lat: " + item.getLatitude()
                            + " Long: " + item.getLongitude(),
                            Toast.LENGTH_SHORT).show();
                    // TODO: Add marker
				}
				Toast.makeText(getApplicationContext(), "Showing all hospitals",
                        Toast.LENGTH_SHORT).show();
				
			}
        } else if (type.equals("Dentists")) {
			// TODO: Clear out current markers
            Toast.makeText(getApplicationContext(), "Could not find any dentists",
                    Toast.LENGTH_SHORT).show();
        } else {
            // TODO: Clear out current markers
            Iterator<Hospital> iterator = hospitals.iterator();
            while(iterator.hasNext()) {
                Hospital item = iterator.next();
                Toast.makeText(getApplicationContext(), item.getName()
                                + " Lat: " + item.getLatitude()
                                + " Long: " + item.getLongitude(),
                        Toast.LENGTH_SHORT).show();
                // TODO: Add marker
            }
            Toast.makeText(getApplicationContext(), "Showing all",
                    Toast.LENGTH_SHORT).show();
        }
    }

	// fetches data from server
	public void fetchData() {

		Hospital sahlgrenska = new Hospital("Sahlgrenska",5,"Emergency", 57.703830518, 11.93582959, 10,"00:00 - 24:00","Göteborg 41753", "075-8833865");
		Hospital lundby = new Hospital("Lundby",5,"Regular appointments", 57.707663836 , 11.90916303, 10,"08:00 - 22:00","Göteborg 41753", "075-8866465");

		hospitals.add(sahlgrenska);
		hospitals.add(lundby);

		//VolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(gsonRequest);
	}

	final GsonRequest gsonRequest = new GsonRequest(url, Hospital[].class, null, new Response.Listener<Hospital[]>() {

		@Override
		public void onResponse(Hospital[] hospitalsResponse) {
			hospitals = Arrays.asList(hospitalsResponse);
			Toast.makeText(MainActivity.this, "Hospitals refreshed", Toast.LENGTH_SHORT).show();
		}
	}, new Response.ErrorListener() {
		@Override
		public void onErrorResponse(VolleyError volleyError) {
			//if(volleyError != null) Log.e("MainActivity", volleyError.getMessage());
			Toast.makeText(MainActivity.this, "Database error", Toast.LENGTH_SHORT).show();
		}
	});
}
