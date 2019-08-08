package controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import main.Main;
import model.QuizResult;
import service.QuizResultsService;
import service.ViewAllQuizResultService;

import java.util.ArrayList;
import java.util.List;

import static constants.PathConstants.HOME_FXML_PATH;
import static constants.SceneConstants.HOME_TITLE;
import static main.Main.navigate;

public class MyRankingController {

    @FXML
    Pane paneView;
    private List<QuizResult> quizResultListByCategory = new ArrayList<>();
    private List<QuizResult> quizResultListByNoOfQuiz = new ArrayList<>();

    @FXML
    private Label rankLabel;

    private void populateBarChart(String name, ObservableList<XYChart.Data>data){
        CategoryAxis categoryAxis = new CategoryAxis();
        NumberAxis numberAxis = new NumberAxis();
        numberAxis.setLabel(name);
        XYChart.Series series = new XYChart.Series();
        series.setName("Ranking");
        series.setData(data);
        BarChart barChartRanking = new BarChart(categoryAxis, numberAxis);
        barChartRanking.getData().add(series);
        barChartRanking.setPrefWidth(383);
        barChartRanking.setPrefHeight(267);
        paneView.getChildren().add(barChartRanking);
    }

    @FXML
    public void home() throws Exception{
        navigate(HOME_FXML_PATH, HOME_TITLE, null);
    }

    @FXML
    void back() {
        Main.back();
    }

    @FXML
    public void displayByScoreObtained() {
        paneView.getChildren().clear();
        XYChart.Series series = new XYChart.Series();
        series.setName("Ranking");
        ObservableList data = series.getData();
        quizResultListByCategory.stream().forEach(quizResult -> data.add(
                new XYChart.Data<>(quizResult.getUserName(),quizResult.getTotalScore())));
        populateBarChart("Score Obtained", data);
        String username = Main.user.getUserName();
        quizResultListByCategory.stream().forEach(quizResult -> {
            if (quizResult.getUserName().equalsIgnoreCase(username)) {
                rankLabel.setText(String.valueOf(quizResultListByCategory.indexOf(quizResult) + 1));
            }
        });
    }

    @FXML
    public void displayByNoOfQuiz() {
        paneView.getChildren().clear();
        XYChart.Series series = new XYChart.Series();
        series.setName("Ranking");
        ObservableList data = series.getData();
        quizResultListByNoOfQuiz.stream().forEach(quizResult -> data.add(
                new XYChart.Data<>(quizResult.getUserName(),quizResult.getNoOfQuiz())));
        populateBarChart("No Of Quiz", data);
        quizResultListByNoOfQuiz.stream().forEach(quizResult -> {
            if (quizResult.getUserName().equalsIgnoreCase(Main.user.getUserName())) {
                rankLabel.setText(String.valueOf(quizResultListByNoOfQuiz.indexOf(quizResult)+ 1));
            }
        });
    }



    public void initialize() {
        ViewAllQuizResultService viewAllQuizResultService = new ViewAllQuizResultService();
        quizResultListByNoOfQuiz = viewAllQuizResultService.retrieveAllValuesByNoOfQuiz();
        quizResultListByCategory = viewAllQuizResultService.retrieveAllValuesByCategory();
        displayByScoreObtained();
    }
}
