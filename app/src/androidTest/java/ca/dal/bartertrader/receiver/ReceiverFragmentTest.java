package ca.dal.bartertrader.receiver;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.testing.TestNavHostController;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.truth.view.PointerPropertiesSubject;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import ca.dal.bartertrader.R;
import ca.dal.bartertrader.provider.DummyProviderFragment;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.*;

public class ReceiverFragmentTest {
    TestNavHostController navController;
    FragmentScenario<ReceiverFragment> receiverScenario;

    @Before
    public void setUp() throws Exception {
        receiverScenario = FragmentScenario.launchInContainer(ReceiverFragment.class);
        navController = new TestNavHostController(
                ApplicationProvider.getApplicationContext());
        navController.setGraph(R.navigation.nav_graph);
        navController.setCurrentDestination(R.id.ReceiverFragment);
    }

    @Test
    public void testSwitchRoles()
    {

        // Set the NavController property on the fragment
        receiverScenario.onFragment(fragment ->
                Navigation.setViewNavController(fragment.requireView(), navController)
        );

        onView(withId(R.id.switchRolesButton))
                .perform(click());

        //TODO: replace this with real Provider fragment
        assertEquals(navController.getCurrentDestination().getId(),R.id.DummyProviderFragment);
    }

    @After
    public void tearDown() throws Exception {
    }
}