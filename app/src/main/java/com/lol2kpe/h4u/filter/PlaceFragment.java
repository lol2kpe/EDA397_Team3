package com.lol2kpe.h4u.filter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.lol2kpe.h4u.R;
import com.lol2kpe.h4u.data.model.Place;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import static com.lol2kpe.h4u.filter.FilterActivity.OPENING_HOUR;
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
    //OnFragmentDataRequestedListener mCallback;

    Spinner spinnerType, spinnerOpeningHours, spinnerRating;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.place_tab, container, false);

        spinnerType = (Spinner) rootView.findViewById(R.id.spinner_type);
        spinnerOpeningHours = (Spinner) rootView.findViewById(R.id.spinner_openinghours);
        spinnerRating = (Spinner) rootView.findViewById(R.id.spinner_rating);
        populateSpinner(spinnerType);
        populateSpinner(spinnerOpeningHours);
        populateSpinner(spinnerRating);
        setFilterSelections();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    /*
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnFragmentDataRequestedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentDataRequestedListener");
        }
    }
    */

    private void populateSpinner(Spinner spinner) {
        ArrayList<String> items = new ArrayList<>();
        for (Place p : returnList) {
            String item = null;
            if (spinner == spinnerType)
                item = p.getClass().getSimpleName();
            else if (spinner == spinnerRating)
                item = Integer.toString(p.getRating());
            if (!items.contains(item) && item != null)
                items.add(item);
        }
        if (!items.isEmpty()) {
            Collections.sort(items);
            items.add(0, "All");
        } else {
            items.add("Not available");
            toggleSpinner(spinner);
        }
        // Create Adapter object with data items for the Spinner object
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item,
                items.toArray(new String[items.size()]));
        // Set the View for the items in the data set in the Adapter object
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    void toggleSpinner(Spinner spinner) {
        spinner.setEnabled(!spinner.isEnabled());
    }

    /**
     * The method sets all the SpinnerObjects selections to their respective value
     * stored in the HashMap.
     */
    void setFilterSelections() {
        spinnerType.setSelection(filterSelections.get(TYPE));
        spinnerOpeningHours.setSelection(filterSelections.get(OPENING_HOUR));
        spinnerRating.setSelection(filterSelections.get(RATING));
    }

    /**
     * The method overwrites all values in the existing HashMap with their respective
     * value currently selected/displayed by the SpinnerObjects.
     */
    void storeFilterValues() {
        filterSelections.put(TYPE, spinnerType.getSelectedItemPosition());
        filterSelections.put(OPENING_HOUR, spinnerOpeningHours.getSelectedItemPosition());
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

    /*
    interface OnFragmentDataRequestedListener {

    }
    */
}
