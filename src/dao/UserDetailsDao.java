package dao;

import constants.DBConstants;

import java.sql.*;

public class UserDetailsDao {

    private Connection connection;

    public UserDetailsDao() {
        try {
            this.connection = DriverManager.getConnection(DBConstants.URL, DBConstants.USERNAME, DBConstants.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int fetchNoOfQuiz(String userName) {
        int noOfQuiz = 0;
        try {
            String query = "SELECT COUNT(*) FROM quiz_result WHERE username = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, userName);
            ResultSet resultSet = pst.executeQuery();
            resultSet.next();
            noOfQuiz = resultSet.getInt(1);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return noOfQuiz;
    }

    public int fetchAvgScore(String userName) {
        int avgScore = 0;
        try {
            String query = "SELECT AVG(percentage_score) FROM quiz_result WHERE username = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, userName);
            ResultSet resultSet = pst.executeQuery();
            resultSet.next();
            avgScore = resultSet.getInt(1);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return avgScore;
    }
}
