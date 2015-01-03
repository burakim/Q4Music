package tr.edu.itu.cs.db;

import java.io.Serializable;


public class Hint implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public int getHintId() {
        return hintId;
    }

    public void setHintId(int hintId) {
        this.hintId = hintId;
    }

    public String getHintText() {
        return hintText;
    }

    public void setHintText(String hintText) {
        this.hintText = hintText;
    }

    private int hintId;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    private int questionId;
    String hintText;

    public String getQuestionshortText() {
        return questionshortText;
    }

    public void setQuestionshortText(String questionshortText) {
        this.questionshortText = questionshortText;
    }

    String questionshortText;

    public Hint(int hintid, String hintText, int questionID, String questionText) {
        this.hintId = hintid;
        this.hintText = hintText;
        this.questionId = questionID;
        if (questionText.length() > 50)
            this.questionshortText = questionText.substring(0, 50);
        else
            this.questionshortText = questionText;
    }

    public Hint(int hintid, String hintText) {
        this.hintId = hintid;
        this.hintText = hintText;
    }

    public Hint() {

    }

}
