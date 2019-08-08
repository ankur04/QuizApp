package dao;

import constants.DBConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpgradeDao {

    private Connection connection;

    public UpgradeDao() {
        try {
            this.connection = DriverManager.getConnection(DBConstants.URL, DBConstants.USERNAME, DBConstants.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean upgrade(String userName) {
        int rows = 0;
        try {
            String query = "UPDATE quiz_users SET pro_version='Y' WHERE username=?";
            PreparedStatement pst = connection.prepareStatement(query);
            int i = 1;
            pst.setString(i++, userName);
            rows = pst.executeUpdate();
            if (rows > 0)
                return true;
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return false;
    }
}
