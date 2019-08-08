package dao;

import constants.DBConstants;
import model.User;
import service.EncryptionSaltAndPassword;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Base64;

public class UpdateUserInfoDao {

    private Connection connection;

    public UpdateUserInfoDao() {
        try {
            this.connection = DriverManager.getConnection(DBConstants.URL, DBConstants.USERNAME, DBConstants.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean update(User user) {
        try {
            byte[] saltBytes = EncryptionSaltAndPassword.generateSalt();
            byte[] passwordBytes = EncryptionSaltAndPassword.getEncryptedPassword(user.getPassword(), saltBytes);
            String salt = Base64.getEncoder().encodeToString(saltBytes);
            String password = Base64.getEncoder().encodeToString(passwordBytes);
            String query = "UPDATE quiz_users SET email_id = ?, password = ?, phone_number = ?, gender = ?, date_of_birth = ?, salt = ? WHERE username = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            int i = 1;
            pst.setString(i++, user.getEmailId());
            pst.setString(i++, password);
            pst.setString(i++, user.getPhoneNumber());
            pst.setString(i++, user.getGender().name());
            pst.setDate(i++, new Date(user.getDateOfBirth().getTime()));
            pst.setString(i++, salt);
            pst.setString(i++, user.getUserName());
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

    public boolean updateImage(String imagePath, String username) {
        try {
            String query = "UPDATE quiz_users SET image_url = ? WHERE username = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            int i = 1;
            pst.setString(i++, imagePath);
            pst.setString(i++, username);
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
