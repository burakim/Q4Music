package tr.edu.itu.cs.db;

import java.util.ArrayList;


public class UserScoreModel {
    private UserModel usermodel;
    private int usertotalscore;
    private ArrayList<UserStatisticsModel> userstatisticsmodellist;

    public UserModel getUserModel() {
        return usermodel;
    }

    public void setUserModel(UserModel usermodel) {
        this.usermodel = usermodel;
    }

    public int getUserTotalScore() {
        return usertotalscore;
    }

    public void setUserTotalScore(int usertotalscore) {
        this.usertotalscore = usertotalscore;
    }

    public ArrayList<UserStatisticsModel> getUserStatisticsModelList() {
        return userstatisticsmodellist;
    }

    public void setUserStatisticsModelList(
            ArrayList<UserStatisticsModel> userstatisticsmodellist) {
        this.userstatisticsmodellist = userstatisticsmodellist;
    }

    public UserScoreModel() {

    }
}
