package ca.dal.bartertrader;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class ProviderViewModel extends ViewModel {
    private UserRepository userRepository;

    public ProviderViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LiveData<User> getUserRole() {
//        return Transformations.map(userRepository.getUser(), User::getRole);
        return userRepository.getUser();
    }

    public void switchRoles() {
        userRepository.switchRole();
    }

}

class User {
    private String id;
    private String email;
    private UserRoles userRole;

    public User(String id, String email, UserRoles userRole) {
        this.id = id;
        this.email = email;
        this.userRole = userRole;
    }

    public UserRoles getRole() {
        return this.userRole;
    }

}

class UserRepository {
    private static UserRepository instance;
    private static MutableLiveData<User> user = new MutableLiveData<>();

    public static UserRepository getInstance() {
        if(instance == null) {
            instance = new UserRepository();
        }return instance;
    }

    public void setUser(User user) {
        this.user.postValue(user);
    }

    public LiveData<User> getUser() {
        return user;
    }

    public LiveData<User> switchRole() {
        user.setValue(new User("1", "elias@gmail.com", UserRoles.RECEIVER));
        return user;
    }

}

enum UserRoles {
    RECEIVER,
    PROVIDER
}