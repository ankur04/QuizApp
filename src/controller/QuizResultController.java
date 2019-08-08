package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import main.Main;
import model.Question;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import service.QuizResultsService;
import static constants.PathConstants.*;
import static constants.SceneConstants.ANSWERS_TITLE;
import static constants.SceneConstants.RANKING_TITLE;
import static main.Main.getQuestionsList;
import static main.Main.navigate;

public class QuizResultController {

    @FXML
    PieChart pieChartQuizResult;
    private static int correctCount = 0;
    private static int incorrectCount = 0;

    @FXML
    private Label timeUsed;



    @FXML
    public void displayData() {
        pieChartQuizResult.setData(FXCollections.observableArrayList(
                new PieChart.Data("Correct " + correctCount, correctCount),
                new PieChart.Data("Incorrect " + incorrectCount, incorrectCount)
                )
        );
        QuizResultsService quizResultsService = new QuizResultsService(correctCount);
        long timeUsed = quizResultsService.insertResultInDB(getQuestionsList(), Main.user);
        String timeUsedInString = String.format("%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes(timeUsed),
                TimeUnit.MILLISECONDS.toSeconds(timeUsed) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeUsed))
        );
        this.timeUsed.setText(timeUsedInString);
        incorrectCount = 0;
        correctCount = 0;
    }

    public void initialize() {

        List<Question> questionList= getQuestionsList();
        questionList.stream().forEach(question -> {
            if (question.getMarkedAns()<=0 || (question.getMarkedAns() != question.getCorrectAns())) {
                incorrectCount++;
            }
            else if (question.getMarkedAns() == question.getCorrectAns()) {
                correctCount++;
            }
        });
        displayData();

    }

    @FXML
    private void back() {
        Main.back();
    }

    @FXML
    private void viewAnswers() {
        try {
            navigate(ANSWERS_FXML_PATH, ANSWERS_TITLE, CSS_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void viewRanking() {
        try {
            navigate(RANKING_FXML_PATH, RANKING_TITLE, CSS_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
