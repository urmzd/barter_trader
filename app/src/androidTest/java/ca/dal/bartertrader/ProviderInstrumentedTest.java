package ca.dal.bartertrader;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.navigation.Navigation;
import androidx.navigation.testing.TestNavHostController;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ProviderInstrumentedTest {
    private static TestNavHostController navController;

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @BeforeClass
    public static void prepareFragmentNav() {
        navController = new TestNavHostController(ApplicationProvider.getApplicationContext());
        navController.setGraph(R.navigation.nav_graph);
        navController.setCurrentDestination(R.id.providerFragment);
    }

    @Test
    public void roleSwitchedTest() {
        FragmentScenario.launchInContainer(ProviderFragment.class).onFragment(fragment ->
                Navigation.setViewNavController(fragment.requireView(), navController)
        );

        onView(withId(R.id.provider_text)).check(matches(withText("Provider")));
        onView(withId(R.id.switch_role_button)).perform(ViewActions.click());
        assertEquals(navController.getCurrentDestination().getId(), (R.id.splashFragment));
    }

}
