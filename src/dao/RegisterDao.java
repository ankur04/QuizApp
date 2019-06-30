package dao;

import constants.DBConstants;
import model.User;
import service.EncryptionSaltAndPassword;

import java.sql.*;
import java.util.Base64;

public class RegisterDao {

    private Connection connection;

    public RegisterDao() {
        try {
            this.connection = DriverManager.getConnection(DBConstants.URL, DBConstants.USERNAME, DBConstants.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean register(User user) {

        try {
            byte[] saltBytes = EncryptionSaltAndPassword.generateSalt();
            byte[] passwordBytes = EncryptionSaltAndPassword.getEncryptedPassword(user.getPassword(), saltBytes);
            String salt = Base64.getEncoder().encodeToString(saltBytes);
            String password = Base64.getEncoder().encodeToString(passwordBytes);
            String query = "INSERT INTO quiz_users (username,email_id,password,phone_number,gender,date_of_birth,salt) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(query);
            int i = 1;
            pst.setString(i++, user.getUserName());
            pst.setString(i++, user.getEmailId());
            pst.setString(i++, password);
            pst.setString(i++, user.getPhoneNumber());
            pst.setString(i++, user.getGender().name());
            pst.setDate(i++, new Date(user.getDateOfBirth().getTime()));
            pst.setString(i++, salt);
            int rows = pst.executeUpdate();
            if (rows > 0)
                return true;
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }


}
