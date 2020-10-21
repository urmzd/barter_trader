package ca.dal.bartertrader;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.navigation.Navigation;
import androidx.navigation.testing.TestNavHostController;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class SwitchRolesInstrumentedTest {
    private static TestNavHostController navController;
    private static FirebaseAuth firebaseAuth;
    private static FirebaseFirestore firebaseFirestore;
    private static FirebaseUser currentUser;
    private boolean role;

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @BeforeClass
    public static void prepareFirebase() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithEmailAndPassword("elias@gmail.com", "123abc");
        currentUser = firebaseAuth.getCurrentUser();
    }

    @BeforeClass
    public static void prepareNavFragment() {
        navController = new TestNavHostController(ApplicationProvider.getApplicationContext());
        navController.setGraph(R.navigation.nav_graph);
        navController.setCurrentDestination(R.id.providerFragment);
    }

    @Test
    public void switchToReceiverFragmentTest() {
        FragmentScenario.launchInContainer(ProviderFragment.class).onFragment(fragment ->
                Navigation.setViewNavController(fragment.requireView(), navController)
        );

        onView(withId(R.id.role_text)).check(matches(withText("PROVIDER")));
        onView(withId(R.id.switch_role_button)).perform(ViewActions.click());
        assertEquals(navController.getCurrentDestination().getId(), (R.id.receiverFragment));
    }

    @Test
    public void switchToProviderFragmentTest() {
        FragmentScenario.launchInContainer(ReceiverFragment.class).onFragment(fragment ->
                Navigation.setViewNavController(fragment.requireView(), navController)
        );

        onView(withId(R.id.role_text)).check(matches(withText("RECEIVER")));
        onView(withId(R.id.switch_role_button)).perform(ViewActions.click());
        assertEquals(navController.getCurrentDestination().getId(), (R.id.providerFragment));
    }

    @Test
    public void firestoreRoleSwitchedTest() {
        firebaseFirestore.collection("users")
                .document(currentUser.getUid())
                .get().addOnCompleteListener(task -> {
            role = task.getResult().getBoolean("provider");
        });

        onView(withId(R.id.switch_role_button)).perform(ViewActions.click());

        firebaseFirestore.collection("users")
                .document(currentUser.getUid())
                .get().addOnCompleteListener(task -> {
            assertTrue(role != task.getResult().getBoolean("provider"));
        });
    }
}
