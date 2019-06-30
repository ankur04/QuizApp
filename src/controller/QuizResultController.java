package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import main.Main;

import java.io.IOException;

import static constants.PathConstants.*;
import static constants.SceneConstants.ANSWERS_TITLE;
import static constants.SceneConstants.RANKING_TITLE;
import static main.Main.navigate;

public class QuizResultController {

    @FXML
    PieChart pieChartQuizResult;

    @FXML
    public void displayDummy() {
        pieChartQuizResult.setData(FXCollections.observableArrayList(
                new PieChart.Data("Correct " + 70, 70),
                new PieChart.Data("Incorrect " + 30, 30)
                )
        );
    }

    public void initialize() {
        displayDummy();
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
