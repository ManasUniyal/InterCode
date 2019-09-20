package RequestClasses;

import java.io.Serializable;

public class UpdateDetails implements Serializable {

    private String userID;
    private String userName;
    private String emailAddress;
    private String phoneNumber;
    private byte[] content;
    private String resumeExtension;

    public UpdateDetails(String userID, String userName, String emailAddress, String phoneNumber, byte[] content, String resumeExtension) {
        this.userID = userID;
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.content = content;
        this.resumeExtension = resumeExtension;
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

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getResumeExtension() {
        return resumeExtension;
    }

    public void setResumeExtension(String resumeExtension) {
        this.resumeExtension = resumeExtension;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }


}
