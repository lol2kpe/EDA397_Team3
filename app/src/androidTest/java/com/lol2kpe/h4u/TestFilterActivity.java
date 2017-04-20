package com.lol2kpe.h4u;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import org.hamcrest.collection.IsMapContaining;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static com.lol2kpe.h4u.FilterActivity.filterSelections;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by Jonathan Granström
 * User: Jonathan "juntski" Granström
 * Date: 2017-04-03
 */

@RunWith(AndroidJUnit4.class)
public class TestFilterActivity {


    @Rule
    public ActivityTestRule<FilterActivity> activityTestRule =
            new ActivityTestRule<>(FilterActivity.class);

    @Before
    public void setUp() {
        filterSelections = new HashMap<>();
        filterSelections.put("type", 0);
        filterSelections.put("openingHour", 0);
        filterSelections.put("rating", 1);

    }

    @Test
    public void testButtonsArePresent() {
        // Check if set button is present
        onView(withId(R.id.button_set))
                .check(matches(notNullValue()));
        // Check if cancel button is present
        onView(withId(R.id.button_cancel))
                .check(matches(notNullValue()));
        // Check if clear filter is present
        onView(withId(R.id.toolbar_action_clear_filter))
                .check(matches(notNullValue()));
    }

    @Test
    public void testSpinnerOptionsArePresent() {
        // Check if texts are present
        onView(withId(R.id.text_type))
                .check(matches(notNullValue()));
        onView(withId(R.id.text_openinghours))
                .check(matches(notNullValue()));
        onView(withId(R.id.text_rating))
                .check(matches(notNullValue()));
        // Check if spinners are present
        onView(withId(R.id.spinner_type))
                .check(matches(notNullValue()));
        onView(withId(R.id.spinner_openinghours))
                .check(matches(notNullValue()));
        onView(withId(R.id.spinner_rating))
                .check(matches(notNullValue()));
        // Check is spinners have values
        onView(withId(R.id.spinner_type))
                .check(matches(withSpinnerText(R.string.type_all)));
        onView(withId(R.id.spinner_openinghours))
                .check(matches(withSpinnerText(R.string.openinghours_all)));
        onView(withId(R.id.spinner_rating))
                .check(matches(withSpinnerText("2")));
    }

    @Test
    public void testFilterHashMap() {
        // Create expected HashMap
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("type", 0);
        expected.put("openingHour", 0);
        expected.put("rating", 1);
        // Check if HashMap exists
        assertNotNull(filterSelections);
        // Test size
        assertThat(filterSelections.size(), is(3));
        // Test equal but ignore the order
        assertThat(filterSelections, is(expected));
        // Test HashMap keys
        assertThat(filterSelections, IsMapContaining.hasKey("type"));
        assertThat(filterSelections, IsMapContaining.hasKey("openingHour"));
        assertThat(filterSelections, IsMapContaining.hasKey("rating"));
        // Test HashMap values
        assertThat(filterSelections, IsMapContaining.hasValue(0));
        assertThat(filterSelections, IsMapContaining.hasValue(1));
        // Test HashMap key/entry values
        assertEquals(0, (int)filterSelections.get("type"));
        assertEquals(0, (int)filterSelections.get("openingHour"));
        assertEquals(1, (int)filterSelections.get("rating"));
    }

    @Test
    public void testCancelDoesNotUpdateHashMap() {
        // Create expected HashMap
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("type", 0);
        expected.put("openingHour", 0);
        expected.put("rating", 1);

        String type = activityTestRule.getActivity().getResources()
                .getString(R.string.type_healthcenters);
        String openingHours = activityTestRule.getActivity().getResources()
                .getString(R.string.openinghours_opennow);
        String rating = "5";
        // Perform selections in the different spinners
        onView(withId(R.id.spinner_type)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(type))).perform(click());
        onView(withId(R.id.spinner_openinghours)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(openingHours))).perform(click());
        onView(withId(R.id.spinner_rating)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(rating))).perform(click());
        // Cancel the FilterActivity
        onView(withId(R.id.button_cancel)).perform(click());
        // Make sure the HashMap has not changed
        assertNotNull(filterSelections);
        assertThat(filterSelections.size(), is(3));
        assertThat(filterSelections, is(expected));
        assertEquals(0, (int)filterSelections.get("type"));
        assertEquals(0, (int)filterSelections.get("openingHour"));
        assertEquals(1, (int)filterSelections.get("rating"));
    }

    @Test
    public void testSetUpdatesHashMap() {
        // Create expected HashMap
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("type", 1);
        expected.put("openingHour", 1);
        expected.put("rating", 4);

        String type = activityTestRule.getActivity().getResources()
                .getString(R.string.type_healthcenters);
        String openingHours = activityTestRule.getActivity().getResources()
                .getString(R.string.openinghours_opennow);
        String rating = "5";
        // Perform selections in the different spinners
        onView(withId(R.id.spinner_type)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(type))).perform(click());
        onView(withId(R.id.spinner_openinghours)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(openingHours))).perform(click());
        onView(withId(R.id.spinner_rating)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(rating))).perform(click());
        // Store the selections in the HashMap and end the FilterActivity
        onView(withId(R.id.button_set)).perform(click());
        // Make sure the HashMap has changed
        assertNotNull(filterSelections);
        assertThat(filterSelections.size(), is(3));
        assertThat(filterSelections, is(expected));
        assertEquals(1, (int)filterSelections.get("type"));
        assertEquals(1, (int)filterSelections.get("openingHour"));
        assertEquals(4, (int)filterSelections.get("rating"));
    }
}
