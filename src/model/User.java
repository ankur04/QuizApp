package model;

import java.io.File;
import java.util.Date;

public class User {
    private String userName;
    private String emailId;
    private String password;
    private String phoneNumber;
    private Gender gender;
    private Date dateOfBirth;
    private Date lastLoggedIn;
    private String imageUrl;
    private File image;
    private Character proVersion;

    public User() {

    }

    public User(String userName, String emailId, String password, String phoneNumber, Gender gender, Date dateOfBirth) {
        this.userName = userName;
        this.emailId = emailId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public User(String userName, String emailId, String password, String phoneNumber, Gender gender, Date dateOfBirth, Date lastLoggedIn, String imageUrl, File image, Character proVersion) {
        this.userName = userName;
        this.emailId = emailId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.lastLoggedIn = lastLoggedIn;
        this.imageUrl = imageUrl;
        this.image = image;
        this.proVersion = proVersion;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getLastLoggedIn() {
        return lastLoggedIn;
    }

    public void setLastLoggedIn(Date lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public Character getProVersion() {
        return proVersion;
    }

    public void setProVersion(Character proVersion) {
        this.proVersion = proVersion;
    }
}
