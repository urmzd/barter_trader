package ca.dal.bartertrader;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.UUID;

public class LogInFirebase {
    static boolean success;
    private LogInResult logInResult;

    public boolean task(final FirebaseAuth mAuth, String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            success = true;
                            logInResult = new LogInResult(success);
                        }

                        else {
                            logInResult = new LogInResult("Invalid user name or password");
                            success = false;
                        }
                    }
                });
        return success;
    }
}
