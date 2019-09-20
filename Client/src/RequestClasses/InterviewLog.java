package RequestClasses;

import DataClasses.InterviewDetails;

import java.io.Serializable;
import java.util.ArrayList;

public class InterviewLog implements Serializable {

    private String userID;
    private int mode;
    private ArrayList<InterviewDetails> details;

    public InterviewLog(String userID, int mode) {
        this.userID = userID;
        this.mode = mode;
        this.details = details;
    }

    public InterviewLog(String userID, int mode, ArrayList<InterviewDetails> arrayList) {
        this.userID = userID;
        this.mode = mode;
        this.details = arrayList;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public ArrayList<InterviewDetails> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<InterviewDetails> details) {
        this.details = details;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }



}
