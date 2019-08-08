package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.layout.Pane;
import main.Main;
import model.QuizResult;
import service.ViewAllQuizResultService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static constants.PathConstants.*;
import static constants.SceneConstants.HOME_TITLE;
import static constants.SceneConstants.PRO_TITLE;
import static main.Main.navigate;

public class ViewAllQuizResultController implements Initializable {
    private final String LINE_CHART = "lineChart";
    private final String PIE_CHART = "pieChart";
    private final String BAR_CHART = "barChart";


    @FXML
    private LineChart<?, ?> lineChart;

    @FXML
    private BarChart<?, ?> barChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @FXML
    private Pane lineChartPane;

    @FXML
    private PieChart pieChart;


    @FXML
    private CategoryAxis xbar;

    @FXML
    private NumberAxis ybar;

    private List<QuizResult> quizResultList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pieChart.setOpacity(0);
        barChart.setOpacity(0);
        lineChart.setOpacity(0);
        lineChart.getData().clear();
        barChart.getData().clear();
        pieChart.getData().clear();
        ViewAllQuizResultService viewAllQuizResultService = new ViewAllQuizResultService();
        quizResultList =  viewAllQuizResultService.retriveValues(Main.user);
        showLineChart();
    }

    @FXML
    public void showLineChart() {
        lineChart.getData().clear();
        XYChart.Series series = new XYChart.Series();

        quizResultList.stream().forEach(quizResult ->
                series.getData().add(new XYChart.Data<String,Double>(String.valueOf(quizResult.getQuizno()),quizResult.getScoreObtained())));
        lineChart.getData().addAll(series);
        setOpacity(LINE_CHART);

    }


    @FXML
    void home(ActionEvent event) throws Exception{
        navigate(HOME_FXML_PATH, HOME_TITLE, null);
    }

    @FXML
    public void showPieChart(ActionEvent event) {
        pieChart.getData().clear();
        ObservableList<PieChart.Data> fxArray = FXCollections.observableArrayList();

        ObservableList<PieChart.Data> pieChartData = fxArray;
        quizResultList.stream().forEach(quizResult ->
                fxArray.add(new PieChart.Data(String.valueOf(quizResult.getQuizno()),quizResult.getScoreObtained())));

        pieChart.setData(pieChartData);
        pieChart.setStartAngle(90);
        setOpacity(PIE_CHART);

    }


    @FXML
    public void showBarChart(ActionEvent event) {
        barChart.getData().clear();
        XYChart.Series barSeries = new XYChart.Series<>();
        quizResultList.stream().forEach(quizResult ->
                barSeries.getData().add(new XYChart.Data<String,Double>(String.valueOf(quizResult.getQuizno()),quizResult.getScoreObtained())));
        barChart.getData().addAll(barSeries);
        setOpacity(BAR_CHART);

    }


    private void setOpacity(String name) {
        lineChart.setOpacity(0);
        pieChart.setOpacity(0);
        barChart.setOpacity(0);
        if (name.equalsIgnoreCase(LINE_CHART)) {
            lineChart.setOpacity(1);
        } else if (name.equalsIgnoreCase(PIE_CHART)) {
            pieChart.setOpacity(1);
        } else if (name.equalsIgnoreCase(BAR_CHART)) {
            barChart.setOpacity(1);
        }
    }

    @FXML
    private void back() {
        Main.back();
    }

}
