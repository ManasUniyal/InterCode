package DataClasses;

import java.io.Serializable;

public class ClientDetails implements Serializable {

    private String userID;
    private String userName;
    private String emailAddress;
    private String phoneNumber;
    private String imageExtension;
    private String resumeExtension;
    private int mode;

    public ClientDetails(){
    }

    public ClientDetails(String userID, String userName, String emailAddress, String phoneNumber, String imageExtension, String resumeExtension, int mode) {
        this.userID = userID;
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.imageExtension = imageExtension;
        this.resumeExtension = resumeExtension;
        this.mode = mode;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getImageExtension() {
        return imageExtension;
    }

    public void setImageExtension(String imageExtension) {
        this.imageExtension = imageExtension;
    }

    public String getResumeExtension() {
        return resumeExtension;
    }

    public void setResumeExtension(String resumeExtension) {
        this.resumeExtension = resumeExtension;
    }



}
