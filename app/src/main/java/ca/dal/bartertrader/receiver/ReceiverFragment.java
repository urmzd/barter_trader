package ca.dal.bartertrader.receiver;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import ca.dal.bartertrader.R;

public class ReceiverFragment extends Fragment {

    private ReceiverViewModel mViewModel;

    public static ReceiverFragment newInstance() {
        return new ReceiverFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.receiver_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //TODO: remove the placeholder for repo class below, only here for testing
        mViewModel = new ViewModelProvider(requireActivity(), new ReceiverViewModelFactory(FirebaseFirestore.getInstance()))
                .get(ReceiverViewModel.class);
    }
}
