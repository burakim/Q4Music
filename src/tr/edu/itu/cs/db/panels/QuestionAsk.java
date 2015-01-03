package tr.edu.itu.cs.db.panels;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import tr.edu.itu.cs.db.HtmlProperty;
import tr.edu.itu.cs.db.Question;
import tr.edu.itu.cs.db.QuestionView;


public class QuestionAsk extends Panel {
    // question data labels
    private Label question_DataLabel;
    private Label question_Button1;
    private Label question_Button2;
    private Label question_Button3;
    private Label question_Button4;
    private int whoSelected;
    private Label hint;
    private Label[] choices;
    private WebMarkupContainer choice1_outer;
    private WebMarkupContainer choice2_outer;
    private WebMarkupContainer choice3_outer;
    private WebMarkupContainer choice4_outer;
    private WebMarkupContainer hintTitle;
    private WebMarkupContainer hintBody;
    private QuestionView questionView;

    public QuestionAsk(QuestionView ptr, String id, int questionnumber,
            final Question question) {

        super(id);
        whoSelected = -1;
        choices = new Label[4];
        this.questionView = ptr;
        hintTitle = new WebMarkupContainer("hinttitle");
        hintBody = new WebMarkupContainer("hintbody");
        final WebMarkupContainer questionform = new WebMarkupContainer(
                "question");
        question_DataLabel = new Label("question-question",
                question.getQuestiontext());

        hintTitle.add(new HtmlProperty("href", "#" + "hint-"
                + question.getQuestionId()).getAttributeModifier());
        hintBody.add(new HtmlProperty("id", "hint-" + question.getQuestionId())
                .getAttributeModifier());

        hint = new Label("hint", question.getHint());

        choice1_outer = new WebMarkupContainer("choice-outer1");
        choice2_outer = new WebMarkupContainer("choice-outer2");
        choice3_outer = new WebMarkupContainer("choice-outer3");
        choice4_outer = new WebMarkupContainer("choice-outer4");

        question_Button1 = new Label("choice1", question.getChoices()[0]);
        question_Button2 = new Label("choice2", question.getChoices()[1]);
        question_Button3 = new Label("choice3", question.getChoices()[2]);
        question_Button4 = new Label("choice4", question.getChoices()[3]);

        questionform.setOutputMarkupId(true);
        choice1_outer.setOutputMarkupId(true);
        question_Button1.setOutputMarkupId(true);
        question_Button2.setOutputMarkupId(true);
        question_Button3.setOutputMarkupId(true);
        question_Button4.setOutputMarkupId(true);
        choice2_outer.setOutputMarkupId(true);
        choice3_outer.setOutputMarkupId(true);
        choice4_outer.setOutputMarkupId(true);
        choices[0] = question_Button1;
        choices[1] = question_Button2;
        choices[2] = question_Button3;
        choices[3] = question_Button4;

        choice1_outer.add(new AjaxEventBehavior("onclick") {

            @Override
            protected void onEvent(AjaxRequestTarget arg0) {
                int oldelement;

                // TODO Auto-generated method stub

                questionView.InsertAnswer(question.getQuestionId(), 0);
                oldelement = whoSelected;
                if (whoSelected != 0) {
                    if (whoSelected != -1)
                        choices[whoSelected].add(new HtmlProperty("style", "")
                                .getAttributeModifier());
                    whoSelected = 0;
                    question_Button1.add(new HtmlProperty("style",
                            "background-color:red").getAttributeModifier());

                }
                if (oldelement != -1)
                    arg0.add(choices[oldelement]);

                arg0.add(question_Button1);

            }

        });

        choice2_outer.add(new AjaxEventBehavior("onclick") {

            @Override
            protected void onEvent(AjaxRequestTarget arg0) {
                int oldelement;
                // TODO Auto-generated method stub

                questionView.InsertAnswer(question.getQuestionId(), 1);
                oldelement = whoSelected;
                if (whoSelected != 1) {
                    if (whoSelected != -1)
                        choices[whoSelected].add(new HtmlProperty("style", "")
                                .getAttributeModifier());
                    whoSelected = 1;
                    question_Button2.add(new HtmlProperty("style",
                            "background-color:red").getAttributeModifier());

                }
                if (oldelement != -1)

                    arg0.add(choices[oldelement]);
                arg0.add(question_Button2);
            }

        });

        choice3_outer.add(new AjaxEventBehavior("onclick") {

            @Override
            protected void onEvent(AjaxRequestTarget arg0) {
                int oldelement;

                // TODO Auto-generated method stub

                questionView.InsertAnswer(question.getQuestionId(), 2);
                oldelement = whoSelected;
                if (whoSelected != 2) {
                    if (whoSelected != -1)
                        choices[whoSelected].add(new HtmlProperty("style", "")
                                .getAttributeModifier());
                    whoSelected = 2;
                    question_Button3.add(new HtmlProperty("style",
                            "background-color:red").getAttributeModifier());

                }
                if (oldelement != -1)

                    arg0.add(choices[oldelement]);

                arg0.add(question_Button3);
            }

        });

        choice4_outer.add(new AjaxEventBehavior("onclick") {

            @Override
            protected void onEvent(AjaxRequestTarget arg0) {
                int oldelement;

                // TODO Auto-generated method stub

                questionView.InsertAnswer(question.getQuestionId(), 3);
                oldelement = whoSelected;
                if (whoSelected != 3) {
                    if (whoSelected != -1)
                        choices[whoSelected].add(new HtmlProperty("style", "")
                                .getAttributeModifier());
                    whoSelected = 3;
                    question_Button4.add(new HtmlProperty("style",
                            "background-color:red").getAttributeModifier());

                }
                if (oldelement != -1)

                    arg0.add(choices[oldelement]);

                arg0.add(question_Button4);
            }

        });

        questionform.add(question_DataLabel);
        choice1_outer.add(question_Button1);
        choice2_outer.add(question_Button2);
        choice3_outer.add(question_Button3);
        choice4_outer.add(question_Button4);
        questionform.add(choice1_outer);
        questionform.add(choice2_outer);
        questionform.add(choice3_outer);
        questionform.add(choice4_outer);
        hintBody.add(hint);
        questionform.add(hintBody);
        questionform.add(hintTitle);

        add(questionform);

    }
}
