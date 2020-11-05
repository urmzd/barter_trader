package ca.dal.bartertrader.presentation.view_model;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ca.dal.bartertrader.domain.use_case.LoginUseCase;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<Integer> destID = new MutableLiveData<>();
    private MutableLiveData<FirebaseUser> user = new MutableLiveData<>();
    public final MutableLiveData<String> email = new MutableLiveData<>();
    public final MutableLiveData<String> password = new MutableLiveData<>();

    private final LiveData<Boolean> emailIsInvalid = Transformations.map(email, email -> {
        return email == null || email.isEmpty();
    });

    public LiveData<Boolean> getEmailIsInvalid() {
        return emailIsInvalid;
    }

    private final LiveData<Boolean> passwordIsInvalid = Transformations.map(password, password -> {
        return password == null || password.isEmpty();
    });

    public LiveData<Boolean> getPasswordIsInvalid() {
        return passwordIsInvalid;
    }

    @BindingAdapter("email")
    public void setEmail(TextInputEditText view, String email) {
        if (!view.getText().toString().equals(email)) {
            this.email.setValue(email);
        }
    }

    @InverseBindingAdapter(attribute = "email")
    public MutableLiveData<String> getEmail(TextInputEditText view) {
        return email;
    }

    @BindingAdapter("password")
    public void setPassword(TextInputEditText view, String password) {
        if (!view.getText().toString().equals(password)) {
            this.password.setValue(password);
        }
    }

    @InverseBindingAdapter(attribute = "password")
    public MutableLiveData<String> getPassword(TextInputEditText view) {
        return password;
    }

    public LiveData<FirebaseUser> getUser() {
        return user;
    }

    public void login() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        LoginUseCase useCase = new LoginUseCase(mAuth);

        if (email.getValue() == null || password.getValue() == null) {
            user.setValue(null);
        }
        else if (!emailIsInvalid.getValue() && !passwordIsInvalid.getValue()) {
            //Log.d("Login", "sent to firebase");
            useCase.execute(email.getValue(), password.getValue(), user);
        }

        else {
            user.setValue(null);
        }
    }

    //@BindingAdapter("destID")
    public void navigate(int destID) {
        this.destID.setValue(destID);
    }

    //@InverseBindingAdapter(attribute = "destID")
    public MutableLiveData<Integer> getDestID() {
        return destID;
    }
}
