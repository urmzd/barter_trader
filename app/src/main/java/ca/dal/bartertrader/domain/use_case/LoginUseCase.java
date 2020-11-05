package ca.dal.bartertrader.domain.use_case;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ca.dal.bartertrader.domain.repository.LoginRepository;

public class LoginUseCase {
    private LoginRepository loginRepository;

    public LoginUseCase (FirebaseAuth mAuth) {
        loginRepository = new LoginRepository(mAuth);
    }
    public void execute(String email, String password, MutableLiveData<FirebaseUser> user) {
        loginRepository.login(email, password, user);
    }
}
