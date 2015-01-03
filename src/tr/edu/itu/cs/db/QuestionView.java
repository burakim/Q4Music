package tr.edu.itu.cs.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.repeater.RepeatingView;

import tr.edu.itu.cs.db.models.QuizStatistic;
import tr.edu.itu.cs.db.panels.QuestionAsk;
import tr.edu.itu.cs.db.panels.QuestionTab;


/*
 * Halim Burak Yesilyurt 2014
 * Q4Music
 * This class represents questions according to question template
 */
public class QuestionView extends DefaultPage {
    private Map<Integer, Integer> map;
    private QuestionView classptr;
    private Button sendbttn;
    private DbOperations dataconn;
    private ArrayList<Question> data;

    public QuestionView(final ArrayList<Question> data, final int categoryId) {
        super();

        WebMarkupContainer c1 = new WebMarkupContainer("opponentinfobox");
        Label l1 = new Label("opponentscore", "Not found score");
        c1.add(l1);
        c1.setVisible(false);
        this.add(c1);

        classptr = this;
        RepeatingView repeatingview = new RepeatingView("repeater");
        RepeatingView repeatingview1 = new RepeatingView("maddeler");
        map = new HashMap<Integer, Integer>();
        for (int i = 0; i < data.size(); i++) {

            int tabid = i + 1;

            QuestionTab questionAskTab = new QuestionTab(
                    repeatingview1.newChildId(), tabid);
            repeatingview1.add(questionAskTab);

            WebMarkupContainer c = new WebMarkupContainer(
                    repeatingview.newChildId());
            int tabid1 = i + 1;
            c.add(new HtmlProperty("id", "tab_4_" + Integer.toString(i + 1))
                    .getAttributeModifier());
            if (tabid == 1) {
                c.add(new HtmlProperty("class", "tab-pane active")
                        .getAttributeModifier());

            } else {
                c.add(new HtmlProperty("class", "tab-pane")
                        .getAttributeModifier());

            }
            QuestionAsk questionAskPanel = new QuestionAsk(classptr,
                    "questionask-panel", tabid1, data.get(i));
            c.add(questionAskPanel);
            repeatingview.add(c);

        }
        if (data.size() == 0) {
            repeatingview.setVisible(false);
            repeatingview1.setVisible(false);
        }
        add(repeatingview);
        add(repeatingview1);
        Form form = new Form("form");
        sendbttn = new Button("answersendbttn") {
            @Override
            public void onSubmit() {
                dataconn = new DbOperations();

                QuizStatistic result = CalculateScore(data, map);
                int userId = 0;
                if (UserSession.getInstance().getUserModel() != null) {
                    userId = UserSession.getInstance().getUserModel()
                            .GetObjectId();
                }

                if (dataconn == null) {
                    System.out.println("DATA CONN NULL");
                }
                result.setCategoryId(categoryId);
                result.setOwnerId(userId);
                dataconn.AddQuizResults(userId, categoryId,
                        result.getUserScoreDifference(),
                        result.getCorrectAnswer(), result.getWrongAnswer());

                this.setResponsePage(new QuizResultPage(result, false));
            }

        };
        if (data.size() == 0) {

        }
        form.add(sendbttn);
        this.add(form);

    }

    private static final long serialVersionUID = 1L;

    public QuestionView(ChallengeModel cdata) {
        super();
        dataconn = new DbOperations();

        WebMarkupContainer c1 = new WebMarkupContainer("opponentinfobox");
        Label l1 = new Label("opponentscore", dataconn.getName(cdata
                .getUserId())
                + "'s score completed this quiz with "
                + cdata.getOwnerScore() + " points.");
        c1.add(l1);
        c1.setVisible(true);
        this.add(c1);

        int qids[] = new int[5];

        for (int i = 0; i < cdata.getChallengemodel().size(); i++) {
            qids[i] = cdata.getChallengemodel().get(i).getQuestionId();

        }

        String categoryName = dataconn.GetCategoryNameByQuestionId(qids[0]);

        data = dataconn.FetchLimitedQuestions(qids, categoryName);

        classptr = this;
        final int categoryId = cdata.getCategoryId();
        RepeatingView repeatingview = new RepeatingView("repeater");
        RepeatingView repeatingview1 = new RepeatingView("maddeler");
        map = new HashMap<Integer, Integer>();
        for (int i = 0; i < cdata.getChallengemodel().size(); i++) {

            int tabid = i + 1;

            QuestionTab questionAskTab = new QuestionTab(
                    repeatingview1.newChildId(), tabid);
            repeatingview1.add(questionAskTab);

            WebMarkupContainer c = new WebMarkupContainer(
                    repeatingview.newChildId());
            int tabid1 = i + 1;
            c.add(new HtmlProperty("id", "tab_4_" + Integer.toString(i + 1))
                    .getAttributeModifier());
            if (tabid == 1) {
                c.add(new HtmlProperty("class", "tab-pane active")
                        .getAttributeModifier());

            } else {
                c.add(new HtmlProperty("class", "tab-pane")
                        .getAttributeModifier());

            }
            QuestionAsk questionAskPanel = new QuestionAsk(classptr,
                    "questionask-panel", tabid1, data.get(i));
            c.add(questionAskPanel);
            repeatingview.add(c);

        }

        add(repeatingview);
        add(repeatingview1);
        Form form = new Form("form");
        sendbttn = new Button("answersendbttn") {
            @Override
            public void onSubmit() {
                dataconn = new DbOperations();

                QuizStatistic result = CalculateScore(data, map);
                int userId = 0;
                if (UserSession.getInstance().getUserModel() != null) {
                    userId = UserSession.getInstance().getUserModel()
                            .GetObjectId();
                    result.setOwnerId(userId);
                }

                if (dataconn == null) {
                    System.out.println("DATA CONN NULL");
                }

                result.setCategoryId(categoryId);
                result.setOwnerId(userId);
                dataconn.AddQuizResults(userId, categoryId,
                        result.getUserScoreDifference(),
                        result.getCorrectAnswer(), result.getWrongAnswer());

                this.setResponsePage(new QuizResultPage(result, true));
            }

        };
        form.add(sendbttn);
        this.add(form);
    }

    public void InsertAnswer(int id, int answer) {

        map.put(id, answer);
    }

    public QuizStatistic CalculateScore(ArrayList<Question> questions,
            Map<Integer, Integer> userChoices) {

        QuizStatistic quizstatistic;
        ArrayList<Question> wrongAnsw = new ArrayList<Question>();
        ArrayList<Question> trueAnsw = new ArrayList<Question>();
        int tempScore = 0;
        int negansw = 0;
        int posansw = 0;
        for (int i = 0; i < userChoices.size(); i++) {
            if (questions.get(i).getCorrectanw() != userChoices.get(questions
                    .get(i).getQuestionId())) {

                tempScore -= questions.get(i).getScore()[1];
                negansw++;
                wrongAnsw.add(questions.get(i));

            } else {

                tempScore += questions.get(i).getScore()[0];

                trueAnsw.add(questions.get(i));
                posansw++;
            }
        }

        quizstatistic = new QuizStatistic(negansw, posansw, tempScore,
                wrongAnsw, trueAnsw);
        return quizstatistic;
    }
}
