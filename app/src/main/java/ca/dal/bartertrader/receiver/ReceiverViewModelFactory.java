package ca.dal.bartertrader.receiver;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.firestore.FirebaseFirestore;

public class ReceiverViewModelFactory implements ViewModelProvider.Factory {

    /*private UserFirebaseFirestoreRepository repoRef;*/;

    public ReceiverViewModelFactory(/*UserFirebaseFirestoreRepository repoRef*/)
    {
        //this.firestoreRef = repoRef;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(ReceiverViewModel.class))
        {
            return (T) new ReceiverViewModel(/*repoRef*/);
        }
        throw new IllegalArgumentException("ViewModel Not Found.");
    }
}
