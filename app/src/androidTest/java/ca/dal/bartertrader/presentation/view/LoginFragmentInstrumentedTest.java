package ca.dal.bartertrader.presentation.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;

import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.dal.bartertrader.MainActivity;
import ca.dal.bartertrader.R;

import static org.junit.Assert.*;

public class LoginFragmentInstrumentedTest {
    @Rule
    public ActivityScenarioRule<MainActivity> myRule
            = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void navToRegistration() {
        onView(withId(R.id.login_register))
                .perform(click());

        onView(withId(R.id.registration_register))
                .check(matches(isDisplayed()));
    }

    @Test
    public void navToPasswordReset() {
        onView(withId(R.id.login_forgot_password))
                .perform(click());

        onView(withId(R.id.passwordReset_fragment))
                .check(matches(isDisplayed()));
    }

    @Test
    public void logInValidation() throws InterruptedException{
        onView(withHint("Email"))
                .perform(click())
                .perform(typeText("poyowox421@ptcji.com"))
                .perform(closeSoftKeyboard());

        onView(withHint("Password"))
                .perform(click())
                .perform(typeText("Ab433-0317"))
                .perform(closeSoftKeyboard());

        onView(withText("LOG IN"))
                .perform(click());

        Thread.sleep(2000);
        onView(withId(R.id.splash_fragment))
                .check(matches(isDisplayed()));
    }

}