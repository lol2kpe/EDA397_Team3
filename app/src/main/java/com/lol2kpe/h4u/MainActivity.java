package com.lol2kpe.h4u;

import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.lol2kpe.h4u.data.model.Hospital;
import com.lol2kpe.h4u.data.model.Place;
import com.lol2kpe.h4u.util.markers.MarkerOptionFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    static final int PICK_FILTER_OPTIONS_REQUEST = 1;
    private static String url = "http://lol2kpe.asuscomm.com:3001/hospitals.json";
    Location location;
    Location locationMaps;
    LatLng myLocation;
    FloatingActionButton FAB;
    private GoogleMap mMap;
    private List<Hospital> hospitals;
    private List<Place> places;
    final GsonRequest gsonRequest = new GsonRequest(url, Place[].class, null, new Response.Listener<Place[]>() {

        @Override
        public void onResponse(Place[] placesResponse) {
            places = Arrays.asList(placesResponse);
            Toast.makeText(MainActivity.this, "Places are refreshed", Toast.LENGTH_SHORT).show();
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            if(volleyError != null) Log.e("MainActivity", volleyError.getMessage());
            Toast.makeText(MainActivity.this, "Database error", Toast.LENGTH_SHORT).show();
        }
    });
    private View.OnClickListener FABonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.filter:
                    Intent intent = new Intent(MainActivity.this, FilterActivity.class);
                    startActivityForResult(intent, PICK_FILTER_OPTIONS_REQUEST);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.home);

        FAB = (FloatingActionButton) findViewById(R.id.filter);
        FAB.setOnClickListener(FABonClickListener);

        hospitals = new ArrayList<>();
        fetchData();

        /********** MAP **********/

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);

        // Location of the user
        /*UserLocationMonitor monitor = UserLocationMonitor.getMonitor(this.getApplicationContext());
        location = monitor.getLocation();*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {

        } else if (id == R.id.med_reminders) {

        } else if (id == R.id.settings) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
    }

    public void addMapMarker(MarkerOptions marker) {
        LatLng destination = marker.getPosition();
        mMap.addMarker(marker.position(destination).title(marker.getTitle()).icon(marker.getIcon()));
    }

    public void removeAllMarkers() {
        mMap.clear();
    }

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
            if (hospitals.isEmpty()) {
                Toast.makeText(getApplicationContext(), "No hospitals found",
                        Toast.LENGTH_SHORT).show();
            } else {
                removeAllMarkers();
                Iterator<Hospital> iterator = hospitals.iterator();
                while (iterator.hasNext()) {
                    Hospital item = iterator.next();
                    Toast.makeText(getApplicationContext(), item.getName()
                                    + " Lat: " + item.getLatitude()
                                    + " Long: " + item.getLongitude(),
                            Toast.LENGTH_SHORT).show();
                    addMapMarker(MarkerOptionFactory.getMarkerOptions(item));
                }
                Toast.makeText(getApplicationContext(), "Showing all hospitals",
                        Toast.LENGTH_SHORT).show();

            }
        } else if (type.equals("Dentists")) {
            removeAllMarkers();
            Toast.makeText(getApplicationContext(), "Could not find any dentists",
                    Toast.LENGTH_SHORT).show();
        } else {
            removeAllMarkers();
            Iterator<Hospital> iterator = hospitals.iterator();
            while (iterator.hasNext()) {
                Hospital item = iterator.next();
                Toast.makeText(getApplicationContext(), item.getName()
                                + " Lat: " + item.getLatitude()
                                + " Long: " + item.getLongitude(),
                        Toast.LENGTH_SHORT).show();
                addMapMarker(MarkerOptionFactory.getMarkerOptions(item));
            }
            Toast.makeText(getApplicationContext(), "Showing all",
                    Toast.LENGTH_SHORT).show();
        }
    }

    // fetches data from server
    public void fetchData() {

        Hospital sahlgrenska = new Hospital()
                .setName("Sahlgrenska")
                .setId(10)
                .setHospitalType("Emergency")
                .setLatitude(57.703830518)
                .setLongitude(11.93582959);
        Hospital lundby = new Hospital()
                .setName("Lundby")
                .setId(10)
                .setHospitalType("Regular appointments")
                .setLatitude(57.707663836)
                .setLongitude(11.90916303);
        hospitals.add(sahlgrenska);
        hospitals.add(lundby);

        // uncomment this, and database should work
       // VolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(gsonRequest);
    }
}
