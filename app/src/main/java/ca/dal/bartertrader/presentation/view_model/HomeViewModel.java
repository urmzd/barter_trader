package ca.dal.bartertrader.presentation.view_model;


import android.util.Log;


import androidx.core.util.Pair;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import androidx.paging.PagedList;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.PropertyName;

import java.util.ArrayList;

import ca.dal.bartertrader.R;
import ca.dal.bartertrader.data.model.Post;

public class HomeViewModel extends ViewModel {
    private static final String TAG = "HomeViewModel";

    private MutableLiveData<ArrayList<Post>> items = new MutableLiveData<>();
    private MutableLiveData<Boolean> isProvider = new MutableLiveData<>();

    private PostRepo postRepo = new PostRepo();


    public HomeViewModel() {
        items.setValue(postRepo.getItems());

    }

    public LiveData<ArrayList<Post>> getItems() {
        return items;
    }


    public void deleteItem(Post post) {
        items.getValue().remove(post);
        // Remove from database
    }

    public LiveData<Boolean> getIsProvider() {
        return isProvider;
    }


    public void switchRole() {
//        isProvider = repo.switchRoles()
        Log.d("ViewModel", "SWITCH ROLES");
    }

    public void setPreferences(ArrayList<String> preferences) {
        for(String s : preferences) {
            Log.d(TAG, s);
        }
    }

    public void sendSearchQuery(Pair<String, String> query) {
        Log.d(TAG, query.second);
    }


}


class PostRepo {
    public ArrayList<Post> getItems() {
        ArrayList<Post> result = new ArrayList<>();
        Post post;
        for(int i = 0; i < 8; i++) {
            post = new Post(
                    "Lorem Ipsum is simply dummy text of the printing " + i,
                    "Lorem Ipsum is simply dummy text of the printing and typesetting " +
                            "industry. Lorem Ipsum has been the industry's standard dummy text ever since the " +
                            "1500s, when an unknown printer took a galley of type and scrambled it to make a " +
                            "type specimen book.",
                    "Halifax, Nova Scotia, Canada",
                    R.drawable.placeholder);
            post.setId(i);
            if (i % 2 == 0) {
                post.setLocation("Dartmouth, Nova Scotia, Canada");
            }
            result.add(post);
        }return result;
    }
}




