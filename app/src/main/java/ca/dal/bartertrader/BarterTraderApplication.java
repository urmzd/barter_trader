package ca.dal.bartertrader;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import ca.dal.bartertrader.repository.UserRepository;

public class BarterTraderApplication extends Application {

    public BarterTraderApplicationContainer barterTraderApplicationContainer = new BarterTraderApplicationContainer();

}
