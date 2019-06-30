package model;

public class Question {

    private int questionNo;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int markedAns;
    private int correctAns;
    private double points;
    private int categoryNo;

    public Question() {
    }

    public Question(int questionNo, String question, String option1, String option2, String option3, String option4, int markedAns, int correctAns) {
        this.questionNo = questionNo;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.markedAns = markedAns;
        this.correctAns = correctAns;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public int getMarkedAns() {
        return markedAns;
    }

    public void setMarkedAns(int markedAns) {
        this.markedAns = markedAns;
    }

    public int getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(int correctAns) {
        this.correctAns = correctAns;
    }
}
