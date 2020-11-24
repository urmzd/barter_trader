package ca.dal.bartertrader.presentation.view;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import ca.dal.bartertrader.MainActivity;
import ca.dal.bartertrader.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

public class ProfileFragmentInstrumentedTest {
    @Rule
    public ActivityScenarioRule<MainActivity> myRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void profileLayout() throws InterruptedException{
        onView(withHint("Email"))
                .perform(click())
                .perform(typeText("spyromagic10@gmail.com"))
                .perform(closeSoftKeyboard());

        onView(withHint("Password"))
                .perform(click())
                .perform(typeText("Ab4330317!"))
                .perform(closeSoftKeyboard());

        onView(withText("LOG IN"))
                .perform(click());

        Thread.sleep(1000);

        onView(withText("PROFILE"))
                .perform(click());

        onView(withId(R.id.profile_fragment_text_username))
                .check(matches(isDisplayed()));

        onView(withId(R.id.profile_fragment_text_joinDate))
                .check(matches(isDisplayed()));

        onView(withId(R.id.profile_fragment_recyclerView_review))
                .check(matches(isDisplayed()));
    }

    @Test
    public void displayName() throws InterruptedException {
        onView(withHint("Email"))
                .perform(click())
                .perform(typeText("spyromagic10@gmail.com"))
                .perform(closeSoftKeyboard());

        onView(withHint("Password"))
                .perform(click())
                .perform(typeText("Ab4330317!"))
                .perform(closeSoftKeyboard());

        onView(withText("LOG IN"))
                .perform(click());

        Thread.sleep(1000);

        onView(withText("PROFILE"))
                .perform(click());

        onView(withId(R.id.profile_fragment_text_username))
                .check(matches(withText("Nicholas McPhee")));
    }
}