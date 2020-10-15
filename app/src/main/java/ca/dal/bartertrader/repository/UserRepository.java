package ca.dal.bartertrader.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.storage.FirebaseStorage;

public class UserRepository {

    private final FirebaseAuth firebaseAuth;
    private final FirebaseFirestore firebaseFirestore;
    private final FirebaseStorage firebaseStorage;

    private final MutableLiveData<User> user = new MutableLiveData<>();

    public UserRepository(FirebaseAuth firebaseAuth, FirebaseFirestore firebaseFirestore, FirebaseStorage firebaseStorage) {
        this.firebaseAuth = firebaseAuth;
        this.firebaseFirestore = firebaseFirestore;
        this.firebaseStorage = firebaseStorage;
    }

    public LiveData<User> getUser() {
        if (this.user == null) {
            return new MutableLiveData<>();
        }

        return user;
    }

    public void register(User user) {
    }

    public void registerAnonymously() {

    }

    public void login(String email, String password) {
    }

    public void resetPassword(String email) {
    }

    public void switchRoles() {
    }

    public boolean emailExists(String email) {
        return false;
    }

    public boolean emailVerified(String email) {
        return false;
    }

}
