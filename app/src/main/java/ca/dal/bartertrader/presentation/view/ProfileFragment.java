package ca.dal.bartertrader.presentation.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Date;

import ca.dal.bartertrader.R;
import ca.dal.bartertrader.data.data_source.FirebaseAuthDataSource;
import ca.dal.bartertrader.data.data_source.FirebaseFirestoreDataSource;
import ca.dal.bartertrader.data.repository.FirebaseUserRepository;

public class ProfileFragment extends Fragment {
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;
    private FirebaseAuthDataSource authDataSource;
    private FirebaseFirestoreDataSource firestoreDataSource;
    private FirebaseUserRepository userRepository;
    private TextView displayName;
    private TextView dateJoined;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        authDataSource = new FirebaseAuthDataSource(mAuth);
        firestoreDataSource = new FirebaseFirestoreDataSource(mFirestore);
        userRepository = new FirebaseUserRepository(authDataSource, firestoreDataSource);

        displayName = getView().findViewById(R.id.profile_fragment_text_username);
        dateJoined = getView().findViewById(R.id.profile_fragment_text_joinDate);

        FirebaseUser user = userRepository.getUser();

        if (user != null) {
            displayName.setText(user.getDisplayName());
            long timestamp = user.getMetadata().getCreationTimestamp();
            Date d = new Date(timestamp);
            Log.d("DATE", d.toString());
            dateJoined.setText(d.toString());
        }
    }
}

