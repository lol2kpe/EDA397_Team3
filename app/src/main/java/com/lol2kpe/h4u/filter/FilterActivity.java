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
import android.widget.Button;

import com.lol2kpe.h4u.MainActivity;
import com.lol2kpe.h4u.R;
import com.lol2kpe.h4u.data.model.Place;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

@SuppressWarnings("ConstantConditions")
public class FilterActivity extends AppCompatActivity {

    final static String TYPE = "type";
    final static String OPENING_HOUR = "openingHour";
    final static String RATING = "rating";
    final static String SYMPTOM = "symptom";
    public static HashMap<String, Integer> filterSelections;
    static ArrayList<Place> returnList;
    Integer currentTab = 0;
    Intent returnIntent;
    Fragment currentFragment;
    PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        // Enable the up action to the toolbar (the "<-" arrow)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Add tabs to the FilterActivity
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Place"));
        tabLayout.addTab(tabLayout.newTab().setText("Symptom"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                currentTab = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // Get the buttons and set their onClickListeners and functionality
        Button cancelButton = (Button) findViewById(R.id.button_cancel);
        cancelButton.setOnClickListener(view -> {
            returnIntent = new Intent(this, MainActivity.class);
            setResult(Activity.RESULT_CANCELED);
            finish();
        });
        Button setButton = (Button) findViewById(R.id.button_set);
        setButton.setOnClickListener(view -> {
            switch (currentTab) {
                case 0:
                    currentFragment = adapter.getItem(currentTab);
                    PlaceFragment placeFragment = (PlaceFragment) currentFragment;
                    if (placeFragment != null) {
                        placeFragment.storeFilterValues();
                        placeFragment.filter();
                        returnData();
                    }
                    break;
                case 1:
                    currentFragment = adapter.getItem(currentTab);
                    SymptomFragment symptomFragment = (SymptomFragment) currentFragment;
                    if (symptomFragment != null) {
                        symptomFragment.storeFilterValues();
                        symptomFragment.filter();
                        returnData();
                    }
                    break;
                default:
                    break;
            }
        });

        // If the app/activity is started for the first time, create a HashMap to store values
        if (filterSelections == null) {
            filterSelections = new HashMap<>();
            // Set the filter options to their default values
            setDefaultFilterValues();
        }

        // Get the list of Place objects from MainActivity
        returnList = getIntentData();

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
                switch (currentTab) {
                    case 0:
                        currentFragment = adapter.getItem(currentTab);
                        PlaceFragment placeFragment = (PlaceFragment) currentFragment;
                        if (placeFragment != null) {
                            setDefaultFilterValues();
                            placeFragment.setFilterSelections();
                        }
                        break;
                    case 1:
                        currentFragment = adapter.getItem(currentTab);
                        SymptomFragment symptomFragment = (SymptomFragment) currentFragment;
                        if (symptomFragment != null) {
                            setDefaultFilterValues();
                            symptomFragment.setFilterSelections();
                        }
                    default:
                        break;
                }
            default:
                return super.onOptionsItemSelected(item);
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
     * The method sets all the selections of the SpinnerObjects to their default values.
     */
    void setDefaultFilterValues() {
        filterSelections.put(TYPE, 0);
        filterSelections.put(OPENING_HOUR, 0);
        filterSelections.put(RATING, 0);
        filterSelections.put(SYMPTOM, 0);
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
