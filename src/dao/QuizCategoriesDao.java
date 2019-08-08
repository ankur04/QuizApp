package dao;

import constants.DBConstants;
import model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizCategoriesDao {

    private Connection connection;

    public QuizCategoriesDao() {
        try {
            this.connection = DriverManager.getConnection(DBConstants.URL, DBConstants.USERNAME, DBConstants.PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        try {
            String query = "SELECT * FROM quiz_category";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                categories.add(new Category(
                        resultSet.getInt("CATEGORY_NO"),
                        resultSet.getString("CATEGORY_NAME")/*,
                        resultSet.getString("CATEGORY_DETAILS")*/
                ));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return categories;
    }
}
