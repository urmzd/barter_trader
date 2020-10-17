package ca.dal.bartertrader.receiver;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReceiverViewModel extends ViewModel {

    private static String COLLECTIOIN_TAG = "testCollection";
    private static String DOCUMENT_TAG ="testDocument";

    //TODO: refactor after repository is in
    //prob have an instance of the repository here
    //private UserFirebaseFirestoreRepository mUserRepo;
    //for now I"m using place holders to test everything is connected

    private static String ITEM_TAG = "item";
    //private MutableLiveData</*Item to be passed*/> mItemMutableLiveData;

    public ReceiverViewModel(/*UserFirebaseFirestoreRepository repoRef*/)
    {

        //if(mItemMutableLiveData != null)
        //{
        //    return;
        //}
        //mUserRepo = UserFirebaseFirestoreRepository.getInstance();
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
