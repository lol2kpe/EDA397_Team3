package com.lol2kpe.h4u;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.support.test.espresso.core.deps.guava.base.Strings;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.StringStartsWith.startsWith;

/**
 * Created by Jonathan Granström on 2017-04-03.
 */

@RunWith(AndroidJUnit4.class)
public class TestFilterActivity {


    @Rule
    public ActivityTestRule<FilterActivity> activityRule =
            new ActivityTestRule<>(FilterActivity.class);

    @Test
    public void testSetButtonIsPresent() {
        // Check if set button is present
        onView(withId(R.id.button_set))
                .check(matches(notNullValue()));
        onView(withId(R.id.button_set))
                .perform(click());
    }

    @Test
    public void testCancelButtonIsPresent() {
        // Check if cancel button is present
        onView(withId(R.id.button_cancel))
                .check(matches(notNullValue()));
        onView(withId(R.id.button_cancel))
                .perform(click());
    }

    @Test
    public void testTypeOptionIsPresent() {
        // Check if type option is present
        onView(withId(R.id.text_type))
                .check(matches(notNullValue()));
        // Check if type spinner is present
        onView(withId(R.id.spinner_type))
                .check(matches(notNullValue()));
        onView(withId(R.id.spinner_type))
                .perform(click());
    }

    @Test
    public void testTypeOptionSpinnerHasValues() {
        // Check if spinner data exists
        onView(withId(R.id.spinner_type)).check(matches(withSpinnerText("All")));
    }

    @Test
    public void testTypeOptionSpinnerHasCorrectValue() {
        // Click the spinner
        onView(withId(R.id.spinner_type)).perform(click());
        // Make sure "Dentist" is a String and is called "Dentist", then select it
        onData(allOf(is(instanceOf(String.class)), is("Dentists"))).perform(click());
        // Make sure the spinner has the correct value
        onView(withId(R.id.spinner_type)).check(matches(withSpinnerText("Dentists")));
    }
}
