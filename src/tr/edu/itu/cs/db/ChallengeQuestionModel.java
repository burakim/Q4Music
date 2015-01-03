package tr.edu.itu.cs.db;

public class ChallengeQuestionModel {

    public int getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(int isTrue) {
        this.isTrue = isTrue;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    private int isTrue;
    private int questionId;

    public ChallengeQuestionModel() {

    }

    public ChallengeQuestionModel(int questionId, int isTrue) {
        this.questionId = questionId;
        this.isTrue = isTrue;
    }

}
