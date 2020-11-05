package ca.dal.bartertrader.domain.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginRepository {
    private FirebaseAuth mAuth;

    public LoginRepository(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }

    public void login(String email, String password, MutableLiveData<FirebaseUser> user) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    user.setValue(currentUser);
                } else {
                    user.setValue(null);
                }
            }
        });
    }
}
