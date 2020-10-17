package ca.dal.bartertrader.receiver;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import ca.dal.bartertrader.Item;

public class ReceiverViewModel extends ViewModel {

    private static String COLLECTIOIN_TAG = "testCollection";
    private static String DOCUMENT_TAG ="testDocument";

    //TODO: refactor after repository is in
    //prob have an instance of the repository here
    //private UserFirebaseFirestoreRepository mUserRepo;
    //for now I"m using place holders to test everything is connected

    private FirebaseFirestore db;

    private static String ITEM_TAG = "item";
    //private MutableLiveData</*Item to be passed*/> mItemMutableLiveData;

    public ReceiverViewModel(/*UserFirebaseFirestoreRepository repoRef*/)
    {

        //if(mItemMutableLiveData != null)
        //{
        //    return;
        //}
        //mUserRepo.UserFirebaseFirestoreRepository.getInstance();
        //mItemMutableLiveData = mUserRepo.getItems();

        //TODO: remove the below placeholder

        //this.db = repoRef;
       // mItemMutableLiveData = repoRef.getRelevantData();
        //TODO: remove ^^^
    }

/*    public LiveData<*//*Item to be passed*//*> getData()
    {
        return mItemMutableLiveData;
    }*/

}
