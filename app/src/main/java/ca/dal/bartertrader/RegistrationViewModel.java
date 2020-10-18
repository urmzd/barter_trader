package ca.dal.bartertrader;

import androidx.lifecycle.ViewModel;

//private userRepository;

public class RegistrationViewModel extends ViewModel {

    RegistrationViewModel(/**UserRepository userRepository**/)
    {
        /**this.userRepository = userRepository;**/
    }

    public void registerUser( String email, String password ) {}

    private void validateRegistration() {};

    private boolean isEmailValid() { return false; };

    private boolean isUserNameValid() { return false; };

    private boolean isPasswordValid() {return false; };

    private boolean isEmailRegistered() { return false; }

}
