package ca.dal.bartertrader.account_recovery;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.navigation.Navigation;
import androidx.navigation.testing.TestNavHostController;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import ca.dal.bartertrader.MainActivity;
import ca.dal.bartertrader.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class AccountRecoveryFragmentTest {

    private TestNavHostController navController;
    private FragmentScenario<AccountRecoveryFragment> receiverScenario;
    private static String EMAIL_NO_AT = "noAtemail.com";
    private static String EMAIL_NO_DOT = "nodotemail.com";
    private static String EMAIL_VALID = "celeste@email.com";

    @Before
    public void setUp() throws Exception {
        receiverScenario = FragmentScenario.launchInContainer(AccountRecoveryFragment.class);
        navController = new TestNavHostController(
                ApplicationProvider.getApplicationContext());
        navController.setGraph(R.navigation.nav_graph);
        navController.setCurrentDestination(R.id.splashFragment);

    }

    @Test
    public void emailIsMissingAt() {

        onView(withId(R.id.account_recovery_text_email))
                .perform(typeText(EMAIL_NO_AT), closeSoftKeyboard());
        onView(withId(R.id.account_recovery_send_email_button))
                .perform(click());
        onView(withId(R.id.account_recovery_text_email))
                .check(matches(hasErrorText("Error--invalid email format"/*getString(R.string.error_email_invalid))*/)));
    }

    @Test
    public void emailIsMissingDot() {

        onView(withId(R.id.account_recovery_text_email))
                .perform(typeText(EMAIL_NO_DOT ), closeSoftKeyboard());
        onView(withId(R.id.account_recovery_send_email_button))
                .perform(click());
        onView(withId(R.id.account_recovery_text_email))
                .check(matches(hasErrorText("Error--invalid email format"/*regPage.getString(R.string.error_email_invalid)*/)));
    }

    @Test
    public void navigateToLoginScreenAfterEmailSent()
    {
        receiverScenario.onFragment(fragment ->
                Navigation.setViewNavController(fragment.requireView(), navController));

        onView(withId(R.id.account_recovery_text_email))
                .perform(typeText(EMAIL_VALID), closeSoftKeyboard());
        onView(withId(R.id.account_recovery_send_email_button))
                .perform(click());

        //TODO: replace this with login fragment
        assertEquals(navController.getCurrentDestination().getId(),R.id.splashFragment);

    }
    @After
    public void tearDown() throws Exception {
    }
}