package tr.edu.itu.cs.db;

import java.io.Serializable;


public class Choices implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    private int questionid;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    private int correctAnswer;

    public int getQuestionOrder() {
        return correctAnswer;
    }

    public void setQuestionOrder(int questionOrder) {
        this.correctAnswer = questionOrder;
    }

    private String questionText;

    public int[] getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(int[] choiceId) {
        this.choiceId = choiceId;
        String choiceid = "";
        if (this.idStr == "" || this.idStr == null) {
            for (int i = 0; i < getChoiceId().length; i++) {
                if (i == 0) {
                    choiceid += getChoiceId()[0];
                } else {
                    choiceid += (" - " + getChoiceId()[i]);
                }
            }
            this.idStr = choiceid;
        }
    }

    private int choiceId[];
    private String idStr;

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
        String ids[] = idStr.split(" - ");
        int temp_choice[] = new int[4];
        for (int i = 0; i < ids.length; i++) {
            try {

                temp_choice[i] = Integer.parseInt(ids[i]);
            } catch (NumberFormatException ex) {
                System.out.println(ex.toString());
            }
        }
        setChoiceId(temp_choice);
    }

    public String[] getChoices() {
        return choices;
    }

    public void setChoices(String[] choices) {
        this.choices = choices;
    }

    private String choices[];

    public Choices(int[] choiceID, String[] choices) {
        String choiceid = "";

        this.choiceId = new int[4];
        this.choices = new String[4];
        for (int i = 0; i < choiceId.length; i++)
            this.choiceId[i] = choiceID[i];
        for (int i = 0; i < choices.length; i++)
            this.choices[i] = choices[i];

        for (int i = 0; i < getChoiceId().length; i++) {
            if (i == 0) {
                choiceid += getChoiceId()[0];
            } else {
                choiceid += (" - " + getChoiceId()[i]);
            }

        }
        this.questionText = choiceid;

    }

    public Choices(int[] choiceID, String[] choices, int questionOrder,
            int questionId, String questionText) {
        String choiceid = "";

        this.choiceId = new int[4];
        this.choices = new String[4];
        for (int i = 0; i < choiceId.length; i++)
            this.choiceId[i] = choiceID[i];
        for (int i = 0; i < choices.length; i++)
            this.choices[i] = choices[i];

        for (int i = 0; i < getChoiceId().length; i++) {
            if (i == 0) {
                choiceid += getChoiceId()[0];
            } else {
                choiceid += (" - " + getChoiceId()[i]);
            }

        }
        this.questionText = choiceid;
        this.correctAnswer = questionOrder;
        this.questionText = questionText;
        this.questionid = questionId;

    }

    public Choices() {
        this.choiceId = new int[4];
        this.choices = new String[4];
        this.idStr = "";
    }
}
