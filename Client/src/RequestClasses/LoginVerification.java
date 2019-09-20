package RequestClasses;

import DataClasses.ClientDetails;

import java.io.Serializable;

public class LoginVerification implements Serializable {

    private String loginCredential;
    private String password;
    private int mode;
    private boolean isLoginSuccessful;
    private ClientDetails clientDetails;
    private String message;

    public LoginVerification(boolean isLoginSuccessful, String message){
        this.isLoginSuccessful = isLoginSuccessful;
        this.message = message;
    }

    public LoginVerification(boolean isLoginSuccessful, ClientDetails clientDetails, String message){
        this.isLoginSuccessful = isLoginSuccessful;
        this.clientDetails = clientDetails;
        this.message = message;
    }

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

    public ClientDetails getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(ClientDetails clientDetails) {
        this.clientDetails = clientDetails;
    }

    public boolean isLoginSuccessful() {
        return isLoginSuccessful;
    }

    public void setLoginSuccessful(boolean loginSuccessful) {
        isLoginSuccessful = loginSuccessful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
