package ca.dal.bartertrader.dto;

public class UserDTO {
    private final String uid;
    private final String displayName;
    private final String email;

    private final boolean isVerified;
    private final boolean isProvider;

    public UserDTO(String uid, String displayName, String email, boolean verified, boolean provider) {
        this.uid = uid;
        this.displayName = displayName;
        this.email = email;
        this.isVerified = verified;
        this.isProvider = provider;
    }

    public String getUid() {
        return uid;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public boolean isProvider() {
        return isProvider;
    }
}
