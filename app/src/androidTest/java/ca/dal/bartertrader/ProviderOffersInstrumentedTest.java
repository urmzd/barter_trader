package ca.dal.bartertrader;


import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;


@RunWith(AndroidJUnit4.class)
public class ProviderOffersInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setup() throws InterruptedException {
        onView(withHint("Email"))
                .perform(click())
                .perform(typeText("eliask1094@gmail.com"))
                .perform(closeSoftKeyboard());

        onView(withHint("Password"))
                .perform(click())
                .perform(typeText("123123Abc/!"))
                .perform(closeSoftKeyboard());

        onView(withText("LOG IN"))
                .perform(click());

        Thread.sleep(5000);
        onView(withId(R.id.view_provider_offers)).perform(click());
        Thread.sleep(5000);
    }

    @Test
    public void checkReceiverTitleDisplayed() {
        onView(allOf(withId(R.id.receiver_title), withText("Offer: room4"))).check(matches(isDisplayed()));
    }


    @Test
    public void checkProviderTitleDisplayed() {
        onView(withText("Your post: Room")).check(matches(isDisplayed()));
    }

    @Test
    public void onAcceptClick() throws InterruptedException {
        final TextView[] acceptText = clickAccept(0);
        assertEquals(acceptText[0].getVisibility(), View.VISIBLE);
    }

    @Test
    public void onDeclineClick() throws InterruptedException {
        final TextView[] declineText = clickDecline(0);
        assertEquals(declineText[0].getVisibility(), View.VISIBLE);
    }


    @Test
    public void onClickReview() throws InterruptedException {
        clickAccept(1);
        clickReview(1);
        Thread.sleep(2000);

        onView(withText("Exchange Review Form")).check(matches(isDisplayed()));
    }

    private TextView[] clickAccept(int position) throws InterruptedException {
        final TextView[] acceptText = new TextView[1];
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(position,
                new ViewAction() {
                    @Override
                    public Matcher<View> getConstraints() {
                        return null;
                    }

                    @Override
                    public String getDescription() {
                        return "On accept click";
                    }

                    @Override
                    public void perform(UiController uiController, View view) {
                        view.findViewById(R.id.accept_button).performClick();

                        acceptText[0] = view.findViewById(R.id.accepted_text);

                    }
                }));
        Thread.sleep(2000);
        return acceptText;
    }

    private TextView[] clickDecline(int position) throws InterruptedException {
        final TextView[] declineText = new TextView[1];
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(position,
                new ViewAction() {
                    @Override
                    public Matcher<View> getConstraints() {
                        return null;
                    }

                    @Override
                    public String getDescription() {
                        return "On decline click";
                    }

                    @Override
                    public void perform(UiController uiController, View view) {
                        view.findViewById(R.id.decline_button).performClick();

                        declineText[0] = view.findViewById(R.id.declined_text);

                    }
                }));
        Thread.sleep(2000);
        return declineText;
    }

    private void clickReview(int position) throws InterruptedException {
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(position,
                new ViewAction() {
                    @Override
                    public Matcher<View> getConstraints() {
                        return null;
                    }

                    @Override
                    public String getDescription() {
                        return "On review click";
                    }

                    @Override
                    public void perform(UiController uiController, View view) {
                        view.findViewById(R.id.review_button).performClick();

                    }
                }));
        Thread.sleep(2000);
    }


}

