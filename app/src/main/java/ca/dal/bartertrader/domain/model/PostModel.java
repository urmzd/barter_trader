package ca.dal.bartertrader.domain.model;

import android.net.Uri;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.io.Serializable;

import ca.dal.bartertrader.utils.CategoryEnum;

@IgnoreExtraProperties
public class PostModel implements Serializable {

    private String authUid;
    private Uri image;
    private String title;
    private String description;
    private String imageName;
    private CategoryEnum category;

    private String postId;

    @Exclude
    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    private double lat;
    private double lon;

    @ServerTimestamp
    private Timestamp timestamp;

    public PostModel() {

    }

    public PostModel(Uri image, String title, String description, double lat, double lon, CategoryEnum category) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.lat = lat;
        this.lon = lon;
        this.category = category;
        this.timestamp = null;
        this.authUid = null;
    }

    @Exclude
    public Uri getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getLat() { return lat; }

    public double getLon() { return lon; }

    public void setImage(Uri image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getAuthUid() {
        return authUid;
    }

    public CategoryEnum getCategory() { return category; }

    public void setAuthUid(String authUid) {
        this.authUid = authUid;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
