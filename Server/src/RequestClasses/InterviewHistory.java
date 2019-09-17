package RequestClasses;

import DataClasses.ClientDetails;

import java.io.Serializable;
import java.util.ArrayList;

public class InterviewHistory implements Serializable {

    private String userID;
    private ArrayList<ClientDetails> arrayList;

    public InterviewHistory(String userID) {
        this.userID = userID;
    }

    public InterviewHistory(String userID, ArrayList<ClientDetails> arrayList){
        this.userID = userID;
        this.arrayList = arrayList;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public ArrayList<ClientDetails> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<ClientDetails> arrayList) {
        this.arrayList = arrayList;
    }

}
