package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import main.Main;
import model.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static constants.PathConstants.CSS_PATH;
import static constants.PathConstants.QUIZ_RESULT_FXML_PATH;
import static constants.SceneConstants.QUIZ_RESULT_TITLE;
import static main.Main.navigate;

public class AppearForQuizController {
    private List<Question> questionList = new ArrayList<>();
    private List<String> optionsList = new ArrayList<>();
    private static int currentIndex = 1;

    public AppearForQuizController() {
        questionList.add(new Question(1, "Toronto won 1st NBA Championship in which year",
                "1994", "2012", "2020", "2019", 4, 4));
        questionList.add(new Question(2, "Which is the Highest Rated TV Show in IMDB?",
                "Game of thrones", "Chernobyl", "Breaking Bad", "House M.D.", 3, 2));

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
    private RadioButton radioA;

    @FXML
    private RadioButton radioB;

    @FXML
    private RadioButton radioC;

    @FXML
    private RadioButton radioD;

    @FXML
    private ToggleGroup options;

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
        System.out.println(currentIndex);
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
    public void next() {
        resetParameters();
        sliderQuestion.setValue(currentIndex + 1);
        if (currentIndex >= 1 && currentIndex < 10) {
            displayQuestion(currentIndex + 1);
        } else if (currentIndex < 1) {
            displayQuestion(1);
        } else if (currentIndex >= 10) {
            displayQuestion(10);
        }
    }

    @FXML
    public void submit() {
        try {
            navigate(QUIZ_RESULT_FXML_PATH, QUIZ_RESULT_TITLE, CSS_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    }

    @FXML
    void back(){
        Main.back();
    }


}
