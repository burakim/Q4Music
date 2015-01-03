package tr.edu.itu.cs.db.panels;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import tr.edu.itu.cs.db.HtmlProperty;


public class QuestionTab extends Panel {
    public QuestionTab(String id, int tabid) {
        super(id);
        final WebMarkupContainer questiontab_markup = new WebMarkupContainer(
                "questiontab");
        if (tabid == 1) {
            questiontab_markup.add(new HtmlProperty("class", "active")
                    .getAttributeModifier());
        }
        String tab_href = "#tab_4_" + tabid;
        Label questiontab = new Label("question_tab", Integer.toString(tabid));
        questiontab.add(new HtmlProperty("href", tab_href)
                .getAttributeModifier());
        // questiontab.setOutputMarkupId(true);
        questiontab_markup.add(questiontab);
        add(questiontab_markup);
    }

}
