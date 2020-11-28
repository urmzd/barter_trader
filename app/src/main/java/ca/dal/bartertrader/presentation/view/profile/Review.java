package ca.dal.bartertrader.presentation.view.profile;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Review {
    private String displayName;
    private String review;
    private float rating;
    private Date datePosted;

    public Review() {}

    public Review(String name, String review, float rating) {
        displayName = name;
        this.review = review;
        this.rating = rating;
    }

    public String getDisplayName() {return displayName;}
    public void setDisplayName(String name) {displayName = name;}
    public String getReview() {return review;}
    public void setReview(String review) {this.review = review;}
    public float getRating() {return rating;}
    public void setRating(float rating) {this.rating = rating;}

    @ServerTimestamp
    public Date getDatePosted() {return datePosted;}
    public void setDatePosted(Date datePosted) {this.datePosted = datePosted;}
}
