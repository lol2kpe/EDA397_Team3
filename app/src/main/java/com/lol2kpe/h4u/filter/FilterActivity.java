package com.lol2kpe.h4u.filter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.lol2kpe.h4u.MainActivity;
import com.lol2kpe.h4u.R;
import com.lol2kpe.h4u.data.model.Place;
import com.lol2kpe.h4u.data.model.Symptom;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Jonathan Granström
 * User: Jonathan "juntski" Granström
 * Date: 2017-03-26
 */
@SuppressWarnings("ConstantConditions")
public class FilterActivity extends AppCompatActivity {

    static EnumMap<KEY, Integer> filterSelections;
    static ArrayList<Place> returnList;
    Intent returnIntent;
    static Integer currentTab = 0;
    static Fragment currentFragment;

    private ViewPagerAdapter adapter;
    private ViewPager viewPager;

    enum KEY {
        TYPE, RATING, SYMPTOM
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        // Enable the up action to the toolbar (the "<-" arrow)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set up the buttons and their functionality
        Button cancelButton = (Button) findViewById(R.id.button_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnIntent = new Intent(FilterActivity.this, MainActivity.class);
                FilterActivity.this.setResult(Activity.RESULT_CANCELED);
                FilterActivity.this.finish();
            }
        });
        Button setButton = (Button) findViewById(R.id.button_set);
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentFragment = adapter.getItem(currentTab);
                if (currentFragment instanceof PlaceFragment) {
                    ((PlaceFragment) currentFragment).storeFilterValues();
                    ((PlaceFragment) currentFragment).filter();
                    FilterActivity.this.returnData();
                } else if (currentFragment instanceof SymptomFragment) {
                    ((SymptomFragment) currentFragment).storeFilterValues();
                    ((SymptomFragment) currentFragment).filter();
                    FilterActivity.this.returnData();
                }
            }
        });
        viewPager = (ViewPager) findViewById(R.id.pager);
        setupViewPager(viewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentTab = tab.getPosition();
                viewPager.setCurrentItem(currentTab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        // If the app/activity is started for the first time, create a HashMap to store values
        if (filterSelections == null) {
            filterSelections = new EnumMap<>(KEY.class);
            // Set the filter options to their default values
            setDefaultFilterValues();
        }
        // Get the list of Place objects from MainActivity
        returnList = getIntentData();
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PlaceFragment(), getResources().getString(R.string.tab_place));
        adapter.addFragment(new SymptomFragment(), getResources().getString(R.string.tab_symptom));
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(currentTab);
    }

    /**
     * The method overrides the standard onCreateOptionsMenu. The method makes sure
     * the custom XML-file and its contents and layout is added and displayed in the menu
     * for the FilterActivity. The method then calls the the original onCreateOptionsMenu
     * to add the standard menu items for the FilterActivity (e.g., activity title).
     *
     * @param menu The Menu object for the FilterActivity.
     * @return The original onCreateOptionsMenu method, which adds default menu items to
     * the FilterActivity menu.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Used to instantiate XML-files in to Menu object
        MenuInflater inflater = getMenuInflater();
        // Inflates a layout from an XML resource file in to the specified Menu object
        inflater.inflate(R.menu.menu_filter, menu);
        // Call the original method in order to add default items to the menu (e.g., activity title)
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * The method overrides the original onOptionsItemSelected in order to specify what
     * will happen when the additional content added to the menu is interacted with.
     * If the interacted item isn't part of the additionally added menu content,
     * the original onOptionsItemSelected method is called to handle that event.
     *
     * @param item The menu item in the FilterActivity menu which has been interacted with.
     * @return The original onOptionsItemSelected method, which handles interaction with
     * default MenuItems.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_action_clear_filter:
                resetFilter();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void resetFilter() {
        setDefaultFilterValues();
        for (Fragment fragment : adapter.getItems()) {
            if (fragment instanceof PlaceFragment) {
                ((PlaceFragment) fragment).setFilterSelections();
            } else if (fragment instanceof SymptomFragment) {
                ((SymptomFragment) fragment).setFilterSelections();
            }
        }
    }

    /**
     * The method controls the received ArrayList of Place objects sent from the MainActivity.
     * The method first controls if any data was sent from the MainActivity. If no data was sent,
     * a NullPointerException is thrown, as the FilterActivty can't work with null pointers. It
     * then checks if the data has the correct name, otherwise the method can't find the ArrayList
     * of Place objects to work with. It also checks if the received list is empty. If any of these
     * other tests fails, an empty return list is created. If all tests pass, the list of objects
     * is retrieved and stored in the FilterActivity.
     *
     * @return An empty or filled ArrayList of Place objects.
     */
    @SuppressWarnings({"unchecked"})
    private ArrayList<Place> getIntentData() {
        // Get the intent that started the FilterActivity
        Intent intent = getIntent();
        // Get a map of extended data from the intent
        Bundle extras = intent.getExtras();
        // Check if the data from MainActivity is null
        if (extras == null) {
            Log.e("NullIntentData", "The intent data received in FilterActivity is null");
            throw new NullPointerException();
        }
        // Check if the name of the data is the same as the expected name
        else if (!extras.containsKey(getResources().getString(R.string.object_data))) {
            Set<String> keys = extras.keySet();
            StringBuilder sb = new StringBuilder();

            for (String key : keys) {
                sb.append(key);
                sb.append(" ");
            }
            Log.e("IntentDataKeyMissing", "Could not find the needed intent data for the" +
                    " FilterActivity. Expected data name: " +
                    getResources().getString(R.string.object_data) +
                    ", Found data names: " + sb.toString());
            return new ArrayList<>();
        }
        // Check if the data from MainActivity is empty
        else if (extras.isEmpty() ||
                ((ArrayList<Place>) getIntent().getSerializableExtra(getResources()
                        .getString(R.string.object_data))).isEmpty()) {
            Log.w("EmptyIntentData", "The intent data received in FilterActivity is empty. "
                    + "Empty bundle: " + extras.isEmpty() + " Empty data: "
                    + ((ArrayList<Place>) getIntent().getSerializableExtra(getResources()
                    .getString(R.string.object_data))).isEmpty());
            return new ArrayList<>();
        } else {
            return (ArrayList<Place>) intent
                    .getSerializableExtra(getResources().getString(R.string.object_data));
        }
    }

    /**
     * Sets all the selections of the SpinnerObjects to their default values.
     */
    void setDefaultFilterValues() {
        for (KEY key : KEY.values()) {
            filterSelections.put(key, 0);
        }
        Log.i("INFO", filterSelections.toString());
    }

    /**
     * Returns either an empty or filtered ArrayList of Place objects to the MainActivity.
     */
    private void returnData() {
        returnIntent = new Intent(this, MainActivity.class);
        returnIntent.putExtra(getResources().getString(R.string.filtered_data), returnList);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
