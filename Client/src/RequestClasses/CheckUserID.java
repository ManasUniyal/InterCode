package RequestClasses;

import java.io.Serializable;

public class CheckUserID implements Serializable {

    private String userID;

    public CheckUserID(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

}
