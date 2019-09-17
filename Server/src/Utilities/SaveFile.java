package Utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class SaveFile {

    byte[] content;
    String userID;
    String destination;
    String extension;

    public SaveFile(byte[] content, String userID, String destination, String extension) {
        this.content = content;
        this.userID = userID;
        this.destination = destination;
        this.extension = extension;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public void save(){
        File destinationFile = new File(destination+"/"+userID+"."+extension);
        try {
            Files.write(destinationFile.toPath(), content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}