package ca.dal.bartertrader.presentation.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

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

        FirebaseUser user = userRepository.getUser();
        displayName.setText(user.getDisplayName());
    }
}
