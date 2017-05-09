package com.lol2kpe.h4u.filter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.lol2kpe.h4u.R;
import com.lol2kpe.h4u.data.model.Place;
import com.lol2kpe.h4u.data.model.Symptom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static com.lol2kpe.h4u.filter.FilterActivity.KEY.SYMPTOM;
import static com.lol2kpe.h4u.filter.FilterActivity.filterSelections;
import static com.lol2kpe.h4u.filter.FilterActivity.returnList;

/**
 * Created by Jonathan Granström
 * User: Jonathan "juntski" Granström
 * Date: 2017-04-24
 */
public class SymptomFragment extends Fragment {

    Spinner spinnerSymptom;
    Set<Symptom> symptoms;
    SparseArray<Symptom> symptomsMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.symptom_tab, container, false);

        spinnerSymptom = (Spinner) rootView.findViewById(R.id.spinner_symptom);

        getSymptoms();
        populateSpinner(spinnerSymptom);
        setFilterSelections();

        return rootView;
    }

    private void getSymptoms() {

        symptoms = new HashSet<>();
        Collections.addAll(symptoms, Symptom.values());

        Log.i("Symptoms", symptoms.toString());
    }

    /**
     * Populates the Spinner object with a list of items. The method takes the Spinner object and
     * based on the type of the Spinner object, retrives relevant information from the list of
     * Place objects. Creates an empty list if no relevant info could be found, or creates a
     *
     * @param spinner the spinner object to populate with list items
     */
    private void populateSpinner(Spinner spinner) {

        ArrayList<String> items = new ArrayList<>();
        // If no symptoms are found, put a notice in the spinner and disable the spinner
        if (symptoms.isEmpty() && spinner.isEnabled()) {
            toggleSpinner(spinner);
            items.add(getResources().getString(R.string.not_available));
        }
        // Else, map each symptom
        else {
            symptomsMap = new SparseArray<>();
            items.add(getResources().getString(R.string.symptom_all));
            int i = 1;
            for (Symptom symptom : symptoms) {
                symptomsMap.put(i, symptom);
                items.add(getSymptomString(symptom));
                i++;
            }
        }
        // Create Adapter object with data items for the Spinner object
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(), android.R.layout.simple_spinner_item, items);
        // Set the View for the items in the data set in the Adapter object
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private String getSymptomString(Symptom symptom) {

        switch (symptom) {
            case FEVER:
                return getResources().getString(R.string.symptom_fever);
            case COLD:
                return getResources().getString(R.string.symptom_cold);
            case COUGH:
                return getResources().getString(R.string.symptom_cough);
            case BROKEN_BONE:
                return getResources().getString(R.string.symptom_broke);
            case SORE_THROAT:
                return getResources().getString(R.string.symptom_throat);
            case EAR_DISORDER:
                return getResources().getString(R.string.symptom_ear);
            case URINARY_DISORDER:
                return getResources().getString(R.string.symptom_urine);
            case HEADACHE:
                return getResources().getString(R.string.symptom_head);
            default:
                Log.w("NoSymptomStringFound", "No matching String was found for a symptom: " + symptom.toString());
                return "Unknown symptom";
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
    private boolean checkSymptom(Place place) {

        Set<Symptom> symptoms = place.getSymptoms();
        Log.i("CheckSymptomInfo", "Place: " + place.getName() +
                " Symptoms: " + place.getSymptoms().toString() +
                " Selected symptom: " + symptomsMap.get(spinnerSymptom.getSelectedItemPosition()));
        return (symptoms.contains(symptomsMap.get(spinnerSymptom.getSelectedItemPosition())) ||
                spinnerSymptom.getSelectedItemPosition() == 0);
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

        spinnerSymptom.setSelection(filterSelections.get(SYMPTOM));
    }

    /**
     * The method overwrites all values in the existing HashMap with their respective
     * value currently selected/displayed by the SpinnerObjects.
     */
    void storeFilterValues() {

        filterSelections.put(SYMPTOM, spinnerSymptom.getSelectedItemPosition());
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
            // Symptom filter
            for (Iterator<Place> i = returnList.iterator(); i.hasNext(); ) {
                Place item = i.next();
                if (!checkSymptom(item))
                    i.remove();
            }
        }
    }
}