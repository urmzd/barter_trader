package ca.dal.bartertrader;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import ca.dal.bartertrader.repository.user.UserAuth;
import ca.dal.bartertrader.repository.user.UserRepository;

public class BarterTraderApplicationContainer {
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private UserAuth userAuth = new UserAuth(firebaseAuth);

    public UserRepository userRepository = new UserRepository(userAuth);
}
