package dao;

import constants.DBConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StoreQuizResultsDao {

    private Connection connection;

    public StoreQuizResultsDao() {
        try {
            this.connection = DriverManager.getConnection(DBConstants.URL, DBConstants.USERNAME, DBConstants.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insertInDB(int correctScore, double percentageScore, double totalTimeUsed, String userName, int categoryNo) {
        try {
            String query = "INSERT INTO quiz_result (score_obtained,percentage_score,time_used,username,category_no) VALUES (?,?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(query);
            int i = 1;
            pst.setInt(i++, correctScore);
            pst.setDouble(i++, percentageScore);
            pst.setDouble(i++, totalTimeUsed);
            pst.setString(i++,userName);
            pst.setInt(i++,categoryNo);
            pst.executeUpdate();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
