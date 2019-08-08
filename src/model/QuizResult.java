package model;

public class QuizResult {
    private int quizno;
    private double scoreObtained;
    private double percentageScores;
    private double timeUsed;
    private String userName;
    private int categoryNo;
    private int noOfQuiz;
    private int totalScore;

    public QuizResult() {

    }

    public QuizResult(int quizno, double scoreObtained, double percentageScores, double timeUsed, String userName, int categoryNo) {
        this.quizno = quizno;
        this.scoreObtained = scoreObtained;
        this.percentageScores = percentageScores;
        this.timeUsed = timeUsed;
        this.userName = userName;
        this.categoryNo = categoryNo;
    }

    public QuizResult(String userName, int noOfQuiz) {
        this.userName = userName;
        this.noOfQuiz = noOfQuiz;
    }

    public QuizResult(int totalScore, String userName) {
        this.userName = userName;
        this.totalScore = totalScore;
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

    public int getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(int categoryNo) {
        this.categoryNo = categoryNo;
    }

    public int getNoOfQuiz() {
        return noOfQuiz;
    }

    public void setNoOfQuiz(int noOfQuiz) {
        this.noOfQuiz = noOfQuiz;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}
