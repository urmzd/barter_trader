package ca.dal.bartertrader;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInViewModel extends ViewModel {
    private MutableLiveData<LogInResult> result = new MutableLiveData<>();
    private LogInRepository repository;

    LogInViewModel(LogInRepository repository) {
        this.repository = repository;
    }

    public LiveData<LogInResult> getResult() {
        return result;
    }

    public void task(FirebaseAuth mAuth, String email, String password) {
        boolean taskSuccess = repository.task(mAuth, email, password);
        if (taskSuccess) {
           result.setValue(new LogInResult(taskSuccess));
        }
        else {
            result.setValue(new LogInResult("Invalid user name or password"));
        }
    }
}
