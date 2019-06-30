package model;

public class Login {

    String username;
    String emailId;
    String salt;
    String encryptedPassword;

    public Login() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    @Override
    public String toString() {
        return "Login{" +
                "username='" + username + '\'' +
                ", emailId='" + emailId + '\'' +
                ", salt='" + salt + '\'' +
                ", encryptedPassword='" + encryptedPassword + '\'' +
                '}';
    }
}
