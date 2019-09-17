package RequestClasses;

import java.io.Serializable;

public class UploadResume implements Serializable {

    private String userID;
    private byte[] content;
    private String extension;

    public UploadResume(String userID, byte[] content, String extension) {
        this.userID = userID;
        this.content = content;
        this.extension = extension;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

}
