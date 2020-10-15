package ca.dal.bartertrader;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class LogInViewModelFactory implements ViewModelProvider.Factory{
    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LogInViewModel.class)) {
            return (T) new LogInViewModel(LogInRepository.getInstance(new LogInFirebase()));
        }
        else
            throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
