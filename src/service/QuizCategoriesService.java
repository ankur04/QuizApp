package service;

import dao.QuizCategoriesDao;
import model.Category;

import java.util.List;

public class QuizCategoriesService {
    public List<Category> getCategories() {
        return new QuizCategoriesDao().getCategories();
    }
}
