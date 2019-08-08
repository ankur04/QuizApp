package model;

import helper.RandomNameGenerator;

import java.io.File;
import java.sql.Blob;
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
    private Blob image;
    private Character proVersion;
    private String salt;

    public User() {
        // TODO : verify if guest id is never used before or generate new id from DB
        this.userName = RandomNameGenerator.getRandomNumberString();
    }

    public User(String userName, String emailId, String password, String phoneNumber, Gender gender, Date dateOfBirth) {
        this.userName = userName;
        this.emailId = emailId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public User(String userName, String emailId, String password, String phoneNumber, Gender gender, Date dateOfBirth, Date lastLoggedIn, String imageUrl, Blob image, Character proVersion) {
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

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public Character getProVersion() {
        return proVersion;
    }

    public void setProVersion(Character proVersion) {
        this.proVersion = proVersion;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", emailId='" + emailId + '\'' +
                '}';
    }
}
