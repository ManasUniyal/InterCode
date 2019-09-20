package RequestClasses;

import java.io.Serializable;

public class UploadQuestion implements Serializable {

    private int type;
    private String group;
    private byte[] content;

    public UploadQuestion(int type, String group, byte[] content) {
        this.type = type;
        this.group = group;
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

}
