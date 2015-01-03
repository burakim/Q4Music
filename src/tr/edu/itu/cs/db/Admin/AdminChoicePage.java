package tr.edu.itu.cs.db.Admin;

import java.io.Serializable;
import java.util.List;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import tr.edu.itu.cs.db.Choices;
import tr.edu.itu.cs.db.DbOperations;


public class AdminChoicePage extends AdminDefaultPage implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    PageParameters pp = new PageParameters();
    private DbOperations ChoiceOp;
    private List<Choices> list;

    public AdminChoicePage() {
        super();
        ChoiceOp = new DbOperations();
        list = ChoiceOp.FetchAllChoices();

        final DataView dv = new DataView("choicestablerow",
                new ListDataProvider(list)) {

            @Override
            protected void populateItem(final Item item) {
                final Choices choice = (Choices) item.getModelObject();
                System.out.println(choice.getIdStr());
                item.add(new Label("tdChoiceID", choice.getIdStr()));
                item.add(new Label("tdChoice1", choice.getChoices()[0]));
                item.add(new Label("tdChoice2", choice.getChoices()[1]));
                item.add(new Label("tdChoice3", choice.getChoices()[2]));
                item.add(new Label("tdChoice4", choice.getChoices()[3]));
                item.add(new Label("tdQuestion", choice.getQuestionText()));
                item.add(new Label("tdCorrectChoice",
                        choice.getQuestionOrder() + 1));

                Link testLnk = new Link("tdEdit") {
                    @Override
                    public void onComponentTag(ComponentTag tag) {
                        // TODO Auto-generated method stub
                        super.onComponentTag(tag);
                        tag.put("id", choice.getIdStr());
                        tag.put("tdchoice1", choice.getChoices()[0]);
                        tag.put("tdchoice2", choice.getChoices()[1]);
                        tag.put("tdchoice3", choice.getChoices()[2]);
                        tag.put("tdchoice4", choice.getChoices()[3]);
                        tag.put("tdquestion_data", choice.getQuestionText());
                        tag.put("tdcorrectanswer", choice.getQuestionOrder());
                    }

                    @Override
                    public void onClick() {

                    }
                };
                testLnk.add(new AttributeAppender("class", " QCMODELEDIT"));
                item.add(testLnk);

            }
        };

        this.add(dv);
        final TextField tb_choiceid = new TextField("tb_ChoiceId",
                new Model(""));
        final TextField tb_choiceid2 = new TextField("tb_ChoiceId2", new Model(
                ""));

        final TextField tb_choice1 = new TextField("tb_Choice1", new Model(""));

        final TextField tb_choice2 = new TextField("tb_Choice2", new Model(""));
        final TextField tb_choice3 = new TextField("tb_Choice3", new Model(""));
        final TextField tb_choice4 = new TextField("tb_Choice4", new Model(""));
        final TextField tb_question = new TextField("tb_QuestionChoice",
                new Model(""));
        final TextField tb_questionorder = new TextField("tb_CorrectAnswer",
                new Model(""));

        final Button sendButton = new Button("sendbttn");

        final Choices choice_edit = new Choices();
        Form Form_Details = new Form("Form_Details") {

            @Override
            protected void onSubmit() {
                System.out.println("Update baslasin !!");
                choice_edit.setIdStr(((String) tb_choiceid2.getModelObject()));
                System.out.println("Line 1");

                String temp_choices[] = new String[4];
                System.out.println("Line 2");
                temp_choices[0] = ((String) tb_choice1.getModelObject());
                System.out.println("Line 3");
                temp_choices[1] = ((String) tb_choice2.getModelObject());
                System.out.println("Line 4");
                temp_choices[2] = ((String) tb_choice3.getModelObject());
                System.out.println("Line 5");
                temp_choices[3] = ((String) tb_choice4.getModelObject());
                System.out.println("Line 6");
                choice_edit.setChoices(temp_choices);
                System.out.println("Line 7");
                int val = 0;
                // System.out.println("Kim dogru"
                // + (String) tb_questionorder.getModelObject());
                if ("choice1"
                        .equals((String) tb_questionorder.getModelObject())) {
                    val = 0;
                    System.out.println("Kim dogru"
                            + (String) tb_questionorder.getModelObject());
                }

                else if ("choice2".equals((String) tb_questionorder
                        .getModelObject())) {
                    val = 1;
                    System.out.println("Kim dogru"
                            + (String) tb_questionorder.getModelObject());
                }

                else if ("choice3".equals((String) tb_questionorder
                        .getModelObject())) {
                    val = 2;
                    System.out.println("Kim dogru"
                            + (String) tb_questionorder.getModelObject());
                }

                else if (("choice4".equals((String) tb_questionorder
                        .getModelObject()))) {
                    val = 3;
                    System.out.println("Kim dogru"
                            + (String) tb_questionorder.getModelObject());
                }
                System.out.println("Line 8 " + val);
                choice_edit.setQuestionOrder(val);
                System.out.println("Line 9");
                ChoiceOp.UpdateChoicesWithChoiceIdandQuestionID(choice_edit);

                setResponsePage(new AdminChoicePage());
            }

        };

        Form_Details.add(tb_choiceid);
        Form_Details.add(sendButton);
        Form_Details.add(tb_choiceid2);
        Form_Details.add(tb_choice1);
        Form_Details.add(tb_choice2);
        Form_Details.add(tb_choice3);
        Form_Details.add(tb_choice4);
        Form_Details.add(tb_question);
        Form_Details.add(tb_questionorder);
        this.add(Form_Details);
    }
}
