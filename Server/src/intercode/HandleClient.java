package intercode;

import RequestClasses.*;
import Utilities.SaveFile;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HandleClient implements Runnable {

    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private Object message;

    public HandleClient(Socket socket) {
        this.socket = socket;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                message = objectInputStream.readObject();
                objectOutputStream.writeObject(process());
                objectOutputStream.flush();
            } catch (IOException e) {
//                e.printStackTrace();
            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
            }

        }
    }

    private Object process() {

        if (message instanceof UploadResume) {
            return _uploadResume();
        } else if(message instanceof LoginVerification){
            return _loginVerification();
        } else if(message instanceof CheckUserID){
            return _checkUserID();
        } else if(message instanceof SignUpRequest){
            return _signUpRequest();
        }
        return new Object();
    }

    private Response _loginVerification() {
        LoginVerification loginVerification = (LoginVerification) message;
        String loginCredential = loginVerification.getLoginCredential();
        String password = loginVerification.getPassword();
        int mode = loginVerification.getMode();
        try {
            boolean flag = false;
            ResultSet rs = Main.SQLQUERYEXECUTER.select("SELECT * FROM user WHERE (((UserID = '" + loginCredential + "'" + " AND Password = " + "'" + password + "') OR (EmailAddress = '" + loginCredential + "' AND Password = '" + password + "')) AND Mode = "+mode+");");
            while (rs.next()){
                flag = true;
            }
            System.out.println(flag);
            if(flag == true){
                return new Response(0,"Login successful");
            } else {
                return new Response(1,"Invalid login credentials");
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return new Response(1,"SQL Query error");
        }
    }

    private Object _uploadResume() {
        UploadResume uploadResume = (UploadResume) message;
        new File("src/Resume").mkdir();
        new SaveFile(uploadResume.getContent(), uploadResume.getUserID(), "src/Resume", uploadResume.getExtension()).save();
        return new Response(0,"Resume uploaded");
    }


    private Response _checkUserID(){
        CheckUserID checkUserID = (CheckUserID) message;
        boolean flag = false;
        try {
            ResultSet rs = Main.SQLQUERYEXECUTER.select("SELECT * FROM user WHERE UserID = '" + checkUserID.getUserID() + "';");
            while (rs.next()) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(!flag) {
            return new Response(0, "Successful");
        } else{
            return new Response(1,"User ID already exists");
        }
    }

    private Response _signUpRequest() {
        SignUpRequest signUpRequest = (SignUpRequest) message;
        Main.SQLQUERYEXECUTER.update("INSERT INTO user VALUES ('"+signUpRequest.getUserName()+"','"+signUpRequest.getUserID()+"','"+signUpRequest.getPhoneNumber()+"','"+signUpRequest.getEmailAddress()+"','"+signUpRequest.getExtension()+"','"+signUpRequest.getPassword()+"',"+signUpRequest.getMode()+");");
        String destinationDir = "src/ProfilePictures";
        new File(destinationDir).mkdir();
        File destinationFile = new File(destinationDir+"/"+signUpRequest.getUserID()+"."+signUpRequest.getExtension());
        try {
            Files.write(destinationFile.toPath(), signUpRequest.getPictureContent());
            return new Response(0,"");
        } catch (IOException e) {
            e.printStackTrace();
            return new Response(1,"Error in saving profile picture");
        }
    }


}
