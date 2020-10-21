package ca.dal.bartertrader.registration;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import android.text.TextUtils;
import android.util.Patterns;

import com.google.android.material.textfield.TextInputLayout;

import ca.dal.bartertrader.SingleEvent;
//private userRepository;


public class RegistrationViewModel extends ViewModel {

    public RegistrationViewModel(/**UserRepository userRepository**/) {
        /**this.userRepository = userRepository;**/
    }

    private final MutableLiveData<String> email = new MutableLiveData<>();
    private final LiveData<Boolean> showEmailError = Transformations.map(email, email ->
    {
        if (TextUtils.isEmpty(email) || email == null) {
            return false;
        }
        return !isEmailValid(email);
    });
    private final MutableLiveData<SingleEvent<Boolean>> sendRegistrationEmailEvent = new MutableLiveData<>();

    public void registerUser( String email, String username, String password ) {
        boolean validRegistration = validateRegistration(email, username, password);

        boolean registrationResult = false;
        if (validRegistration)
        {
            //registrationResult = userRepository.registerAnonymously(email, username, password);
        }
        if (validRegistration && registrationResult)
        {
            // set live registration result callback to be a success
            sendConfirmationEmail();
        }
        else
        {
            // fire back an error with registration.
        }
    }

    public void registrationDataChanged(String email, String username, String password)
    {
        if (!isEmailValid(email))
        {
            /** set live data to notify an invalid email **/
        }
        else if (!isUserNameValid(username))
        {
            /** set live data to notify an invalid user **/
        }
        else if (!isPasswordValid(password))
        {
            /** set live data to notify an invalid password **/
        }
    }

    public void sendConfirmationEmail() {
        /**TODO: send email confirmation after a valid registration **/
    };

    private boolean validateRegistration(String email, String username, String password) {
        boolean validRegistration = isEmailValid(email)         &&
                                    isUserNameValid(username)   &&
                                    isPasswordValid(password)   &&
                                    !isEmailRegistered(email);
        return validRegistration;
    };

    // Validates a potential email, does not check if it is already registered.
    private boolean isEmailValid(String email) {
        boolean emailPatternCheck = Patterns.EMAIL_ADDRESS.matcher(email).matches();
        if (!email.isEmpty() && emailPatternCheck)
        {
            return true;
        }
        return false;
    };

    // Validates the username given for registration.
    private boolean isUserNameValid(String username) {
        return !username.isEmpty();
    };

    // Validates the password given for registration.
    private boolean isPasswordValid(String password) {
        return !password.isEmpty();
    };

    // Checks firebase to see if this email already belongs to a user.
    private boolean isEmailRegistered(String email) {
        boolean exists = true;
        //exists = userRepository.emailExists(email);
        return exists;
    }

    public MutableLiveData<String> getEmail() {
        return this.email;
    }

    public LiveData<Boolean> getShowEmailError() { return this.showEmailError; }

    public LiveData<SingleEvent<Boolean>> getSendRegistrationEmailEvent() {
        return this.sendRegistrationEmailEvent;
    }

    @BindingAdapter("email")
    public void setEmail(TextInputLayout view, String currentEmail)
    {
        this.email.setValue(currentEmail);
    }

    public void sendRegistrationEmailEvent() {
        sendRegistrationEmailEvent.setValue(new SingleEvent<>(true));
    }
}
