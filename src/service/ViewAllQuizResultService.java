package service;

import dao.ViewAllQuizResultDao;
import model.QuizResult;
import model.User;

import java.util.List;

public class ViewAllQuizResultService {

    public List<QuizResult> retriveValues(User user) {
        ViewAllQuizResultDao viewAllQuizResultDao = new ViewAllQuizResultDao();
        return viewAllQuizResultDao.retireveValues(user.getUserName());
    }

    public List<QuizResult> retrieveAllValues() {
        ViewAllQuizResultDao viewAllQuizResultDao = new ViewAllQuizResultDao();
        return viewAllQuizResultDao.retireveAllValues();
    }

    public List<QuizResult> retrieveAllValuesByNoOfQuiz() {
        ViewAllQuizResultDao viewAllQuizResultDao = new ViewAllQuizResultDao();
        return viewAllQuizResultDao.retireveAllValuesByNoOfQuiz();
    }

    public List<QuizResult> retrieveAllValuesByCategory() {
        ViewAllQuizResultDao viewAllQuizResultDao = new ViewAllQuizResultDao();
        return viewAllQuizResultDao.retireveAllValuesByCategory();
    }
}
