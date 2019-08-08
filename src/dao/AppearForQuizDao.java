package dao;

import constants.DBConstants;
import model.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppearForQuizDao {
    private Connection connection;

    public AppearForQuizDao() {
        try {
            this.connection = DriverManager.getConnection(DBConstants.URL, DBConstants.USERNAME, DBConstants.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Question> getAllQuestions(int categoryNo) {
    List<Question> questions = new ArrayList<>();
        try {
            String query = "select * from quiz_questions where category_no=?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, categoryNo);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                questions.add(new Question(
                        resultSet.getInt("question_no"),
                        resultSet.getString("question"),
                        resultSet.getString("option1"),
                        resultSet.getString("option2"),
                        resultSet.getString("option3"),
                        resultSet.getString("option4"),
                        resultSet.getInt("correct_ans"),
                        resultSet.getInt("points"),
                        resultSet.getInt("category_no")
                ));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return questions;
    }

}
