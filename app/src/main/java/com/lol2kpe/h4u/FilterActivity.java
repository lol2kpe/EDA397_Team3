package com.lol2kpe.h4u;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import java.util.Set;

/**
 * Created by Jonathan Granström
 * User: Jonathan "juntski" Granström
 * Date: 2017-04-02
 */

public class FilterActivity extends AppCompatActivity {

    private final String TYPE = "type";
    private final String OPENING_HOUR = "openingHour";
    private final String RATING = "rating";
	private final String INJURYTYPE = "injuryType";

    private final int DEFAULT_TYPE_VALUE = 0;
    private final int DEFAULT_OPENING_HOURS_VALUE = 0;
    private final int DEFAULT_RATING_VALUE = 1;
	private final int DEFAULT_INJURYTYPE_VALUE = 0;

    public static HashMap <String, Integer> filterSelections;
    private Spinner spinnerType, spinnerOpeningHours, spinnerRating, spinnerInjuryType;

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
	    spinnerInjuryType = (Spinner)findViewById(R.id.spinner_injurytype);

        // Create Adapter objects with data items for the Spinner objects
        ArrayAdapter<CharSequence> adapterType = ArrayAdapter.createFromResource(this,
                R.array.activity_filter_type_options, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterOpeningHours = ArrayAdapter.createFromResource(this,
                R.array.activity_filter_openinghours_options, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterRating = ArrayAdapter.createFromResource(this,
                R.array.activity_filter_rating_options, android.R.layout.simple_spinner_item);
	    ArrayAdapter<CharSequence> adapterInjuryType = ArrayAdapter.createFromResource(this,
			    R.array.activity_filter_injurytype_options, android.R.layout.simple_spinner_item);

        // Set the View for the items in the data set in the Adapter objects
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterOpeningHours.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterRating.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    adapterInjuryType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the Adapter objects to their respective Spinner objects
        spinnerType.setAdapter(adapterType);
        spinnerOpeningHours.setAdapter(adapterOpeningHours);
        spinnerRating.setAdapter(adapterRating);
	    spinnerInjuryType.setAdapter(adapterInjuryType);

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
     * @param menu  The Menu object for the FilterActivity.
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
     * @param item  The menu item in the FilterActivity menu which has been interacted with.
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
	    spinnerInjuryType.setSelection(DEFAULT_INJURYTYPE_VALUE);
    }

    /**
     * The method overwrites all values in the existing HashMap with their respective
     * value currently selected/displayed by the SpinnerObjects.
     */
    private void storeFilterValues() {
        filterSelections.put(TYPE, spinnerType.getSelectedItemPosition());
        filterSelections.put(OPENING_HOUR, spinnerOpeningHours.getSelectedItemPosition());
        filterSelections.put(RATING, spinnerRating.getSelectedItemPosition());
	    filterSelections.put(INJURYTYPE, spinnerInjuryType.getSelectedItemPosition());
    }

    /**
     * The method sets all the SpinnerObjects selections to their respective value
     * stored in the HashMap.
     */
    private void setFilterValues() {
        spinnerType.setSelection(filterSelections.get(TYPE));
        spinnerOpeningHours.setSelection(filterSelections.get(OPENING_HOUR));
        spinnerRating.setSelection(filterSelections.get(RATING));
	    spinnerInjuryType.setSelection(filterSelections.get(INJURYTYPE));
    }

    /**
     * The method retrives the ArrayList of Place-objects from the MainActivity Intent's extra data.
     * Iterator loops for filter alternatives removes invalid objects. The method sends the
     * filtered return list of objects to the returnData method.
     */
    @SuppressWarnings({"unchecked"})
    private void filter() {
        // Check the received data from MainActivity
        checkIntentData();

        Intent intent = getIntent();
        ArrayList<Place> returnList = (ArrayList<Place>) intent
                .getSerializableExtra(getResources().getString(R.string.object_data));

        // Type filter
        Iterator<Place> iterator = returnList.iterator();
        while (iterator.hasNext()) {
            Place item = iterator.next();
            if (!checkType(filterSelections.get(TYPE), item))
                iterator.remove();
        }

        // Rating filter
        iterator = returnList.iterator();
        while (iterator.hasNext()) {
            Place item = iterator.next();
            if (!checkRating(filterSelections.get(RATING), item))
                iterator.remove();
        }
        returnData(returnList);

	    // Injury type filter
	    iterator = returnList.iterator();
	    while (iterator.hasNext()) {
		    Place item = iterator.next();
		    if (!checkRating(filterSelections.get(INJURYTYPE), item))
			    iterator.remove();
	    }
	    returnData(returnList);
    }

    @SuppressWarnings({"unchecked"})
    private void checkIntentData() {
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
        if(!extras.containsKey(getResources().getString(R.string.object_data))) {
            Set<String> keys = extras.keySet();
            StringBuilder sb = new StringBuilder();

            for( String key : keys) {
                sb.append(key);
                sb.append(" ");
            }
            Log.e("IntentDataKeyMissing", "Could not find the needed intent data for the" +
                    " FilterActivity. Expected data name: " +
                    getResources().getString(R.string.object_data) +
                    ", Found data names: " + sb.toString());
            returnData(new ArrayList<>());
        }
        // Check if the data from MainActivity is empty
        if(extras.isEmpty() ||
                ((ArrayList<Place>)getIntent().getSerializableExtra(getResources()
                        .getString(R.string.object_data))).isEmpty()) {
            Log.w("EmptyIntentData", "The intent data received in FilterActivity is empty. "
                    + "Empty bundle: " + extras.isEmpty()
                    + " Empty data: " + ((ArrayList<Place>)getIntent().getSerializableExtra(getResources()
                    .getString(R.string.object_data))).isEmpty());
            returnData(new ArrayList<>());
        }
    }

    /**
     * The method takes the selected item position of the "Type" spinner and checks
     * the class of the selected position against the class of the Place object.
     * The method returns true if the class of the Place object is equal to the class
     * of the selected rating class of the "Type" spinner.
     *
     * @param pos   The current item position of the "Type" spinner.
     * @param p     The current Place object.
     * @return      True if the class type of the Place object is equal the current type class
     *              of the selected spinner item, else returns false.
     */
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

    /**
     * The method takes the selected item position of the "Rating" spinner and checks
     * the rating value of the selected position against the value of the Place object.
     * The method returns true if the rating value of the Place object is equal or higher
     * to the rating value of the selected rating value of the "Rating" spinner.
     *
     * @param pos   The current item position of the "Rating" spinner.
     * @param p     The current Place object.
     * @return      True if the rating value of the Place object is equal or higher than
     *              the current rating value of the selected rating value of the "Rating"
     *              spinner, else returns false.
     */
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

	/**
	 * The method takes the selected item position of the "Injury type" spinner and checks
	 * the rating value of the selected position against the value of the Place object.
	 * The method returns true if the rating value of the Place object is equal or higher
	 * to the rating value of the selected rating value of the "Rating" spinner.
	 *
	 * @param pos   The current item position of the "Injury Type" spinner.
	 * @param p     The current Place object.
	 * @return      True if the rating value of the Place object is equal or higher than
	 *              the current rating value of the selected rating value of the "Rating"
	 *              spinner, else returns false.
	 */
	private boolean checkInjuryType(int pos, Place p) {
		switch(pos) {
            // TODO Implement this
			default:
				return false;
		}
	}

    /**
     * Returns an ArrayList of filtered Place objects to the MainActivity.
     *
     * @param returnData An ArrayList of Place objects
     */
    private void returnData(ArrayList<Place> returnData) {
        returnIntent = new Intent();
        returnIntent.putExtra(getResources().getString(R.string.filtered_data), returnData);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
