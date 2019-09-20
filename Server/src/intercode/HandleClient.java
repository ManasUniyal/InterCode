package intercode;

import DataClasses.ClientDetails;
import DataClasses.InterviewDetails;
import RequestClasses.*;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

        if(message instanceof LoginVerification){
            return _loginVerification();
        } else if(message instanceof CheckUserID){
            return _checkUserID();
        } else if(message instanceof SignUpRequest){
            return _signUpRequest();
        } else if(message instanceof InterviewLog){
            return _interviewLog();
        } else if(message instanceof GetProfilePicture){
            return _getProfilePicture();
        } else if(message instanceof SetProfilePicture){
            return _setProfilePicture();
        } else if(message instanceof GetResume){
            return _getResume();
        } else if(message instanceof UpdateDetails){
            return _updateDetails();
        } else if(message instanceof ChangePassword){
            return _changePassword();
        } else if(message instanceof UploadQuestion){
            return _uploadQuestion();
        }
        return new Object();
    }

    private LoginVerification _loginVerification() {
        LoginVerification loginVerification = (LoginVerification) message;
        String loginCredential = loginVerification.getLoginCredential();
        String password = loginVerification.getPassword();
        int mode = loginVerification.getMode();
        try {
            ResultSet rs = Main.SQLQUERYEXECUTER.select("SELECT * FROM user WHERE (((UserID = '" + loginCredential + "'" + " AND Password = " + "'" + password + "') OR (EmailAddress = '" + loginCredential + "' AND Password = '" + password + "')) AND Mode = "+mode+");");
            ClientDetails clientDetails = new ClientDetails();
            boolean flag = false;
            while (rs.next()){
                flag = true;
                clientDetails = new ClientDetails(rs.getString("UserID"), rs.getString("UserName"), rs.getString("EmailAddress"), rs.getString("PhoneNo"), rs.getString("ImageExtension"), rs.getString("ResumeExtension"), rs.getInt("Mode"));
            }
            return new LoginVerification(flag, clientDetails, flag?"Successful":"Invalid login credentials");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new LoginVerification(false, "Server error");
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
        Main.SQLQUERYEXECUTER.update("INSERT INTO user VALUES ('"+signUpRequest.getUserName()+"','"+signUpRequest.getUserID()+"','"+signUpRequest.getPhoneNumber()+"','"+signUpRequest.getEmailAddress()+"','"+signUpRequest.getExtension()+"','"+signUpRequest.getPassword()+"',"+signUpRequest.getMode()+", NULL);")  ;
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

    private InterviewLog _interviewLog(){
        InterviewLog interviewLog = (InterviewLog) message;
        String userID = interviewLog.getUserID();
        int mode = interviewLog.getMode();
        ArrayList<InterviewDetails> arrayList = new ArrayList<>() ;
        try {
            ResultSet rs = null;
            assert mode!=0 : "Undefined mode";  //Assertion
            if (mode == 1) {  //Interviewer
                rs = Main.SQLQUERYEXECUTER.select("SELECT CandidateName, CandidateEmail, Time, Duration FROM interviewrecord WHERE InterviewerID = '"+userID+"' ORDER BY Rating DESC;");
                while(rs.next()){
                    arrayList.add(new InterviewDetails(rs.getString("CandidateName")+" ("+rs.getString("CandidateEmail") + ")", rs.getString("Time"), rs.getString("Duration"), rs.getString("Rating"), rs.getString("Review")));
                }
            } else if(mode == 2){    //Candidate
                rs = Main.SQLQUERYEXECUTER.select("SELECT InterviewerName, InterviewerEmail, Time, Duration FROM interviewrecord WHERE CandidateID = '"+userID+"';");
                while(rs.next()){
                    arrayList.add(new InterviewDetails(rs.getString("InterviewerName")+" ("+rs.getString("InterviewerEmail") + ")", rs.getString("Time"), rs.getString("Duration")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new InterviewLog(userID, mode, arrayList);
    }

    private GetProfilePicture _getProfilePicture(){
        GetProfilePicture getProfilePicture = (GetProfilePicture) message;
        String userID = getProfilePicture.getUserID();
        String extension = null;
        try {
            ResultSet rs = Main.SQLQUERYEXECUTER.select("SELECT ImageExtension from user WHERE UserID = '" + userID + "';");
            while (rs.next()) {
                extension = rs.getString("ImageExtension");
            }
            File file = new File("src/ProfilePictures/"+userID+"."+extension);
            byte[] content = Files.readAllBytes(file.toPath());
            System.out.println("Sent");
            return new GetProfilePicture(userID, content, extension);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new GetProfilePicture(userID, null, null);
    }

    private Response _setProfilePicture(){
        SetProfilePicture setProfilePicture = (SetProfilePicture) message;
        File destinationFile = new File("src/ProfilePictures/"+setProfilePicture.toString()+"."+setProfilePicture.getExtension());
        try {
            Files.write(destinationFile.toPath(), setProfilePicture.getContent());
            return new Response(0,"Success");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Response(1,"Error in saving Profile Picture");
    }

    private GetResume _getResume(){
        GetResume getResume = (GetResume) message;
        String userID = getResume.getUserID();
        String extension = null;
        byte[] content = null;
        ResultSet rs = Main.SQLQUERYEXECUTER.select("SELECT ResumeExtension FROM user WHERE UserID = '"+userID+"';");
        try{
            while(rs.next()){
                extension = rs.getString("ResumeExtension");
            }
            File destinationFile = new File("src/Resume/"+userID+"."+extension);
            content = Files.readAllBytes(destinationFile.toPath());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new GetResume(userID, content, extension);
    }

    private Response _updateDetails(){
        UpdateDetails updateDetails = (UpdateDetails) message;
        Main.SQLQUERYEXECUTER.update("UPDATE user SET UserName = '"+updateDetails.getUserName()+"', EmailAddress = '"+updateDetails.getEmailAddress()+"', PhoneNo = '"+updateDetails.getPhoneNumber()+"', ResumeExtension = '"+updateDetails.getResumeExtension()+"' WHERE UserID = '"+updateDetails.getUserID()+"';");
        new File("src/Resume").mkdir();
        File resume = new File("src/Resume/"+updateDetails.getUserID()+"."+updateDetails.getResumeExtension());
        try {
            Files.write(resume.toPath(), updateDetails.getContent());
            return new Response(0,"Success");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Response (1,"Unable to update details");
    }

    private Response _changePassword(){
        ChangePassword changePassword = (ChangePassword) message;
        String oldPassword = changePassword.getOldPassword();
        String newPassword = changePassword.getNewPassword();
        String confirmPassword = changePassword.getConfirmPassword();
        if(newPassword.equals(confirmPassword)){
            Main.SQLQUERYEXECUTER.update("UPDATE user SET Password = '"+newPassword+"' WHERE UserID = '"+changePassword.getUserID()+"';");
            return new Response (0,"Successful");
        } else {
            return new Response(1,"Passwords do not match");
        }
    }

    private Response _uploadQuestion(){
        UploadQuestion uploadQuestion = (UploadQuestion) message;
        String group = uploadQuestion.getGroup();
        int type = uploadQuestion.getType();
        byte[] content = uploadQuestion.getContent();
        new File("src/"+group).mkdir();
        String fileName = null;
        if(type==1){
            fileName = "Question";
        } else if(type == 2){
            fileName = "Input";
        } else if(type == 3){
            fileName = "Output";
        } else if(type == 4){
            fileName = "Sample";
        } else if(type == 5){
            fileName = "SampleOutput";
        }
        File destinationFile = new File("src/"+group+"/"+fileName+".txt");
        try {
            Files.write(destinationFile.toPath(), content);
            return new Response(1,"Error");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Response(0,"Success");
    }
}
