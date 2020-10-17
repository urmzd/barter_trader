package ca.dal.bartertrader.receiver;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import static org.junit.Assert.*;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ca.dal.bartertrader.MainActivity;

public class ReceiverViewModelTest {
    private static final String COLLECTION = "testCollection";
    private static final String DOCUMENT = "testDocument";
    static final String TEST_VAL_1 = "value1";
    static final String TEST_VAL_2 = "value2";
    static final String TEST_VAL_3 = "value3";

    static String value1;
    static String value2;
    static String value3;

    @Mock
    FirebaseFirestore mockFirestore;

    @Mock
    MainActivity mainActivity;

    private ReceiverViewModel mViewModel;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }
}
