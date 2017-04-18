package com.lol2kpe.h4u;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.lol2kpe.h4u.data.model.Hospital;
import com.lol2kpe.h4u.data.model.Pharmacy;
import com.lol2kpe.h4u.data.model.Place;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Jonathan Granström
 * User: Jonathan "juntski" Granström
 * Date: 2017-04-02
 */

public class FilterActivity extends AppCompatActivity {

    private final String TYPE = "type";
    private final String OPENING_HOUR = "openingHour";
    private final String RATING = "rating";

    private final int DEFAULT_TYPE_VALUE = 0;
    private final int DEFAULT_OPENING_HOURS_VALUE = 0;
    private final int DEFAULT_RATING_VALUE = 1;

    public static HashMap <String, Integer> filterSelections;
    private Spinner spinnerType, spinnerOpeningHours, spinnerRating;

    Intent returnIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        // Enable the up action to the toolbar (the "<-" arrow)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get the buttons and set their onClickListeners and functionality
        Button cancelButton = (Button)findViewById(R.id.button_cancel);
        cancelButton.setOnClickListener(view -> {
            returnIntent = new Intent(this, MainActivity.class);
            setResult(Activity.RESULT_CANCELED);
            finish();
        });
        Button setButton = (Button)findViewById(R.id.button_set);
        setButton.setOnClickListener(view -> {
            storeFilterValues();
            filter();
        });

        // Get the spinner objects and set their
        spinnerType = (Spinner)findViewById(R.id.spinner_type);
        spinnerOpeningHours = (Spinner)findViewById(R.id.spinner_openinghours);
        spinnerRating = (Spinner)findViewById(R.id.spinner_rating);

        // Create Adapter objects with data items for the Spinner objects
        ArrayAdapter<CharSequence> adapterType = ArrayAdapter.createFromResource(this,
                R.array.activity_filter_type_options, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterOpeningHours = ArrayAdapter.createFromResource(this,
                R.array.activity_filter_openinghours_options, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterRating = ArrayAdapter.createFromResource(this,
                R.array.activity_filter_rating_options, android.R.layout.simple_spinner_item);

        // Set the View for the items in the data set in the Adapter objects
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterOpeningHours.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterRating.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the Adapter objects to their respective Spinner objects
        spinnerType.setAdapter(adapterType);
        spinnerOpeningHours.setAdapter(adapterOpeningHours);
        spinnerRating.setAdapter(adapterRating);

        // If the app/activity is started for the first time, create a HashMap to store values
        if(filterSelections == null) {
            filterSelections = new HashMap<>();
            // Set the filter options to their default values
            setDefaultFilterValues();
            storeFilterValues();
        }
        // Otherwise, set the filter options to the values stored in the already existing HashMap
        else {
            setFilterValues();
        }
    }

    /**
     * The method overrides the standard onCreateOptionsMenu. The method makes sure
     * the custom XML-file and its contents and layout is added and displayed in the menu
     * for the FilterActivity. The method then calls the the original onCreateOptionsMenu
     * to add the standard menu items for the FilterActivity (e.g., activity title).
     *
     * @param menu  The Menu object for the FilterActivity
     * @return      The original onCreateOptionsMenu method, which adds default menu items to
     *              the FilterActivity menu.
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
     * @param item  The menu item in the FilterActivity menu which has been interacted with
     * @return      The original onOptionsItemSelected method, which handles interaction with
     *              default MenuItems.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_action_clear_filter:
                setDefaultFilterValues();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /**
     * The method sets all the selections of the SpinnerObjects to their default values.
     */
    private void setDefaultFilterValues() {
        spinnerType.setSelection(DEFAULT_TYPE_VALUE);
        spinnerOpeningHours.setSelection(DEFAULT_OPENING_HOURS_VALUE);
        spinnerRating.setSelection(DEFAULT_RATING_VALUE);
    }

    /**
     * The method overwrites all values in the existing HashMap with their respective
     * value currently selected/displayed by the SpinnerObjects.
     */
    private void storeFilterValues() {
        filterSelections.put(
                TYPE, spinnerType.getSelectedItemPosition());
        filterSelections.put(
                OPENING_HOUR, spinnerOpeningHours.getSelectedItemPosition());
        filterSelections.put(
                RATING, spinnerRating.getSelectedItemPosition());
    }

    /**
     * The method sets all the SpinnerObjects selections to their respective value
     * stored in the HashMap.
     */
    private void setFilterValues() {
        spinnerType.setSelection(filterSelections.get(TYPE));
        spinnerOpeningHours.setSelection(filterSelections.get(OPENING_HOUR));
        spinnerRating.setSelection(filterSelections.get(RATING));
    }

    private void filter() {
        ArrayList<Place> returnList = (ArrayList<Place>)getIntent().getSerializableExtra("objects");

        Iterator<Place> iterator = returnList.iterator();

        while(iterator.hasNext()) {
            Place item = iterator.next();
            if(checkType(filterSelections.get(TYPE), item))
                returnList.add(item);
        }

        iterator = returnList.iterator();
        while(iterator.hasNext()) {
            Place item = iterator.next();
            if(checkRating(filterSelections.get(RATING), item))
                iterator.remove();
        }

        returnIntent = new Intent(this, MainActivity.class);
        if(!returnList.isEmpty()) {
            returnIntent.putExtra("result", returnList);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        } else {
            setResult(Activity.RESULT_CANCELED);
            finish();
        }
    }

    private boolean checkType(int pos, Place p) {
        switch(pos) {
            case 0:
                return true;
            case 1:
                return p instanceof Hospital;
            case 2:
                return p instanceof  Pharmacy;
            default:
                return false;
        }
    }

    private boolean checkRating(int pos, Place p) {
        switch(pos) {
            case 0:
                return p.getRating() >= 1;
            case 1:
                return p.getRating() >= 2;
            case 2:
                return p.getRating() >= 3;
            case 3:
                return p.getRating() >= 4;
            case 4:
                return p.getRating() == 5;
            default:
                return false;
        }
    }

    /*
    private void filter() {
        // TODO: Get an ACTUAL list of objects to filter
        List<Place> objects = new ArrayList<>();
        int typePos = filterSelections.get(TYPE);

        List<Place> result = objects.stream()
                .filter(p -> checkType(typePos, p))
                .filter().collect(Collectors.toList());

        // TODO: Add call to method in MainActivity
    }

    private boolean checkType(int pos, Place p) {
        switch(pos) {
            case 0:
                return true;
            case 1:
                return p instanceof Hospital;
            case 2:
                return p instanceof Pharmacy;
            default:
                return false;
        }
    }
    */
}
