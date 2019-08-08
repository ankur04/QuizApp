package service;

import controller.AppearForQuizController;
import dao.StoreQuizResultsDao;
import main.Main;
import model.Question;
import model.User;

import java.util.List;

public class QuizResultsService {
    private int correctScore;
    private double percentageScore = 0;
    private double pointsObtaned = 0;
    private double totalPoints = 0;

    public QuizResultsService(int correctScore) {
        this.correctScore = correctScore;
    }

    public long insertResultInDB(List<Question> questionsList, User user) {
        StoreQuizResultsDao storeQuizResultsDao = new StoreQuizResultsDao();
        questionsList.stream().forEach(question -> {
            if (question.getMarkedAns() > 0 && question.getCorrectAns() == question.getMarkedAns()) {
                pointsObtaned = question.getPoints();
            }
            totalPoints = question.getPoints();
        });

        storeQuizResultsDao.insertInDB(correctScore, pointsObtaned * 100 / totalPoints,
                AppearForQuizController.getEndTime() - AppearForQuizController.getStartTime(), user.getUserName(), Main.getCategory().getCategoryNo());
        return (long) (AppearForQuizController.getEndTime() - AppearForQuizController.getStartTime());
    }
}
