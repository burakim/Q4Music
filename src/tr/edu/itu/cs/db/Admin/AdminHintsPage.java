package tr.edu.itu.cs.db.Admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.behavior.AttributeAppender;
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
import org.apache.wicket.request.mapper.parameter.PageParameters;

import tr.edu.itu.cs.db.DbOperations;
import tr.edu.itu.cs.db.Hint;


public class AdminHintsPage extends AdminDefaultPage implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    PageParameters pp = new PageParameters();
    private DbOperations HintOp;
    private ArrayList<Hint> list;
    private ArrayList<String> questionShortText;
    private Map<String, Integer> shortquestionData;

    public AdminHintsPage() {
        super();

        shortquestionData = new HashMap<String, Integer>();
        HintOp = new DbOperations();
        list = HintOp.FetchAllHints();
        questionShortText = new ArrayList<String>();
        questionShortText.clear();
        final DataView dv = new DataView("hintstablerow", new ListDataProvider(
                list)) {

            @Override
            protected void populateItem(final Item item) {
                final Hint hint = (Hint) item.getModelObject();
                item.add(new Label("tdHintID", hint.getHintId()));
                item.add(new Label("tdHint", hint.getHintText()));
                item.add(new Label("tdQuestion", hint.getQuestionshortText()));
                shortquestionData.put(hint.getQuestionshortText(),
                        hint.getQuestionId());

                questionShortText.add(hint.getQuestionshortText());

                Link testLnk = new Link("tdEdit") {
                    @Override
                    public void onComponentTag(ComponentTag tag) {
                        // TODO Auto-generated method stub
                        super.onComponentTag(tag);
                        tag.put("id", hint.getHintId());
                        tag.put("tdhint", hint.getHintText());
                        tag.put("tdquestion", hint.getQuestionshortText());

                    }

                    @Override
                    public void onClick() {

                    }
                };
                testLnk.add(new AttributeAppender("class", " HintModal"));
                item.add(testLnk);

            }
        };

        this.add(dv);

        final TextField tb_hintid = new TextField("tb_HintId", new Model(""));
        final TextField tb_hintid2 = new TextField("tb_HintId2", new Model(""));

        final TextField tb_questionhint = new TextField("tb_QuestionHint",
                new Model(""));
        final TextField tb_QuestionName2 = new TextField("tb_QuestionText2",
                new Model(""));

        final DropDownChoice tb_QuestionName = new DropDownChoice(
                "tb_QuestionText", new Model<String>(""), questionShortText);
        final Button sendButton = new Button("sendbttn");

        final Hint hint_edit = new Hint();
        Form Form_Details = new Form("Form_Details") {

            @Override
            protected void onSubmit() {
                if (((String) tb_hintid.getModelObject() == "")
                        || ((String) tb_hintid2.getModelObject() == "")
                        || ((String) tb_questionhint.getModelObject() == "")) {
                    setResponsePage(new AdminHintsPage());
                }
                hint_edit
                        .setHintText((String) tb_questionhint.getModelObject());
                System.out.println((String) tb_hintid.getModelObject());

                hint_edit.setHintId(Integer.parseInt((String) tb_hintid2
                        .getModelObject()));
                hint_edit.setQuestionshortText((String) tb_QuestionName
                        .getModelObject());

                hint_edit.setQuestionId(shortquestionData
                        .get((String) tb_QuestionName2.getModelObject()));
                System.out.println("Question Changed Name"
                        + (String) tb_QuestionName.getModelObject());
                System.out.println(hint_edit.getQuestionId());
                HintOp.UpdateHintsWithHintId(hint_edit);
                setResponsePage(new AdminHintsPage());
            }

        };
        Form_Details.add(tb_QuestionName2);
        Form_Details.add(tb_hintid);
        Form_Details.add(tb_hintid2);
        Form_Details.add(sendButton);
        Form_Details.add(tb_questionhint);
        Form_Details.add(tb_QuestionName);
        this.add(Form_Details);
    }
}
