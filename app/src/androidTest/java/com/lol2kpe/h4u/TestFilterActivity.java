package com.lol2kpe.h4u;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by Jonathan Granström on 2017-04-03.
 */

@RunWith(AndroidJUnit4.class)
public class TestFilterActivity {

    @Rule
    public IntentsTestRule<FilterActivity> mActivityRule =
            new IntentsTestRule<>(FilterActivity.class);

    @Test
    public void testSetButtonIsPresent() {
        // Check if set button is present
        onView(withId(R.id.button_set))
                .check(matches(notNullValue()));
    }

    @Test
    public void testCancelButtonIsPresent() {
        // Check if cancel button is present
        onView(withId(R.id.button_cancel))
                .check(matches(notNullValue()));
    }

    @Test
    public void testTypeOptionIsPresent() {
        // Check if type option is present
        onView(withId(R.id.text_type))
                .check(matches(notNullValue()));
        onView(withId(R.id.spinner_type))
                .check(matches(notNullValue()));
    }

    // TODO: Implement tests for filter functionality
}
