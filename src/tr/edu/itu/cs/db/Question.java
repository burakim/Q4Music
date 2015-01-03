package tr.edu.itu.cs.db;

import java.io.Serializable;


public class Question implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public String getQuestiontext() {
        return questiontext;
    }

    public void setQuestiontext(String questiontext) {
        this.questiontext = questiontext;
    }

    public String[] getChoices() {
        return choices;
    }

    public void setChoices(String[] choices) {
        if (this.choices == null) {
            this.choices = new String[choices.length];
            for (int i = 0; i < choices.length; i++) {
                this.choices[i] = choices[i];
            }

        } else
            this.choices = choices;
    }

    public int getCorrectanw() {
        return correctanw;
    }

    public void setCorrectanw(int correctanw) {
        this.correctanw = correctanw;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public int[] getScore() {
        return score;
    }

    public void setScore(int[] score) {
        this.score = score;
    }

    private String questiontext;
    private String[] choices;
    private int correctanw;
    private String hint;
    private int[] score;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    private int questionId;

    public Question() {

    }

    public Question(int questionId, String questiontext, String[] choices,
            int correctanwid, String hint, int positivescore, int negativescore) {
        this.questionId = questionId;
        this.questiontext = questiontext;
        if (choices != null) {
            this.choices = new String[choices.length];
            for (int i = 0; i < choices.length; i++) {
                this.choices[i] = choices[i];
            }
        }
        this.correctanw = correctanwid;

        this.hint = hint;
        this.score = new int[2];
        score[0] = positivescore;
        score[1] = negativescore;

    }

}
