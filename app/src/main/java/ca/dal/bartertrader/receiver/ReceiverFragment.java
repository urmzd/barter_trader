package ca.dal.bartertrader.receiver;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ca.dal.bartertrader.R;

public class ReceiverFragment extends Fragment {

    private ReceiverViewModel mViewModel;

    public static ReceiverFragment newInstance() {
        return new ReceiverFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //set conditions here that you're logged in and in receiver role
        boolean isLoggedIn = true;
        if(isLoggedIn)
        {
            return inflater.inflate(R.layout.receiver_logged_in_fragment,container,false);
        }
        else
        {
            return inflater.inflate(R.layout.receiver_not_logged_in_fragment,container,false);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //TODO: remove the placeholder for repo class below, only here for testing
        mViewModel = new ViewModelProvider(requireActivity(), new ReceiverViewModelFactory(/*repository ref*/))
                .get(ReceiverViewModel.class);
    }
}
