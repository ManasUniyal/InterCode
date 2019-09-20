package DataClasses;

import java.io.Serializable;

public class InterviewDetails implements Serializable {

    private String userDetails;
    private String time;
    private String duration;
    private String rating;
    private String review;

    public InterviewDetails(String userDetails, String time, String duration) {
        this.userDetails = userDetails;
        this.time = time;
        this.duration = duration;
    }

    public InterviewDetails(String userDetails, String time, String duration, String rating, String review) {
        this.userDetails = userDetails;
        this.time = time;
        this.duration = duration;
        this.rating = rating;
        this.review = review;
    }

    public String getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(String userDetails) {
        this.userDetails = userDetails;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

}