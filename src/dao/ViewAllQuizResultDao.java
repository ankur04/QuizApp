package dao;

import constants.DBConstants;
import main.Main;
import model.Question;
import model.QuizResult;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViewAllQuizResultDao {

    private Connection connection;

    public ViewAllQuizResultDao() {
        try {
            this.connection = DriverManager.getConnection(DBConstants.URL, DBConstants.USERNAME, DBConstants.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<QuizResult> retireveValues(String userName) {
        List<QuizResult> quizResultList = new ArrayList<>();
        try {
            String query = "select * from quiz_result where username=?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, userName);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                quizResultList.add(new QuizResult(
                        resultSet.getInt("quiz_no"),
                        resultSet.getDouble("score_obtained"),
                        resultSet.getDouble("percentage_score"),
                        resultSet.getDouble("time_used"),
                        resultSet.getString("username"),
                        resultSet.getInt("category_no")
                ));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return quizResultList;
    }

    public List<QuizResult> retireveAllValues() {
        List<QuizResult> quizResultList = new ArrayList<>();
        try {
            String query = "select * from quiz_result";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                quizResultList.add(new QuizResult(
                        resultSet.getInt("quiz_no"),
                        resultSet.getDouble("score_obtained"),
                        resultSet.getDouble("percentage_score"),
                        resultSet.getDouble("time_used"),
                        resultSet.getString("username"),
                        resultSet.getInt("category_no")
                ));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return quizResultList;
    }

    public List<QuizResult> retireveAllValuesByNoOfQuiz() {
        List<QuizResult> quizResultList = new ArrayList<>();
        try {
            String query = "select count(*) as count,username from quiz_result group by username order by count desc";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                quizResultList.add(new QuizResult(
                        resultSet.getString("username"),
                        resultSet.getInt("count")
                ));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return quizResultList;
    }

    public List<QuizResult> retireveAllValuesByCategory() {
        List<QuizResult> quizResultList = new ArrayList<>();
        try {
            String query = "select sum(score_obtained) as totalScore, username from quiz_result where category_no=? group by username order by totalScore desc";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, Main.getCategory() != null ? Main.getCategory().getCategoryNo() : 0);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                quizResultList.add(new QuizResult(
                        resultSet.getInt("totalScore"),
                        resultSet.getString("username")
                ));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return quizResultList;
    }
}
