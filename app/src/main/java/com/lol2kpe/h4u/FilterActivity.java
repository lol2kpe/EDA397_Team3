package com.lol2kpe.h4u;

import android.app.Activity;
import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.lol2kpe.h4u.data.model.Place;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Jonathan Granström
 * User: Jonathan "juntski" Granström
 * Date: 2017-04-02
 */

@SuppressWarnings("ConstantConditions")
public class FilterActivity extends AppCompatActivity {

    public static HashMap<String, Integer> filterSelections;
    private final String TYPE = "type";
    private final String OPENING_HOUR = "openingHour";
    private final String RATING = "rating";
    Intent returnIntent;


    private ArrayList<Place> returnList;
    private Spinner spinnerType, spinnerOpeningHours, spinnerRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        // Enable the up action to the toolbar (the "<-" arrow)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get the list of Place objects from MainActivity
        returnList = getIntentData();

        // Get the buttons and set their onClickListeners and functionality
        Button cancelButton = (Button) findViewById(R.id.button_cancel);
        cancelButton.setOnClickListener(view -> {
            returnIntent = new Intent(this, MainActivity.class);
            setResult(Activity.RESULT_CANCELED);
            finish();
        });
        Button setButton = (Button) findViewById(R.id.button_set);
        setButton.setOnClickListener(view -> {
            storeFilterValues();
            filter();
            returnData();
        });

        // Create filter options for the activity
        createFilterOptions();

        // If the app/activity is started for the first time, create a HashMap to store values
        if (filterSelections == null) {
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
                setDefaultFilterValues();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void createFilterOptions() {
        // Get the spinner objects
        spinnerType = (Spinner) findViewById(R.id.spinner_type);
        spinnerOpeningHours = (Spinner) findViewById(R.id.spinner_openinghours);
        spinnerRating = (Spinner) findViewById(R.id.spinner_rating);

        populateSpinner(spinnerType);
        populateSpinner(spinnerRating);
        populateSpinner(spinnerOpeningHours);
    }

    private void populateSpinner(Spinner spinner) {
        ArrayList<String> items = new ArrayList<>();
        for(Place p : returnList) {
            String item = null;
            if(spinner == spinnerType)
                item = p.getClass().getSimpleName();
            else if(spinner == spinnerRating)
                item = Integer.toString(p.getRating());
            if(!items.contains(item) && item != null)
                items.add(item);
        }
        if(!items.isEmpty()) {
            Collections.sort(items);
            items.add(0, "All");
        } else {
            items.add("Not available");
            toggleSpinner(spinner);
        }
        // Create Adapter object with data items for the Spinner object
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,
                items.toArray(new String[items.size()]));
        // Set the View for the items in the data set in the Adapter object
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void toggleSpinner(Spinner spinner) {
        spinner.setEnabled(!spinner.isEnabled());

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

    /**
     * The method sets all the selections of the SpinnerObjects to their default values.
     */
    private void setDefaultFilterValues() {
        spinnerType.setSelection(0);
        spinnerOpeningHours.setSelection(0);
        spinnerRating.setSelection(0);
    }

    /**
     * The method overwrites all values in the existing HashMap with their respective
     * value currently selected/displayed by the SpinnerObjects.
     */
    private void storeFilterValues() {
        filterSelections.put(TYPE, spinnerType.getSelectedItemPosition());
        filterSelections.put(OPENING_HOUR, spinnerOpeningHours.getSelectedItemPosition());
        filterSelections.put(RATING, spinnerRating.getSelectedItemPosition());
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
     * The method filters the ArrayList of Place-objects if the list is not empty. Iterator loops
     * for each filter alternative removes invalid Place objects that does not correspond to the
     * user's filter choices. In the end, the return list is either empty or contains one or more
     * Place objects.
     */
    @SuppressWarnings({"unchecked"})
    private void filter() {
        if (!returnList.isEmpty()) {
            // Type filter
            for (Iterator<Place> i = returnList.iterator(); i.hasNext(); ) {
                Place item = i.next();
                if (!checkType(item))
                    i.remove();
            }

            // Rating filter
            for (Iterator<Place> i = returnList.iterator(); i.hasNext(); ) {
                Place item = i.next();
                if (!checkRating(item))
                    i.remove();
            }
        }
    }

    /**
     * The method takes the selected item of the "Type" spinner and compares
     * its String value against the class of the Place object. The method returns true if the
     * Place object's class is equal to the String value of the selected item.
     * Else, the method returns false.
     *
     * @param p The current Place object.
     * @return True if the class of the Place object is equal to the current String value
     * of the selected item, else returns false.
     */
    private boolean checkType(Place p) {
        return (spinnerType.getSelectedItem().toString().equals("All") ||
                spinnerType.getSelectedItem().toString().equals(p.getClass().getSimpleName()));
    }

    /**
     * The method takes the selected item of the "Rating" spinner and compares
     * its value against the rating value of the Place object. The method returns true if the
     * Place object's rating value is equal or higher to the rating value of the selected item.
     * Else, the method returns false.
     *
     * @param p The current Place object.
     * @return True if the Place object's rating is equal or higher than
     * the selected item value, else returns false.
     */
    private boolean checkRating(Place p) {
        return (spinnerRating.getSelectedItem().toString().equals("All") ||
                (p.getRating() >= Integer.parseInt(spinnerRating.getSelectedItem().toString())));
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
