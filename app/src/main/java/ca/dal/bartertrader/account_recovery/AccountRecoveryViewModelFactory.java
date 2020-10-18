package ca.dal.bartertrader.account_recovery;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class AccountRecoveryViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(AccountRecoveryViewModel.class))
        {
            return (T) new AccountRecoveryViewModel(/*repoRef*/);
        }
        throw new IllegalArgumentException("ViewModel Not Found.");

    }
}
