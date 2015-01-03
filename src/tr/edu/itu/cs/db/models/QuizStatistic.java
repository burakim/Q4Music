package tr.edu.itu.cs.db.models;

import java.util.ArrayList;

import tr.edu.itu.cs.db.Question;


public class QuizStatistic {

    public int getWrongAnswer() {
        return wrongAnswer;
    }

    public void setWrongAnswer(int wrongAnswer) {
        this.wrongAnswer = wrongAnswer;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getUserScoreDifference() {
        return userScoreDifference;
    }

    public void setUserScoreDifference(int userScoreDifference) {
        this.userScoreDifference = userScoreDifference;
    }

    public ArrayList<Question> getWrongQuestions() {
        return wrongQuestions;
    }

    public void setWrongQuestions(ArrayList<Question> wrongQuestions) {
        this.wrongQuestions = wrongQuestions;
    }

    private int wrongAnswer;
    private int correctAnswer;
    private int userScoreDifference;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    private int categoryId;
    ArrayList<Question> wrongQuestions;
    ArrayList<Question> trueQuestions;

    public ArrayList<Question> getTrueQuestions() {
        return trueQuestions;
    }

    public void setTrueQuestions(ArrayList<Question> trueQuestions) {
        this.trueQuestions = trueQuestions;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int opponentId) {
        this.ownerId = opponentId;
    }

    private int ownerId;

    public QuizStatistic(int wrongAnswer, int correctAnswer,
            int userScoreDifference, ArrayList<Question> wrongQuestions,
            ArrayList<Question> trueQuestions) {

        this.ownerId = -1;
        this.categoryId = -1;
        this.wrongAnswer = wrongAnswer;
        this.correctAnswer = correctAnswer;
        this.userScoreDifference = userScoreDifference;
        this.wrongQuestions = new ArrayList<Question>();
        this.trueQuestions = new ArrayList<Question>();

        for (int i = 0; i < wrongQuestions.size(); i++)
            this.wrongQuestions.add(wrongQuestions.get(i));
        for (int i = 0; i < trueQuestions.size(); i++)
            this.trueQuestions.add(trueQuestions.get(i));

    }

    public QuizStatistic(int ownerId, int categoryId, int wrongAnswer,
            int correctAnswer, int userScoreDifference,
            ArrayList<Question> wrongQuestions,
            ArrayList<Question> trueQuestions) {
        this.ownerId = ownerId;
        this.categoryId = categoryId;
        this.wrongAnswer = wrongAnswer;
        this.correctAnswer = correctAnswer;
        this.userScoreDifference = userScoreDifference;
        this.wrongQuestions = new ArrayList<Question>();
        this.trueQuestions = new ArrayList<Question>();
        for (int i = 0; i < wrongQuestions.size(); i++)
            this.wrongQuestions.add(wrongQuestions.get(i));
        for (int i = 0; i < trueQuestions.size(); i++)
            this.trueQuestions.add(trueQuestions.get(i));

    }

}
