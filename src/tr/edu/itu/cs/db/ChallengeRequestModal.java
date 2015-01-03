package tr.edu.itu.cs.db;

import java.util.ArrayList;


public class ChallengeRequestModal {

    private int ownerId;
    private String code;
    private int categoryId;
    private ArrayList<ChallengeQuestionModel> data;

    public ChallengeRequestModal() {
        this.data = new ArrayList<ChallengeQuestionModel>();
    }

    public ChallengeRequestModal(int ownerId, String code, int categoryId,
            ArrayList<ChallengeQuestionModel> data) {
        this.ownerId = ownerId;
        this.code = code;
        this.categoryId = categoryId;
        this.data = new ArrayList<ChallengeQuestionModel>();
        for (ChallengeQuestionModel data_single : data) {
            this.data.add(data_single);
        }

    }

}
