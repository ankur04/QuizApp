package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.layout.Pane;
import main.Main;

import java.net.URL;
import java.util.ResourceBundle;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pieChart.setOpacity(0);
        barChart.setOpacity(0);
        lineChart.setOpacity(0);
        lineChart.getData().clear();
        barChart.getData().clear();
        pieChart.getData().clear();
        showLineChart();
    }

    @FXML
    public void showLineChart() {
        lineChart.getData().clear();
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("1", 7));
        series.getData().add(new XYChart.Data("2", 4));
        series.getData().add(new XYChart.Data("3", 7));
        series.getData().add(new XYChart.Data("4", 9));
        series.getData().add(new XYChart.Data("5", 10));
        series.getData().add(new XYChart.Data("6", 6));
        series.getData().add(new XYChart.Data("7", 3));
        series.getData().add(new XYChart.Data("8", 1));
        series.getData().add(new XYChart.Data("9", 4));
        series.getData().add(new XYChart.Data("10", 5));
        lineChart.getData().addAll(series);
        setOpacity(LINE_CHART);

    }

    @FXML
    public void showPieChart(ActionEvent event) {
        pieChart.getData().clear();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Quiz 1", 8),
                new PieChart.Data("Quiz 2", 8),
                new PieChart.Data("Quiz 3", 6),
                new PieChart.Data("Quiz 4", 2),
                new PieChart.Data("Quiz 5", 2),
                new PieChart.Data("Quiz 6", 9),
                new PieChart.Data("Quiz 7", 10),
                new PieChart.Data("Quiz 8", 0),
                new PieChart.Data("Quiz 9", 1),
                new PieChart.Data("Quiz 10", 4)

        );

        pieChart.setData(pieChartData);
        pieChart.setStartAngle(90);
        setOpacity(PIE_CHART);

    }


    @FXML
    public void showBarChart(ActionEvent event) {
        barChart.getData().clear();
        XYChart.Series barSeries = new XYChart.Series<>();
        barSeries.getData().add(new XYChart.Data("Quiz 1", 8));
        barSeries.getData().add(new XYChart.Data("Quiz 2", 4));
        barSeries.getData().add(new XYChart.Data("Quiz 3", 2));
        barSeries.getData().add(new XYChart.Data("Quiz 4", 9));
        barSeries.getData().add(new XYChart.Data("Quiz 5", 7));
        barSeries.getData().add(new XYChart.Data("Quiz 6", 5));
        barSeries.getData().add(new XYChart.Data("Quiz 6", 1));
        barSeries.getData().add(new XYChart.Data("Quiz 6", 2));
        barSeries.getData().add(new XYChart.Data("Quiz 6", 4));
        barSeries.getData().add(new XYChart.Data("Quiz 6", 10));

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
