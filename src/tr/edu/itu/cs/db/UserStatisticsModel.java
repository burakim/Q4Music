package tr.edu.itu.cs.db;

import java.io.Serializable;


public class UserStatisticsModel implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private UserModel usermodel;
    private String QuizSongType;
    private int TotalScore;
    private int TrueAnswerCount;
    private int FalseAnswerCount;

    public UserModel GetUserModel() {
        return usermodel;
    }

    public void SetUserModel(UserModel usermodel) {
        this.usermodel = usermodel;
    }

    public String GetQuizSongType() {
        return QuizSongType;
    }

    public void SetQuizSongType(String QuizSongType) {
        this.QuizSongType = QuizSongType;
    }

    public int GetTotalScore() {
        return TotalScore;
    }

    public void SetTotalScore(int TotalScore) {
        this.TotalScore = TotalScore;
    }

    public int GetTrueAnswerCount() {
        return TrueAnswerCount;
    }

    public void SetTrueAnswerCount(int TrueAnswerCount) {
        this.TrueAnswerCount = TrueAnswerCount;
    }

    public int GetFalseAnswerCount() {
        return FalseAnswerCount;
    }

    public void SetFalseAnswerCount(int FalseAnswerCount) {
        this.FalseAnswerCount = FalseAnswerCount;
    }

    public UserStatisticsModel() {
        usermodel = null;
        QuizSongType = "";
        TotalScore = 0;
        TrueAnswerCount = 0;
        FalseAnswerCount = 0;

    }
}
