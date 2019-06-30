package dao;

import constants.DBConstants;
import model.Login;

import java.sql.*;

public class LoginDao {

    private Connection connection;

    public LoginDao() {
        try {
            this.connection = DriverManager.getConnection(DBConstants.URL, DBConstants.USERNAME, DBConstants.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Login checkUserNameOrEmailId(String name,String valueForQuery) {
        Login user = new Login();
        try {
            String query = "SELECT * FROM quiz_users WHERE " + valueForQuery + "=?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, name);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                user.setEncryptedPassword(resultSet.getString("password"));
                user.setSalt(resultSet.getString("salt"));
                if (valueForQuery.equalsIgnoreCase("username")) {
                    user.setUsername(name);
                } else {
                    user.setEmailId(name);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

}
