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
import com.lol2kpe.h4u.data.model.HospitalSymptom;
import com.lol2kpe.h4u.data.model.PharmacySymptom;
import com.lol2kpe.h4u.data.model.Place;
import com.lol2kpe.h4u.data.model.Symptom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static com.lol2kpe.h4u.data.model.HospitalSymptom.COUGH;
import static com.lol2kpe.h4u.data.model.HospitalSymptom.EAR_DISORDER;
import static com.lol2kpe.h4u.data.model.HospitalSymptom.URINARY_DISORDER;
import static com.lol2kpe.h4u.data.model.HospitalSymptom.URNIARY_DISORDER;
import static com.lol2kpe.h4u.data.model.PharmacySymptom.COLD;
import static com.lol2kpe.h4u.data.model.PharmacySymptom.FEVER;
import static com.lol2kpe.h4u.data.model.PharmacySymptom.HEADACHE;
import static com.lol2kpe.h4u.data.model.PharmacySymptom.SORE_THROAT;
import static com.lol2kpe.h4u.filter.FilterActivity.SYMPTOM;
import static com.lol2kpe.h4u.filter.FilterActivity.filterSelections;
import static com.lol2kpe.h4u.filter.FilterActivity.returnList;

/**
 * Created by Jonathan on 2017-04-24.
 */
public class SymptomFragment extends Fragment {

    Spinner spinnerSymptom;
    Map<String, Symptom> symptomsMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.symptom_tab, container, false);

        Set<Symptom> symptoms = new HashSet<>();
        Collections.addAll(symptoms, Symptom.values());
        Log.i("Symptoms", symptoms.toString());
        symptomsMap = new HashMap<>();

        for (Symptom symptom : symptoms) {
            mapSymptom(symptom);
        }

        spinnerSymptom = (Spinner) rootView.findViewById(R.id.spinner_symptom);
        populateSpinner(spinnerSymptom);
        setFilterSelections();

        return rootView;
    }

    private void mapSymptom(Symptom symptom) {

        switch(symptom) {
            case FEVER:
                symptomsMap.put(getResources().getString(R.string.symptom_fever), FEVER);
                break;
            case HEADACHE:
                break;
            case COLD:
                break;
            case SORE_THROAT:
                break;
            case COUGH:
                break;
            case URINARY_DISORDER:
                break;
            case EAR_DISORDER:
                break;
            default:
                break;
        }

    }

    /**
     * Populates the Spinner object with a list of items. The method takes the Spinner object and
     * based on the type of the Spinner object, retrives relevant information from the list of
     * Place objects. Creates an empty list if no relevant info could be found, or creates a
     *
     * @param spinner
     */
    private void populateSpinner(Spinner spinner) {
        ArrayList<String> items = new ArrayList<>();
        for (Place p : returnList) {

            

            String item = null;
            if (spinner == spinnerSymptom)
                item = p.getClass().getSimpleName();
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
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(), R.array.activity_filter_symptom_options,
                android.R.layout.simple_spinner_item);
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
    private boolean checkSymptom(Place p) {
        if (spinnerSymptom.getSelectedItem().toString().equals("All"))
            return true;
        else if (spinnerSymptom.getSelectedItem().toString().equals("Traumatologibrokenlegs Syndrome") &&
                p.getClass().getSimpleName().equals("Hospital"))
            return true;
        else if (spinnerSymptom.getSelectedItem().toString().equals("Headache") &&
                p.getClass().getSimpleName().equals("Pharmacy"))
            return true;
        else if (spinnerSymptom.getSelectedItem().toString().equals("Sore throat") &&
                p.getClass().getSimpleName().equals("Pharmacy"))
            return true;
        else
            return false;

    }
}