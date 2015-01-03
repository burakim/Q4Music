package tr.edu.itu.cs.db.Admin;

import java.util.ArrayList;
import java.util.Map;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;

import tr.edu.itu.cs.db.DbOperations;
import tr.edu.itu.cs.db.HtmlProperty;
import tr.edu.itu.cs.db.Question;


public class AdminQuestionsPage extends AdminDefaultPage {

    private DbOperations questionOp;
    private ArrayList<Question> data;
    private ArrayList<String> categoryData_str;
    private Map<String, Integer> categoryData;

    public AdminQuestionsPage() {
        super();

        questionOp = new DbOperations();
        data = questionOp.FetchAllQuestionsandChoices();
        categoryData = questionOp.FetchCategoryIdandName();

        categoryData_str = (ArrayList<String>) questionOp
                .FetchAllSongTypeNames();

        final DataView dv = new DataView("questiontablerow",
                new ListDataProvider(data)) {

            @Override
            protected void populateItem(Item item) {

                final Question question = (Question) item.getModelObject();
                item.add(new Label("tdID", question.getQuestionId()));
                item.add(new Label("tdQuestionText", question.getQuestiontext()));
                item.add(new Label("tdCorrectChoice",
                        question.getChoices()[question.getCorrectanw()]));
                item.add(new Label("tdPositiveScore", Integer.toString(question
                        .getScore()[0])));
                item.add(new Label("tdNegativeScore", Integer.toString(question
                        .getScore()[1])));
                item.add(new Label("tdCategory", questionOp
                        .GetCategoryNameByQuestionId(question.getQuestionId())));

                Link editLink = new Link("tdEdit") {
                    @Override
                    public void onComponentTag(ComponentTag tag) {
                        super.onComponentTag(tag);
                        tag.put("objectid", question.getQuestionId());
                        tag.put("questiontext", question.getQuestiontext());
                        tag.put("correctchoice", question.getCorrectanw());
                        tag.put("positivescore",
                                Integer.toString(question.getScore()[0]));
                        tag.put("negativescore",
                                Integer.toString(question.getScore()[1]));
                        tag.put("category", questionOp
                                .GetCategoryNameByQuestionId(question
                                        .getQuestionId()));

                    }

                    @Override
                    public void onClick() {

                    }

                };

                editLink.add(new HtmlProperty("class", "QMODELEDIT")
                        .getAttributeModifier());
                item.add(editLink);

                Link deleteLink = new Link("tdDelete") {
                    @Override
                    public void onClick() {
                        Boolean sonuc = questionOp.DeleteAllEntries(question
                                .getQuestionId());
                        if (sonuc) {
                            questionOp.CloseConnection();
                            setResponsePage(new AdminQuestionsPage());
                        } else {
                            // TODO Responsive mesajlar eklenilmeli...
                        }

                    }
                };
                item.add(deleteLink);

            }
        };

        dv.setItemsPerPage(100);
        add(dv);
        Label lblCount = new Label("questioncount", new Model("("
                + Integer.toString(data.size()) + ")"));
        this.add(lblCount);

        // Table End

        // Modal Area

        final TextField tb_QuestionId = new TextField("tb_QuestionId",
                new Model(""));
        final TextField tb_QuestionText = new TextField("tb_QuestionText",
                new Model(""));
        final TextField tb_CorrectChoice = new TextField("tb_CorrectChoice",
                new Model(""));
        final TextField tb_PositiveScore = new TextField("tb_PositiveScore",
                new Model(""));
        final TextField tb_NegativeScore = new TextField("tb_NegativeScore",
                new Model(""));
        final DropDownChoice tb_CategoryName = new DropDownChoice(
                "tb_CategoryName", new Model<String>(""), categoryData_str);

        Button SaveChangesbttn = new Button("SaveChangesbttn");

        Form formDetails = new Form("Form_Details") {
            @Override
            public void onSubmit() {
                int _tb_QuestionId = Integer.parseInt(tb_QuestionId.getValue());
                String _tb_QuestionText = (String) tb_QuestionText.getValue();

                int val = 0;
                System.out.println("Choices =>  "
                        + (String) tb_CorrectChoice.getModelObject());
                if ("choice1"
                        .equals((String) tb_CorrectChoice.getModelObject()))
                    val = 0;
                else if ("choice2".equals((String) tb_CorrectChoice
                        .getModelObject()))
                    val = 1;
                else if ("choice3".equals((String) tb_CorrectChoice
                        .getModelObject()))
                    val = 2;
                else if ("choice4".equals((String) tb_CorrectChoice
                        .getModelObject()))
                    val = 3;

                int _tb_CorrectChoice = val;
                System.out.println("val = " + val);
                int _tb_PositiveScore = Integer.parseInt(tb_PositiveScore
                        .getValue());
                int _tb_NegativeScore = Integer.parseInt(tb_NegativeScore
                        .getValue());

                Question question_temp = new Question(_tb_QuestionId,
                        _tb_QuestionText, null, _tb_CorrectChoice, "",
                        _tb_PositiveScore, _tb_NegativeScore);
                int category_id = categoryData.get((String) tb_CategoryName
                        .getModelObject());
                System.out.println("DEgisend =>  " + category_id + " "
                        + (String) tb_CategoryName.getModelObject());
                if (questionOp.UpdateQuestionWithQuestionModal(question_temp,
                        category_id)) {
                    setResponsePage(new AdminQuestionsPage());
                }
            }
        };
        formDetails.add(SaveChangesbttn);
        formDetails.add(tb_QuestionId);
        formDetails.add(tb_QuestionText);
        formDetails.add(tb_CorrectChoice);
        formDetails.add(tb_PositiveScore);
        formDetails.add(tb_NegativeScore);
        formDetails.add(tb_CategoryName);
        this.add(formDetails);

        // End Modal Area

        // ADD Question Modal

        final TextField tb_QuestionAddText = new TextField(
                "tb_QuestionAddText", new Model(""));
        final TextField tb_PositiveScoreAdd = new TextField(
                "tb_PositiveScoreAdd", new Model(""));
        final TextField tb_NegativeScoreAdd = new TextField(
                "tb_NegativeScoreAdd", new Model(""));
        final DropDownChoice tb_CategoryAdd = new DropDownChoice(
                "tb_CategoryNameAdd", new Model<String>(""), categoryData_str);
        final TextField tb_HintAdd = new TextField("tb_HintAdd", new Model(""));
        final TextField tb_Choice1 = new TextField("tb_Choice1Add", new Model(
                ""));
        final TextField tb_Choice2 = new TextField("tb_Choice2Add", new Model(
                ""));
        final TextField tb_Choice3 = new TextField("tb_Choice3Add", new Model(
                ""));
        final TextField tb_Choice4 = new TextField("tb_Choice4Add", new Model(
                ""));
        final TextField tb_CorrectChoiceAdd = new TextField(
                "tb_CorrectChoiceAdd", new Model(""));
        final Button btnSaveChangesAddQuestion = new Button(
                "btnSaveChangesAddQuestion");

        final Question question_add = new Question();
        Form Form_AddQuestion = new Form("Form_AddQuestion") {
            @Override
            public void onSubmit() {
                question_add.setQuestiontext((String) tb_QuestionAddText
                        .getModelObject());
                int scores[] = new int[2];
                try {

                    scores[0] = Integer.parseInt((String) tb_PositiveScoreAdd
                            .getModelObject());
                    scores[1] = Integer.parseInt((String) tb_NegativeScoreAdd
                            .getModelObject());
                } catch (NullPointerException ex) {
                    System.out.println(ex.toString());
                } catch (NumberFormatException ex) {
                    setResponsePage(new AdminQuestionsPage());
                    System.out.println(ex.toString());
                }
                question_add.setScore(scores);
                int categoryID_add = categoryData.get((String) tb_CategoryAdd
                        .getModelObject());
                question_add.setHint((String) tb_HintAdd.getModelObject());
                String choices[] = new String[4];

                choices[0] = (String) tb_Choice1.getModelObject();
                choices[1] = (String) tb_Choice2.getModelObject();
                choices[2] = (String) tb_Choice3.getModelObject();
                choices[3] = (String) tb_Choice4.getModelObject();
                question_add.setChoices(choices);

                int val = 0;
                if ("choice1".equals((String) tb_CorrectChoiceAdd
                        .getModelObject()))
                    val = 0;
                else if ("choice2".equals((String) tb_CorrectChoiceAdd
                        .getModelObject()))
                    val = 1;
                else if ("choice3".equals((String) tb_CorrectChoiceAdd
                        .getModelObject()))
                    val = 2;
                else if ("choice4".equals((String) tb_CorrectChoiceAdd
                        .getModelObject()))
                    val = 3;
                question_add.setCorrectanw(val);

                questionOp.AddQuestionHintChoices(question_add, categoryID_add);
                setResponsePage(new AdminQuestionsPage());
            }
        };

        Form_AddQuestion.add(tb_QuestionAddText);
        Form_AddQuestion.add(tb_PositiveScoreAdd);
        Form_AddQuestion.add(tb_NegativeScoreAdd);
        Form_AddQuestion.add(tb_CorrectChoiceAdd);
        Form_AddQuestion.add(tb_CategoryAdd);
        Form_AddQuestion.add(tb_HintAdd);
        Form_AddQuestion.add(tb_Choice1);
        Form_AddQuestion.add(tb_Choice2);
        Form_AddQuestion.add(tb_Choice3);
        Form_AddQuestion.add(tb_Choice4);
        Form_AddQuestion.add(btnSaveChangesAddQuestion);

        this.add(Form_AddQuestion);
        // End question add modal
    }
}
