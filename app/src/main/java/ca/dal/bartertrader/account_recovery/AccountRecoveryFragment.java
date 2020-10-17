package ca.dal.bartertrader.account_recovery;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.dal.bartertrader.R;

public class AccountRecoveryFragment extends Fragment {

    private AccountRecoveryViewModel mViewModel;

    public static AccountRecoveryFragment newInstance() {
        return new AccountRecoveryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.account_recovery_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AccountRecoveryViewModel.class);
        // TODO: Use the ViewModel
    }

}