package model;

public class QuizResult {
    private int quizno;
    private double scoreObtained;
    private double percentageScores;
    private double timeUsed;
    private String userName;

    public QuizResult() {

    }

    public QuizResult(int quizno, double scoreObtained, double percentageScores, double timeUsed, String userName) {
        this.quizno = quizno;
        this.scoreObtained = scoreObtained;
        this.percentageScores = percentageScores;
        this.timeUsed = timeUsed;
        this.userName = userName;
    }

    public int getQuizno() {
        return quizno;
    }

    public void setQuizno(int quizno) {
        this.quizno = quizno;
    }

    public double getScoreObtained() {
        return scoreObtained;
    }

    public void setScoreObtained(double scoreObtained) {
        this.scoreObtained = scoreObtained;
    }

    public double getPercentageScores() {
        return percentageScores;
    }

    public void setPercentageScores(double percentageScores) {
        this.percentageScores = percentageScores;
    }

    public double getTimeUsed() {
        return timeUsed;
    }

    public void setTimeUsed(double timeUsed) {
        this.timeUsed = timeUsed;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
