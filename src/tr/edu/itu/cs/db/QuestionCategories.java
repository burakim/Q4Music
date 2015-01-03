package tr.edu.itu.cs.db;

import java.util.ArrayList;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.repeater.RepeatingView;

import tr.edu.itu.cs.db.panels.CategoryPanel;


public class QuestionCategories extends DefaultPage {
    private ArrayList<String> data;
    public DbOperations dboperations;
    public CategoryPanel c1;

    public QuestionCategories(boolean error) {
        super();
        WebMarkupContainer c1 = new WebMarkupContainer("warnmessage");
        if (error == true) {
            c1.setVisible(true);

        } else {
            c1.setVisible(false);
        }
        this.add(c1);
        dboperations = new DbOperations();
        data = dboperations.FetchMusicAllSubjectName();

        RepeatingView repatingview = new RepeatingView("repeater");
        for (int i = 0; i < data.size(); i++) {

            WebMarkupContainer c = new WebMarkupContainer(
                    repatingview.newChildId());
            repatingview.add(c);
            CategoryPanel categorypanel = new CategoryPanel("categories");
            categorypanel.AddCategoryName(data.get(i).toString());
            categorypanel.AddCategoryPicture("nopic.ong");
            c.add(categorypanel);

        }
        this.add(repatingview);

    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

}
