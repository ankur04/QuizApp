package controller;

import dao.AppearForQuizDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.Main;
import model.Question;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static constants.PathConstants.*;
import static constants.SceneConstants.CUMULATIVE_QUIZ_RESULTS_TITLE;
import static constants.SceneConstants.HOME_TITLE;

public class ViewEachQuestionAnsController {
    private List<Question> questionList = new ArrayList<>();
    private static int currentIndex = 1;

    public ViewEachQuestionAnsController() {
        AppearForQuizDao appearForQuizDao = new AppearForQuizDao();
        questionList = Main.getQuestionsList();

    }

    @FXML
    private Label labelQuestionNo;


    @FXML
    private Label labelQuestion;

    @FXML
    private Label labelOption1;

    @FXML
    private Label labelOption2;

    @FXML
    private Label labelOption3;

    @FXML
    private Label labelOption4;

    @FXML
    private AnchorPane backgroun;

    @FXML
    private Pane abcd;

    @FXML
    private Slider sliderQuestion;

    @FXML
    private Button buttonShowCumulativeResult;

    @FXML
    public void clicked() {
        resetParameters();
        int questionNo = (int) sliderQuestion.getValue();
        displayQuestion(questionNo);
    }

    @FXML
    public void previous() {
        resetParameters();
        sliderQuestion.setValue(currentIndex - 1);
        if (currentIndex >= 2 && currentIndex <= 10) {
            displayQuestion(currentIndex - 1);
        } else if (currentIndex <= 1) {
            displayQuestion(1);
        } else if (currentIndex > 10) {
            displayQuestion(10);
        }
    }

    @FXML
    public void next() throws Exception{
        resetParameters();
        sliderQuestion.setValue(currentIndex + 1);
        if (currentIndex == 10) {
            Main.navigate(CUMULATIVE_FXML_PATH, CUMULATIVE_QUIZ_RESULTS_TITLE, CSS_PATH);
        }
        if (currentIndex >= 1 && currentIndex < 10) {
            displayQuestion(currentIndex + 1);
        } else if (currentIndex < 1) {
            displayQuestion(1);
        } else if (currentIndex >= 10) {
            displayQuestion(10);
        }
    }

    public void initialize() {
        displayQuestion(1);
    }


    private void displayQuestion(int questionNo) {
        labelQuestionNo.setText(String.valueOf(questionNo));
        try {
            Question currentQuestion = questionList.get(questionNo - 1);
            if (questionList != null && currentQuestion != null) {
                labelQuestion.setText(currentQuestion.getQuestion());
                labelOption1.setText(currentQuestion.getOption1());
                labelOption2.setText(currentQuestion.getOption2());
                labelOption3.setText(currentQuestion.getOption3());
                labelOption4.setText(currentQuestion.getOption4());
                if (currentQuestion.getMarkedAns() == currentQuestion.getCorrectAns()) {
                    setFontOfCorrectAnsAndBackgroundColor(currentQuestion.getCorrectAns(), Color.LIGHTGREEN);
                } else {
                    setFontOfCorrectAnsAndBackgroundColor(currentQuestion.getCorrectAns(), Color.LIGHTPINK);
                    strikeIncorrectAns(currentQuestion.getMarkedAns());
                }

            }
            currentIndex = questionNo;
        } catch (Exception e) {

        }
    }

    private void resetParameters() {
        labelOption1.setFont(new Font("Arial", 20));
        labelOption1.setTextFill(Paint.valueOf("0x333333ff"));
        labelOption2.setFont(new Font("Arial", 20));
        labelOption2.setTextFill(Paint.valueOf("0x333333ff"));
        labelOption3.setFont(new Font("Arial", 20));
        labelOption3.setTextFill(Paint.valueOf("0x333333ff"));
        labelOption4.setFont(new Font("Arial", 20));
        labelOption4.setTextFill(Paint.valueOf("0x333333ff"));
    }

    private void strikeIncorrectAns(int markedAns) {
        if (markedAns == 1) {
            labelOption1.setTextFill(Color.RED);
        } else if (markedAns == 2) {
            labelOption2.setTextFill(Color.RED);
        } else if (markedAns == 3) {
            labelOption3.setTextFill(Color.RED);
        } else if (markedAns == 4) {
            labelOption4.setTextFill(Color.RED);
        }
    }

    private void setFontOfCorrectAnsAndBackgroundColor(int optionNumber, Color color) {
        backgroun.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        if (optionNumber == 1) {
            labelOption1.setFont(Font.font(Font.getFontNames().get(0),
                    FontWeight.EXTRA_BOLD, 30));
            labelOption1.setTextFill(Color.GREEN);
        } else if (optionNumber == 2) {
            labelOption2.setFont(Font.font(Font.getFontNames().get(0),
                    FontWeight.EXTRA_BOLD, 30));
            labelOption2.setTextFill(Color.GREEN);
        } else if (optionNumber == 3) {
            labelOption3.setFont(Font.font(Font.getFontNames().get(0),
                    FontWeight.EXTRA_BOLD, 30));
            labelOption3.setTextFill(Color.GREEN);
        } else if (optionNumber == 4) {
            labelOption4.setFont(Font.font(Font.getFontNames().get(0),
                    FontWeight.EXTRA_BOLD, 30));
            labelOption4.setTextFill(Color.GREEN);
        }
    }

    @FXML
    public void showCumulative(ActionEvent event) {
        try {
            Main.navigate(CUMULATIVE_FXML_PATH, CUMULATIVE_QUIZ_RESULTS_TITLE, CSS_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void back() {
        Main.back();
    }


}
