package dao;

import constants.DBConstants;
import main.Main;
import model.Gender;
import model.Login;
import model.User;

import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class LoginDao {

    private Connection connection;

    public LoginDao() {
        try {
            this.connection = DriverManager.getConnection(DBConstants.URL, DBConstants.USERNAME, DBConstants.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Login checkUserNameOrEmailId(String name, String valueForQuery) {
        Login user = new Login();
        User user2 = new User();
        try {
            String query = "SELECT * FROM quiz_users WHERE " + valueForQuery + "=?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, name);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                user.setEncryptedPassword(resultSet.getString("password"));
                user.setSalt(resultSet.getString("salt"));
                user2.setUserName(resultSet.getString("username"));
                user2.setEmailId(resultSet.getString("email_id"));
                user2.setPhoneNumber(resultSet.getString("phone_number"));
                user2.setGender(Gender.valueOf(resultSet.getString("gender")));
                user2.setDateOfBirth(getDateTime(resultSet.getDate("date_of_birth")));
                user2.setLastLoggedIn(getDateTime(resultSet.getDate("last_logged_in")));
                user2.setSalt(resultSet.getString("salt"));
                user2.setImageUrl(resultSet.getString("image_url"));
                user2.setProVersion(resultSet.getString("pro_version") != null ? 'Y' : null);
                Main.user = user2;
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

    private Date getDateTime(Date date) {
        if (date != null)
            return new Date(date.getTime());
        return null;
    }

    public int updateLastLoginTime(String name, String value) {
        int rows = 0;
        try {
            String query = "UPDATE quiz_users SET last_logged_in=? WHERE " + value + "=?";
            PreparedStatement pst = connection.prepareStatement(query);
            int i = 1;
            Calendar currentDate = new GregorianCalendar();

            pst.setDate(i++, new java.sql.Date(currentDate.getTimeInMillis()));
            pst.setString(i++, name);
            rows = pst.executeUpdate();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return rows;

    }

}
