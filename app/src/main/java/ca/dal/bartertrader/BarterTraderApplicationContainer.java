package ca.dal.bartertrader;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import ca.dal.bartertrader.repository.UserRepository;

public class BarterTraderApplicationContainer {
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

    public UserRepository userRepository = new UserRepository(firebaseAuth, firebaseFirestore, firebaseStorage);
}
