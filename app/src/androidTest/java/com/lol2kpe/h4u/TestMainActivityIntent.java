package com.lol2kpe.h4u;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Jonathan Granström
 * User: Jonathan "juntski" Granström
 * Date: 2017-04-03
 */

@RunWith(AndroidJUnit4.class)
public class TestMainActivityIntent {

    @Rule
    public IntentsTestRule<MainActivity> intentRule =
            new IntentsTestRule<>(MainActivity.class);

    @Test
    public void testFilterActivityIsLaunched() {
        // Check if filter activity is launched
        onView(withId(R.id.filter)).perform(click());
        intended(hasComponent(FilterActivity.class.getName()));
    }
}
