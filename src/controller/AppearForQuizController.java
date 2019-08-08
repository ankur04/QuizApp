package controller;

import dao.AppearForQuizDao;
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
import model.Category;
import model.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static constants.Constant.NEXT;
import static constants.Constant.PREVIOUS;
import static constants.PathConstants.CSS_PATH;
import static constants.PathConstants.QUIZ_RESULT_FXML_PATH;
import static constants.SceneConstants.QUIZ_RESULT_TITLE;
import static main.Main.navigate;
import static main.Main.setQuestionsList;

public class AppearForQuizController {
    private List<Question> questionList = new ArrayList<>();
    private List<String> optionsList = new ArrayList<>();
    private static int currentIndex = 0;
    private static double startTime = 0;
    private static double endTime = 0;
    public static double getStartTime() {
        return startTime;
    }
    public static void setStartTime(double startTime) {
        AppearForQuizController.startTime = startTime;
    }
    public static double getEndTime() {
        return endTime;
    }
    public static void setEndTime(double endTime) {
        AppearForQuizController.endTime = endTime;
    }

    public AppearForQuizController() {
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

    private Question currentQuestion;
    private Category category;

    @FXML
    public void clicked() {

        int questionNo = (int) sliderQuestion.getValue();
        resetParameters();
        displayQuestion(questionNo-1);
    }

    public void initialize() {
        startTime = System.currentTimeMillis();
        this.category = Main.getCategory();
        AppearForQuizDao appearForQuizDao = new AppearForQuizDao();
        questionList = appearForQuizDao.getAllQuestions(category.getCategoryNo());
        displayQuestion(0);
    }

    @FXML
    public void previous() {


        setMarkedAns(currentIndex);
        resetParameters();
        currentIndex--;
        sliderQuestion.setValue(currentIndex);
        if (currentIndex >= 1 && currentIndex <= 9) {
            displayQuestion(currentIndex);
        } else if (currentIndex <= 0) {
            displayQuestion(1);
        } else if (currentIndex > 9) {
            displayQuestion(9);
        }

    }

    @FXML
    public void next () {
        setMarkedAns(currentIndex);
        resetParameters();
        currentIndex++;
        sliderQuestion.setValue(currentIndex);
        if (currentIndex == 9) {
            submit();
        }
        if (currentIndex >= 0 && currentIndex < 9) {
            displayQuestion(currentIndex);
        } else if (currentIndex < 0) {
            displayQuestion(1);
        } else if (currentIndex >= 9) {
            displayQuestion(9);
        }
    }

    private void setMarkedAns(int currentIndex) {

        if  (options.getSelectedToggle() != null) {
            String selectedOption = ((RadioButton) options.getSelectedToggle()).getId();
            int selectedInt = 0;
            if (selectedOption.contains("A")) {
                selectedInt = 1;
            } else if (selectedOption.contains("B")) {
                selectedInt = 2;
            } else if (selectedOption.contains("C")) {
                selectedInt = 3;
            } else if (selectedOption.contains("D")) {
                selectedInt = 4;
            }
            this.questionList.get(currentIndex).setMarkedAns(selectedInt);
        }

    }

        private String getIdOfSelectedToggle ( int currentIndex){
            if (questionList.get(currentIndex).getMarkedAns() > 0) {
                int index = questionList.get(currentIndex).getMarkedAns();
                if (index == 1) {
                    return "A";
                } else if (index == 2) {
                    return "B";
                } else if (index == 3) {
                    return "C";
                } else {
                    return "D";
                }
            }
            return null;
        }



        @FXML
        public void submit () {
            try {
                endTime = System.currentTimeMillis();
                setMarkedAns(currentIndex);
                setQuestionsList(questionList);
                navigate(QUIZ_RESULT_FXML_PATH, QUIZ_RESULT_TITLE, CSS_PATH);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        private void displayQuestion ( int index){
            currentIndex = index;
            labelQuestionNo.setText(String.valueOf(currentIndex + 1));
            try {
                Question currentQuestion = questionList.get(currentIndex);
                if (questionList != null && currentQuestion != null) {
                    labelQuestion.setText(currentQuestion.getQuestion());
                    labelOption1.setText(currentQuestion.getOption1());
                    labelOption2.setText(currentQuestion.getOption2());
                    labelOption3.setText(currentQuestion.getOption3());
                    labelOption4.setText(currentQuestion.getOption4());
                }
                if (currentQuestion.getMarkedAns() > 0) {
                    if (currentQuestion.getMarkedAns() == 1) {
                        radioA.setSelected(true);
                    } else if (currentQuestion.getMarkedAns() == 2) {
                        radioB.setSelected(true);
                    } else if (currentQuestion.getMarkedAns() == 3) {
                        radioC.setSelected(true);
                    } else {
                        radioD.setSelected(true);
                    }
                }

            } catch (Exception e) {

            }
        }

        private void resetParameters () {
            if (options.getSelectedToggle() != null) {
                options.getSelectedToggle().setSelected(false);
            }
        }

        @FXML
        void back () {
            Main.back();
        }


}
