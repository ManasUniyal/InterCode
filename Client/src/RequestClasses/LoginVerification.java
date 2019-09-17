package RequestClasses;

import java.io.Serializable;

public class LoginVerification implements Serializable {

    private String loginCredential;
    private String password;
    private int mode;

    public LoginVerification(String loginCredential, String password, int mode) {
        this.loginCredential = loginCredential;
        this.password = password;
        this.mode = mode;
    }

    public String getLoginCredential() {
        return loginCredential;
    }

    public void setLoginCredential(String loginCredential) {
        this.loginCredential = loginCredential;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

}
