package ca.dal.bartertrader.repository;

import java.net.URI;

public class User {
    private String uid;
    private String displayName;
    private String email;

    private URI photoUrl;

    private boolean verified;
    private boolean provider;
    private boolean anonymous;

    public User(String uid, String displayName, String email, URI photoUrl, boolean verified, boolean provider, boolean anonymous) {
        this.uid = uid;
        this.displayName = displayName;
        this.email = email;
        this.photoUrl = photoUrl;
        this.verified = verified;
        this.provider = provider;
        this.anonymous = anonymous;
    }

}
