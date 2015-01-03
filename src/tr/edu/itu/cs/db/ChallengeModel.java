package tr.edu.itu.cs.db;

import java.util.ArrayList;


public class ChallengeModel {
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(int challengeId) {
        this.challengeId = challengeId;
    }

    private int userId;
    private int challengeId;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public ArrayList<ChallengeQuestionModel> getChallengemodel() {
        return challengemodel;
    }

    public void setChallengemodel(
            ArrayList<ChallengeQuestionModel> challengemodel) {
        this.challengemodel = challengemodel;
    }

    private int categoryId;
    private int ownerScore;

    public int getOwnerScore() {
        return ownerScore;
    }

    public void setOwnerScore(int ownerScore) {
        this.ownerScore = ownerScore;
    }

    private ArrayList<ChallengeQuestionModel> challengemodel;

    public ChallengeModel() {

    }

    public ChallengeModel(int userId, int ownerScore, int challengeId,
            int categoryId, ArrayList<ChallengeQuestionModel> challengemodel) {
        this.ownerScore = ownerScore;
        this.userId = userId;
        this.challengeId = challengeId;
        this.categoryId = categoryId;
        this.challengemodel = new ArrayList<ChallengeQuestionModel>();
        for (int qid = 0; qid < challengemodel.size(); qid++) {
            this.challengemodel.add(challengemodel.get(qid));
        }

    }

}
