package com.lol2kpe.h4u.filter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.lol2kpe.h4u.R;
import com.lol2kpe.h4u.data.model.Hospital;
import com.lol2kpe.h4u.data.model.Place;
import com.lol2kpe.h4u.data.model.Places;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static com.lol2kpe.h4u.data.model.Places.Hospital;
import static com.lol2kpe.h4u.filter.FilterActivity.RATING;
import static com.lol2kpe.h4u.filter.FilterActivity.TYPE;
import static com.lol2kpe.h4u.filter.FilterActivity.filterSelections;
import static com.lol2kpe.h4u.filter.FilterActivity.returnList;

/**
 * Created by Jonathan Granström
 * User: Jonathan "juntski" Granström
 * Date: 2017-04-24
 */
public class PlaceFragment extends Fragment {

    Spinner spinnerType, spinnerRating;
    Set<Places> places;
    Map<Integer, Places> placesMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.place_tab, container, false);

        spinnerType = (Spinner) rootView.findViewById(R.id.spinner_type);
        spinnerRating = (Spinner) rootView.findViewById(R.id.spinner_rating);

        getPlaces();
        populateSpinner(spinnerType);
        populateSpinner(spinnerRating);

        setFilterSelections();

        return rootView;
    }

    private void getPlaces() {

        places = new HashSet<>();
        Collections.addAll(places, Places.values());

        Log.i("Places", places.toString());
    }

    /**
     * Populates the Spinner object with a list of items. The method takes the Spinner object and
     * based on the type of the Spinner object, retrives relevant information from the list of
     * Place objects. Creates an empty list if no relevant info could be found, or creates a
     *
     * @param spinner the spinner objects to populate with list items
     */
    private void populateSpinner(Spinner spinner) {
        ArrayList<String> items = new ArrayList<>();

        if (spinner.equals(spinnerType)) {
            // If no types are found, put a notice in the spinner and disable the spinner
            if (places.isEmpty() && spinner.isEnabled()) {
                toggleSpinner(spinner);
                items.add(getResources().getString(R.string.not_available));
            }
            // Else, map each type
            else {
                placesMap = new HashMap<>();
                items.add(getResources().getString(R.string.type_all));
                int i = 1;
                for (Places place : places) {
                    placesMap.put(i, place);
                    items.add(getPlaceString(place));
                    i++;
                }
            }
        } else if (spinner.equals(spinnerRating)) {
            for (int i = 1; i <= 5; i++) {
                items.add(Integer.toString(i));
            }
        }
        // Create Adapter object with data items for the Spinner object
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, items);
        // Set the View for the items in the data set in the Adapter object
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    /**
     * Simply toggles the availability of a Spinner
     *
     * @param spinner The Spinner to enable/disable
     */
    void toggleSpinner(Spinner spinner) {
        spinner.setEnabled(!spinner.isEnabled());
    }

    /**
     * The method sets all the SpinnerObjects selections to their respective value
     * stored in the HashMap.
     */
    void setFilterSelections() {
        spinnerType.setSelection(filterSelections.get(TYPE));
        spinnerRating.setSelection(filterSelections.get(RATING));
    }

    /**
     * The method overwrites all values in the existing HashMap with their respective
     * value currently selected/displayed by the SpinnerObjects.
     */
    void storeFilterValues() {
        filterSelections.put(TYPE, spinnerType.getSelectedItemPosition());
        filterSelections.put(RATING, spinnerRating.getSelectedItemPosition());
    }

    /**
     * The method filters the ArrayList of Place-objects if the list is not empty. Iterator loops
     * for each filter alternative removes invalid Place objects that does not correspond to the
     * user's filter choices. In the end, the return list is either empty or contains one or more
     * Place objects.
     */
    @SuppressWarnings({"unchecked"})
    void filter() {
        if (!returnList.isEmpty()) {
            // Type filter
            if (spinnerType.getSelectedItemPosition() != 0) {
                for (Iterator<Place> i = returnList.iterator(); i.hasNext(); ) {
                    Place item = i.next();
                    if (!checkType(item))
                        i.remove();
                }
            }
            // Rating filter
            for (Iterator<Place> i = returnList.iterator(); i.hasNext(); ) {
                Place item = i.next();
                if (!checkRating(item))
                    i.remove();
            }
        }
    }

    private String getPlaceString(Places place) {

        switch (place) {
            case Hospital:
                return getResources().getString(R.string.type_healthcenters);
            case Pharmacy:
                return getResources().getString(R.string.type_pharmacies);
            default:
                Log.w("NoTypeStringFound", "No matching String was found for a type: " + place.toString());
                return "Unknown type";
        }
    }

    /**
     * The method takes the selected item of the "Type" spinner and compares
     * its String value against the class of the Place object. The method returns true if the
     * Place object's class is equal to the String value of the selected item.
     * Else, the method returns false.
     *
     * @param place The current Place object.
     * @return True if the class of the Place object is equal to the current String value
     * of the selected item, else returns false.
     */
    private boolean checkType(Place place) {
        String currentPlace = place.getClass().getSimpleName();
        Log.i("TYPE", "Current type: " + currentPlace + " Spinner pos: " + Integer.toString(spinnerType.getSelectedItemPosition()));
        return (currentPlace.equals(placesMap.get(spinnerType.getSelectedItemPosition()).toString()));
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
        return (p.getRating() >= Integer.parseInt(spinnerRating.getSelectedItem().toString()));
    }
}
