package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;

public class MyRankingController {

    @FXML
    Pane paneView;

    @FXML
    public void displayByAccuracy() {
        paneView.getChildren().clear();

        CategoryAxis categoryAxis = new CategoryAxis();
        NumberAxis numberAxis = new NumberAxis();
        numberAxis.setLabel("Percentage");
        XYChart.Series series = new XYChart.Series();
        series.setName("Ranking");
        ObservableList data = series.getData();
        data.add(new XYChart.Data<>("User 1", 77));
        data.add(new XYChart.Data<>("User 2", 61));
        data.add(new XYChart.Data<>("User 3", 21));
        data.add(new XYChart.Data<>("User 4", 4));
        data.add(new XYChart.Data<>("User 5", 3));
        BarChart barChartRanking = new BarChart(categoryAxis, numberAxis);
        barChartRanking.getData().add(series);
        barChartRanking.setPrefWidth(383);
        barChartRanking.setPrefHeight(267);
        paneView.getChildren().add(barChartRanking);
    }

    public void initialize() {
        displayByAccuracy();
    }
}
