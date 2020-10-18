package ca.dal.bartertrader;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ProviderViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(ProviderViewModel.class)) {
            return (T) new ProviderViewModel(UserRepository.getInstance());
        }else{
            throw new IllegalArgumentException("Uknown");
        }
    }
}
